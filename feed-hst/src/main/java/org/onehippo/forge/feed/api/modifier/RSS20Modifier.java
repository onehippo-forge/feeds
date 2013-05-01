package org.onehippo.forge.feed.api.modifier;


import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.feed.beans.RSS20FeedDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class RSS20Modifier implements Modifier<Channel, Item, RSS20FeedDescriptor> {

    private static Logger log = LoggerFactory.getLogger(RSS20Modifier.class);


    @Override
    public void modifyHstQuery(final HstRequestContext context, final HstQuery query, final RSS20FeedDescriptor descriptor) {

    }

    @Override
    public void modifyFeed(final HstRequestContext context, final Channel feed, final RSS20FeedDescriptor descriptor) {

    }

    @Override
    public void modifyEntry(final HstRequestContext context, final Item entry, final HippoBean bean) {

    }
}
