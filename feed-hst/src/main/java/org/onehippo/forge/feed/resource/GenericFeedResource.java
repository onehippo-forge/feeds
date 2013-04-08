/*
 *  Copyright 2010-2013 Hippo B.V. (http://www.onehippo.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.onehippo.forge.feed.resource;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedOutput;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.content.beans.manager.ObjectConverter;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.filter.BaseFilter;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.query.filter.PrimaryNodeTypeFilterImpl;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.rewriter.ContentRewriter;
import org.hippoecm.hst.content.rewriter.impl.SimpleContentRewriter;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.content.AbstractContentResource;
import org.onehippo.forge.feed.api.FeedDescriptor;
import org.onehippo.forge.feed.api.GenericEntry;
import org.onehippo.forge.feed.api.Modifier;
import org.onehippo.forge.feed.beans.GenericFeedDescriptor;
import org.onehippo.forge.feed.util.ConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
@Produces({MediaType.APPLICATION_XML})
@Path("/feed:genericdescriptor/")
public class GenericFeedResource extends AbstractContentResource {

    protected static final String HST_FEEDSITE = "hst:feedsite";
    private static Logger log = LoggerFactory.getLogger(GenericFeedResource.class);

    private Modifier<SyndFeed, SyndEntry, FeedDescriptor> modifier;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/")
    public String getFeed(@Context HttpServletRequest request, @Context HttpServletResponse response, @Context UriInfo info) {
        Writer writer = null;
        String feed = null;
        try {
            long indexCount = 0;
            long limit = 10L;
            String sortByField;
            HstRequestContext requestContext = getRequestContext(request);
            HippoDocumentBean contentBean = getRequestContentBean(requestContext, HippoDocumentBean.class);

            if (contentBean == null || !(contentBean instanceof FeedDescriptor)) {
                log.error("Cannot find feed descriptor. Check relative content path on feed's sitemap item.");
                return null;
            }

            final Mount mount = requestContext.getResolvedMount().getMount();
            final String mountSite = mount.getProperty(HST_FEEDSITE);
            FeedDescriptor<SyndFeed> document = (FeedDescriptor) contentBean;

            final SyndFeed syndFeed = document.convert();
            final String type = request.getParameter("type");
            if(StringUtils.isNotEmpty(type)){
              syndFeed.setFeedType(type);
            }
            syndFeed.setLink(createLinkByPath(requestContext, mountSite, "/"));

            // prevent NPE from auto-deboxing
            if (document.getItemCount() != null) {
                limit = document.getItemCount();
            }
            sortByField = document.getSortByField();

            // get scope for search query
            HippoBean scopeBean = getMountContentBaseBean(requestContext);
            String scope = document.getScope();
            if (scope != null && !"".equals(scope)) {
                HippoBean folderBean = scopeBean.getBean(scope);
                if (folderBean != null) {
                    scopeBean = folderBean;
                }
            }

            // FIXME get date field from BE template?
            HstQuery hstQuery = null;
            String documentType = document.getDocumentType();
            if (documentType != null && documentType.trim().length() != 0) {
                ObjectConverter converter = getObjectConverter(requestContext);
                // NOTE: for this to work, document needs to be registered within annotated-nbeans XML file
                final Class<? extends HippoBean> clazz = converter.getAnnotatedClassFor(documentType);
                if (clazz != null) {
                    hstQuery = getHstQueryManager(requestContext.getSession(), requestContext).createQuery(scopeBean, clazz, true);
                } else {
                    hstQuery = getHstQueryManager(requestContext.getSession(), requestContext).createQuery(scopeBean);
                    Filter filter = hstQuery.createFilter();
                    hstQuery.setFilter(filter);
                    BaseFilter nt = new PrimaryNodeTypeFilterImpl(documentType);
                    filter.addAndFilter(nt);
                }
            }
            if (hstQuery == null) {
                // dunno if we should go further at this point?
                hstQuery = getHstQueryManager(requestContext.getSession(), requestContext).createQuery(scopeBean);
            }
            if (sortByField != null) {
                hstQuery.addOrderByDescending(sortByField);
            }

            hstQuery.setLimit((int) limit);
            if (modifier != null) {
                modifier.modifyHstQuery(hstQuery, document);
            }
            HstQueryResult queryResult = hstQuery.execute();
            HippoBeanIterator beans = queryResult.getHippoBeans();

            List entries = new ArrayList();
            while (beans.hasNext() && indexCount < limit) {
                HippoBean bean = beans.nextHippoBean();
                if (bean != null && bean instanceof GenericEntry) {
                    final GenericEntry item = (GenericEntry) bean;
                    SyndEntry syndEntry = ConversionUtil.convertGenericEntryToSyndEntry(item);

                    final Object description = item.getDescription();

                    SyndContent content = new SyndContentImpl();
                    if (description instanceof String) {
                        content.setValue((String) item.getDescription());
                        syndEntry.setDescription(content);
                    } else if (description instanceof HippoHtml) {
                        final HippoHtml hippoHtml = (HippoHtml) description;
                        ContentRewriter<String> contentRewriter = getContentRewriter();
                        if (contentRewriter == null) {
                            contentRewriter = new SimpleContentRewriter();
                        }
                        String rewrittenHtml = contentRewriter.rewrite(hippoHtml.getContent(), hippoHtml.getNode(), requestContext, mountSite);
                        content.setValue(rewrittenHtml);
                        syndEntry.setDescription(content);
                    }
                    syndEntry.setLink(getLinkByBean(requestContext, mountSite, bean));
                    if (modifier != null) {
                        modifier.modifyEntry(syndEntry, bean);
                    }
                    entries.add(syndEntry);
                }
                indexCount++;
            }
            syndFeed.setEntries(entries);
            if (modifier != null) {
                modifier.modifyFeed(syndFeed, document);
            }

            writer = new StringWriter();
            SyndFeedOutput output = new SyndFeedOutput();
            output.output(syndFeed, writer);
            feed = writer.toString();
        } catch (Exception e) {
            log.error("Cannot execute query for Generic feed. {e}", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
        return feed;
    }

    private String getLinkByBean(final HstRequestContext requestContext, final String mountSite, final HippoBean bean) {
        final HstLink hstLink = requestContext.getHstLinkCreator().create(bean.getNode(), requestContext, mountSite);
        return hstLink.toUrlForm(requestContext, true);
    }

    private String createLinkByPath(final HstRequestContext requestContext, final String mountSite, final String path) {
        final HstLinkCreator hstLinkCreator = requestContext.getHstLinkCreator();
        final HstLink hstChannelLink = hstLinkCreator.create(path, requestContext.getMount(mountSite));
        return hstChannelLink.toUrlForm(requestContext, true);
    }

    public Modifier<SyndFeed, SyndEntry, FeedDescriptor> getModifier() {
        return modifier;
    }

    public void setModifier(final Modifier<SyndFeed, SyndEntry, FeedDescriptor> modifier) {
        this.modifier = modifier;
    }
}
