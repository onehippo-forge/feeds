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

package org.bloomreach.forge.feed.api.transform.rss;



import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.rewriter.ContentRewriter;
import org.hippoecm.hst.content.rewriter.impl.SimpleContentRewriter;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.bloomreach.forge.feed.api.annot.ContextTransformable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.rss.Description;


public class HippoHtmlToDescriptionTransformer {

    private static final Logger log = LoggerFactory.getLogger(HippoHtmlToDescriptionTransformer.class);

    @ContextTransformable
    public Description transform(HippoHtml html, AbstractResource resource, HstRequestContext context) {
        Description description = new Description();
        ContentRewriter<String> contentRewriter = resource.getContentRewriter();
        if (contentRewriter == null) {
            contentRewriter = new SimpleContentRewriter();
            contentRewriter.setFullyQualifiedLinks(true);
        }
        if (html != null) {
            String rewrittenHtml = contentRewriter.rewrite(html.getContent(), html.getNode(), context);
            description.setValue(rewrittenHtml);
        }
        return description;

    }


}
