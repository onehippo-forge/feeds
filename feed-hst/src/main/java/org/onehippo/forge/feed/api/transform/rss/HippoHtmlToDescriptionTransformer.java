package org.onehippo.forge.feed.api.transform.rss;

import com.sun.syndication.feed.rss.Description;

import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.rewriter.ContentRewriter;
import org.hippoecm.hst.content.rewriter.impl.SimpleContentRewriter;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.onehippo.forge.feed.api.annot.ContextTransformable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class HippoHtmlToDescriptionTransformer {

    private static Logger log = LoggerFactory.getLogger(HippoHtmlToDescriptionTransformer.class);

    @ContextTransformable
    public Description transform(HippoHtml html, AbstractResource resource, HstRequestContext context) {
        Description description = new Description();
        ContentRewriter<String> contentRewriter = resource.getContentRewriter();
        if (contentRewriter == null) {
            contentRewriter = new SimpleContentRewriter();
            contentRewriter.setFullyQualifiedLinks(true);
        }
        String rewrittenHtml = contentRewriter.rewrite(html.getContent(), html.getNode(), context);
        description.setValue(rewrittenHtml);
        return description;

    }


}
