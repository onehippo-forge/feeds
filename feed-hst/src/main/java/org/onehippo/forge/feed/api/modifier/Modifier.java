package org.onehippo.forge.feed.api.modifier;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.feed.api.FeedDescriptor;

/**
 * @version "$Id$"
 */
public interface Modifier<K, V, E extends FeedDescriptor> {

    /**
     * Modify the HST Query, set implementation in override spring xml
     * e.g. for RSS:
     * <bean id="rssModifier" class="org.onehippo.forge.feed.api.modifier.RSS20Modifier" scope="singleton"/>
     * @param context
     * @param query
     * @param descriptor
     */
    public void modifyHstQuery(final HstRequestContext context, final HstQuery query, final E descriptor);

    /**
     * Modify the Syndication Feed
     * e.g. for RSS
     * <bean id="rssModifier" class="org.onehippo.forge.feed.api.modifier.RSS20Modifier" scope="singleton"/>
     * @param context
     * @param feed
     * @param descriptor
     */
    public void modifyFeed(final HstRequestContext context, final K feed, final E descriptor);

    /**
     * Modify the Syndication Entry
     * e.g. for RSS
     * <bean id="rssModifier" class="org.onehippo.forge.feed.api.modifier.RSS20Modifier" scope="singleton"/>
     * @param context
     * @param entry
     * @param bean
     */
    public void modifyEntry(final HstRequestContext context, final V entry, final HippoBean bean);

}
