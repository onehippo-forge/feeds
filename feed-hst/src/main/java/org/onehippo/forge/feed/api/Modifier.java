package org.onehippo.forge.feed.api;

import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;

/**
 * @version "$Id$"
 */
public interface Modifier<K, V, E extends FeedDescriptor> {

    public void modifyHstQuery(HstQuery query, E descriptor);

    public void modifyFeed(K feed, E descriptor);

    public void modifyEntry(V entry, HippoBean bean);

}
