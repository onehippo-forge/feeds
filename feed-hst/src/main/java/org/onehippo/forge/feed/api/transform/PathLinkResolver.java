package org.onehippo.forge.feed.api.transform;

import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.feed.api.annot.ContextTransformable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class PathLinkResolver {

    private static Logger log = LoggerFactory.getLogger(PathLinkResolver.class);

    @ContextTransformable
    public String getHstLink(HstRequestContext context, String path) {
        final HstLinkCreator hstLinkCreator = context.getHstLinkCreator();
        final HstLink hstChannelLink = hstLinkCreator.create(path, context.getResolvedMount().getMount());
        return hstChannelLink.toUrlForm(context, true);
    }

}
