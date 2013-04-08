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

import com.sun.syndication.feed.atom.Feed;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.onehippo.forge.feed.api.AtomFeed;
import org.onehippo.forge.feed.api.FeedDescriptor;
import org.onehippo.forge.feed.util.ConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Node(jcrType = "feed:atom10descriptor")
public class Atom10FeedDescriptor extends HippoDocument implements AtomFeed, FeedDescriptor<Feed> {

    public static final Logger log = LoggerFactory.getLogger(Atom10FeedDescriptor.class);

    /**
     * required /recommended
     */

    public String getTitle() {
        return getProperty("feed:title");
    }

    @Override
    public String[] getAuthor() {
        return getProperty("feed:author");
    }

    public String getSubtitle() {
        return getProperty("feed:subtitle");
    }

    public Calendar getPublicationDate() {
        return getProperty("hippostdpubwf:publicationDate");
    }

    /**
     * other
     */


    public String[] getCategory() {
        return getProperty("feed:category");
    }

    public String[] getContributor() {
        return getProperty("feed:contributor");
    }

    public String getGenerator() {
        return getProperty("feed:generator");
    }

    public HippoGalleryImageSet getIcon() {
        return getLinkedBean("feed:icon", HippoGalleryImageSet.class);
    }

    public HippoGalleryImageSet getLogo() {
        return getLinkedBean("feed:logo", HippoGalleryImageSet.class);
    }

    public String getRights() {
        return getProperty("feed:rights");
    }


    @Override
    public Feed convert() {
        return ConversionUtil.convertAtomDescriptorToFeed(this);
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
