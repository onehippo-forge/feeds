package org.onehippo.forge.feed.api.transform;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.feed.api.annot.ContextTransformable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class DocumentLinkResolver {

    private static Logger log = LoggerFactory.getLogger(DocumentLinkResolver.class);

    @ContextTransformable
    public String getHstLink(HstRequestContext context, HippoBean document) {
        final HstLinkCreator hstLinkCreator = context.getHstLinkCreator();
        final HstLink hstChannelLink = hstLinkCreator.create(document, context);
        return hstChannelLink.toUrlForm(context, true);
    }

}
