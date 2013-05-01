package org.onehippo.forge.feed.api.transform.rss;

import com.sun.syndication.feed.rss.Image;

import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.feed.api.annot.ContextTransformable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class HippoGalleryImageSetToImageTransformer {

    private static Logger log = LoggerFactory.getLogger(HippoGalleryImageSetToImageTransformer.class);

    @ContextTransformable
    public Image getImage(HstRequestContext context, HippoGalleryImageSet imageSet) {
        Image image = new Image();
        final HstLinkCreator hstLinkCreator = context.getHstLinkCreator();
        final String baseLink = hstLinkCreator.create("/", context.getResolvedMount().getMount()).toUrlForm(context, true);
        final String imageLink = hstLinkCreator.create(imageSet, context).toUrlForm(context, true);
        image.setUrl(imageLink);
        image.setLink(baseLink);
        image.setTitle(imageSet.getFileName());
        return image;
    }

}
