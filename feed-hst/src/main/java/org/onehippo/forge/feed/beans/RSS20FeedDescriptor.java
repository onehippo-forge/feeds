/*
 *  Copyright 2009-2013 Hippo B.V. (http://www.onehippo.com)
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.onehippo.forge.feed.beans;

import java.util.Calendar;

import com.sun.syndication.feed.rss.Channel;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.onehippo.forge.feed.api.FeedDescriptor;
import org.onehippo.forge.feed.api.RssChannel;
import org.onehippo.forge.feed.util.ConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Node(jcrType = "feed:rss20descriptor")
public class RSS20FeedDescriptor extends HippoDocument implements RssChannel, FeedDescriptor<Channel> {

    public static final Logger log = LoggerFactory.getLogger(RSS20FeedDescriptor.class);

    /**
     * required
     */

    public String getTitle() {
        return getProperty("feed:title");
    }

    public String getDescription() {
        return getProperty("feed:description");
    }

    public Calendar getPublicationDate() {
        return getProperty("hippostdpubwf:publicationDate");
    }

    /**
     * other
     */

    public String getLanguage() {
        return getProperty("feed:language");
    }

    public String getCopyright() {
        return getProperty("feed:copyright");
    }

    public String getManagingEditor() {
        return getProperty("feed:managingEditor");
    }

    public String getWebMaster() {
        return getProperty("feed:webMaster");
    }

    public String[] getCategory() {
        return getProperty("feed:category");
    }

    public String getGenerator() {
        return getProperty("feed:generator");
    }

    public HippoGalleryImageSet getImage() {
        return getLinkedBean("feed:image", HippoGalleryImageSet.class);
    }


    @Override
    public Channel convert() {
        return ConversionUtil.convertRssDescriptorToChannel(this);
    }

    /**
     * right side
     */


    public String getScope() {
        return getProperty("feed:scope");
    }

    public String getDocumentType() {
        return getProperty("feed:documentType");
    }

    public String getExclude() {
        return getProperty("feed:exclude");
    }

    public Long getItemCount() {
        return getProperty("feed:itemCount");
    }

    public String getSortByField() {
        return getProperty("feed:sortByField");
    }

}
