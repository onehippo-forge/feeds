/*
 * Copyright 2013-2016 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onehippo.forge.feed.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jcr.RepositoryException;
import javax.jcr.nodetype.NodeType;
import javax.jcr.nodetype.NodeTypeIterator;
import javax.jcr.nodetype.NodeTypeManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.content.AbstractContentResource;
import org.onehippo.forge.feed.api.FeedDescriptor;
import org.onehippo.forge.feed.api.modifier.Modifier;
import org.onehippo.forge.feed.util.ConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id: RssFeedResource.java 9 2013-04-08 08:29:25Z ksalic $"
 */
@Produces({MediaType.APPLICATION_XML})
@Path("/feed:rss20descriptor/")  //todo: include exclude path
public class AbstractSyndicationResource<T, E> extends AbstractContentResource {

    private final static Logger log = LoggerFactory.getLogger(AbstractSyndicationResource.class);

    private Modifier<T, E, FeedDescriptor> modifier;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/")
    public String getFeed(@Context HttpServletRequest request, @Context HttpServletResponse response, @Context UriInfo info) {
        String feed = null;
        try {
            HstRequestContext requestContext = getRequestContext(request);
            HippoDocumentBean contentBean = getRequestContentBean(requestContext, HippoDocumentBean.class);

            if (contentBean == null || !(contentBean instanceof FeedDescriptor)) {
                log.error("Cannot find feed descriptor. Check relative content path on feed's sitemap item.");
                return null;
            }
            @SuppressWarnings("unchecked")
            FeedDescriptor<T, E> document = (FeedDescriptor<T,E>) contentBean;
            final T channel = ConversionUtil.covertToAppropriateSyndicationFeed(document, document, this, requestContext);

            HstQuery hstQuery = getHstQuery(requestContext, document);

            HstQueryResult queryResult = hstQuery.execute();
            HippoBeanIterator beans = queryResult.getHippoBeans();

            List<E> entries = getModifiedEntries(requestContext, document, beans);

            document.set(channel, entries);

            if (modifier != null) {
                modifier.modifyFeed(requestContext, channel, document);
            }
            feed = document.process(channel);
        } catch (RepositoryException | QueryException | ObjectBeanManagerException e) {
            log.error("Cannot execute query for RSS feed. {e}", e);
        }
        return feed;
    }

    private List<E> getModifiedEntries(HstRequestContext requestContext, FeedDescriptor<T, E> document, HippoBeanIterator beans) {
        List<E> entries = new ArrayList<>();
        while (beans.hasNext()) {
            HippoBean bean = beans.nextHippoBean();
            if (bean != null) {
                final E item = ConversionUtil.covertToAppropriateSyndicationEntry(document, bean, this, requestContext);
                if (modifier != null) {
                    modifier.modifyEntry(requestContext, item, bean);
                }
                entries.add(item);
            }
        }
        return entries;
    }

    private HstQuery getHstQuery(HstRequestContext requestContext, FeedDescriptor<T, E> document) throws RepositoryException, QueryException, ObjectBeanManagerException {
        HippoBean scopeBean = getScopeForSearchQuery(requestContext, document);
        final String[] documentTypes = DocumentTypeHelper.getDocTypes(getDocTypes(requestContext, document), getExcludedDocTypes(document));

        HstQuery hstQuery = HstQueryBuilder.create(scopeBean)
                .ofTypes(documentTypes)
                .orderByDescending(document.getSortByField())
                .limit(document.getItemCount().intValue())
                .build();

        if (modifier != null) {
            modifier.modifyHstQuery(requestContext, hstQuery, document);
        }
        return hstQuery;
    }

    private HippoBean getScopeForSearchQuery(HstRequestContext requestContext, FeedDescriptor<T, E> document) throws ObjectBeanManagerException {
        HippoBean scopeBean = getMountContentBaseBean(requestContext);
        String scope = document.getScope();
        if (scope != null && !"".equals(scope)) {
            HippoBean folderBean = scopeBean.getBean(scope);
            if (folderBean != null) {
                scopeBean = folderBean;
            }
        }
        return scopeBean;
    }

    private Set<String> getExcludedDocTypes(final FeedDescriptor<T, E> document) {
        Set<String> result = new HashSet<>();
        if (document.getExclude()!=null){
            String[] excludedDocumentTypes = StringUtils.split(document.getExclude(), ", \t\r\n");
            result.addAll(Arrays.asList(excludedDocumentTypes));
        }
        return result;
    }

    private Set<DocType> getDocTypes(final HstRequestContext requestContext, final FeedDescriptor<T, E> document) throws RepositoryException {
        String [] documentTypes = StringUtils.split(document.getDocumentType(), ", \t\r\n");
        NodeTypeManager nodeTypeManager = requestContext.getSession().getWorkspace().getNodeTypeManager();
        Set<DocType> docTypes = new HashSet<>();
        for (String documentType : documentTypes) {
            NodeType nodeType = nodeTypeManager.getNodeType(documentType);
            NodeTypeIterator iterator = nodeType.getSubtypes();
            Set<String> subTypes = new HashSet<>();
            while (iterator.hasNext()){
                subTypes.add(iterator.nextNodeType().getName());
            }
            docTypes.add(new DocType(documentType,subTypes));
        }
        return docTypes;
    }

    public Modifier<T, E, FeedDescriptor> getModifier() {
        return modifier;
    }

    public void setModifier(final Modifier<T, E, FeedDescriptor> modifier) {
        this.modifier = modifier;
    }
}
