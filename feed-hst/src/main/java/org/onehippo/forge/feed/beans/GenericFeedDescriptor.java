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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import org.apache.commons.io.IOUtils;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.onehippo.forge.feed.api.FeedDescriptor;
import org.onehippo.forge.feed.api.FeedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Node(jcrType = "feed:genericdescriptor")
public class GenericFeedDescriptor extends HippoDocument implements FeedDescriptor<SyndFeed, SyndEntry> {

    public static final Logger log = LoggerFactory.getLogger(GenericFeedDescriptor.class);


    public String getType() {
        return getProperty("feed:type");
    }

    public String getTitle() {
        return getProperty("feed:title");
    }

    public Calendar getPublicationDate() {
        return getProperty("hippostdpubwf:publicationDate");
    }

    public String[] getAuthor() {
        return getProperty("feed:author");
    }

    public String getDescription() {
        return getProperty("feed:description");
    }

    public String getLanguage() {
        return getProperty("feed:language");
    }


    public String[] getContributors() {
        return getProperty("feed:contributor");
    }

    public String getCopyright() {
        return getProperty("feed:copyright");
    }

    public HippoGalleryImageSet getImage() {
        return getLinkedBean("feed:image", HippoGalleryImageSet.class);
    }

    public String[] getCategories() {
        return getProperty("feed:categories");
    }


    @Override
    public SyndFeed createSyndication() {
        final SyndFeedImpl syndFeed = new SyndFeedImpl();
        syndFeed.setFeedType(getType());
        return syndFeed;
    }

    @Override
    public SyndEntry createEntry() {
        return new SyndEntryImpl();
    }

    @Override
    public void set(final SyndFeed syndication, final List<SyndEntry> entries) {
        syndication.setEntries(entries);
    }

    @Override
    public String process(final SyndFeed syndication) {
        Writer writer = null;
        String feed = null;
        try {
            writer = new StringWriter();
            SyndFeedOutput output = new SyndFeedOutput();
            output.output(syndication, writer);
            feed = writer.toString();
        } catch (FeedException e) {
            log.error("", e);
        } catch (IOException e) {
            log.error("", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
        return feed;
    }

    @Override
    public FeedType type() {
        return FeedType.GENERIC;
    }

    /**
     * descriptor
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
