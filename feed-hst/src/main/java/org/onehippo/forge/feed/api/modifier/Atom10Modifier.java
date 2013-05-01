package org.onehippo.forge.feed.api.modifier;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.feed.beans.Atom10FeedDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class Atom10Modifier implements Modifier<Feed, Entry, Atom10FeedDescriptor> {

    private static Logger log = LoggerFactory.getLogger(Atom10Modifier.class);


    @Override
    public void modifyHstQuery(final HstRequestContext context, final HstQuery query, final Atom10FeedDescriptor descriptor) {

    }

    @Override
    public void modifyFeed(final HstRequestContext context, final Feed feed, final Atom10FeedDescriptor descriptor) {

    }

    @Override
    public void modifyEntry(final HstRequestContext context, final Entry entry, final HippoBean bean) {

    }
}
