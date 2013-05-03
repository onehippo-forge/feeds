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
