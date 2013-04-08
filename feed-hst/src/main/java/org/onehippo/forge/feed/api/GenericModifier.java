package org.onehippo.forge.feed.api;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.onehippo.forge.feed.beans.Atom10FeedDescriptor;
import org.onehippo.forge.feed.beans.GenericFeedDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class GenericModifier implements Modifier<SyndFeed, SyndEntry, GenericFeedDescriptor> {

    private static Logger log = LoggerFactory.getLogger(GenericModifier.class);


    @Override
    public void modifyHstQuery(final HstQuery query, final GenericFeedDescriptor descriptor) {

    }

    @Override
    public void modifyFeed(final SyndFeed feed, final GenericFeedDescriptor descriptor) {

    }

    @Override
    public void modifyEntry(final SyndEntry entry, final HippoBean bean) {

    }
}
