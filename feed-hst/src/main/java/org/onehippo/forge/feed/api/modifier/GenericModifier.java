package org.onehippo.forge.feed.api.modifier;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.feed.beans.GenericFeedDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class GenericModifier implements Modifier<SyndFeed, SyndEntry, GenericFeedDescriptor> {

    private static Logger log = LoggerFactory.getLogger(GenericModifier.class);


    @Override
    public void modifyHstQuery(final HstRequestContext context, final HstQuery query, final GenericFeedDescriptor descriptor) {

    }

    @Override
    public void modifyFeed(final HstRequestContext context, final SyndFeed feed, final GenericFeedDescriptor descriptor) {
    }

    @Override
    public void modifyEntry(final HstRequestContext context, final SyndEntry entry, final HippoBean bean) {

    }
}
