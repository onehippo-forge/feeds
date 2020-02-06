/*
 * Copyright 2013-2020 Hippo B.V. (http://www.onehippo.com)
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

package org.bloomreach.forge.feed.api.modifier;


import org.bloomreach.forge.feed.beans.GenericFeedDescriptor;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;


public class GenericModifier implements Modifier<SyndFeed, SyndEntry, GenericFeedDescriptor> {


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
