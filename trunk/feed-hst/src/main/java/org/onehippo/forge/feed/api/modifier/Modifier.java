/*
 * Copyright 2013 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
