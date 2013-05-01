package org.onehippo.forge.feed.api.modifier;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.feed.api.FeedDescriptor;

/**
 * @version "$Id$"
 */
public interface Modifier<K, V, E extends FeedDescriptor> {

    public void modifyHstQuery(final HstRequestContext context, final HstQuery query, final E descriptor);

    public void modifyFeed(final HstRequestContext context, final K feed, final E descriptor);

    public void modifyEntry(final HstRequestContext context, final V entry, final HippoBean bean);

}
