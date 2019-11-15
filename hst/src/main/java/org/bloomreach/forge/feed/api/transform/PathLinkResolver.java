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

package org.bloomreach.forge.feed.api.transform;

import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.bloomreach.forge.feed.api.annot.ContextTransformable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class PathLinkResolver {

    private static Logger log = LoggerFactory.getLogger(PathLinkResolver.class);

    @ContextTransformable
    public String getHstLink(HstRequestContext context, String path) {
        final HstLinkCreator hstLinkCreator = context.getHstLinkCreator();
        final HstLink hstChannelLink = hstLinkCreator.create(path, context.getResolvedMount().getMount());
        return hstChannelLink.toUrlForm(context, true);
    }

}
