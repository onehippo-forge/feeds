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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;

import org.apache.commons.io.IOUtils;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.onehippo.forge.feed.api.FeedDescriptor;
import org.onehippo.forge.feed.api.FeedType;
import org.onehippo.forge.feed.api.annot.SyndicationElement;
import org.onehippo.forge.feed.api.transform.atom.DocumentAtomLinkResolver;
import org.onehippo.forge.feed.api.transform.atom.ListToAtomCategoryListConverter;
import org.onehippo.forge.feed.api.transform.atom.AuthorListToPersonListConverter;
import org.onehippo.forge.feed.api.transform.CalendarToDateConverter;
import org.onehippo.forge.feed.api.transform.DocumentLinkResolver;
import org.onehippo.forge.feed.api.transform.PathLinkResolver;
import org.onehippo.forge.feed.api.transform.atom.StringToContentConverter;
import org.onehippo.forge.feed.api.transform.atom.StringToGeneratorConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Node(jcrType = "feed:atom10descriptor")
public class Atom10FeedDescriptor extends HippoDocument implements FeedDescriptor<Feed, Entry> {

    public static final Logger log = LoggerFactory.getLogger(Atom10FeedDescriptor.class);

    /**
     * required /recommended
     */

    @SyndicationElement(type = FeedType.ATOM, name = "title", converter = StringToContentConverter.class)
    public String getTitle() {
        return getProperty("feed:title");
    }

    @SyndicationElement(type = FeedType.ATOM, name = "authors", converter = AuthorListToPersonListConverter.class)
    public List<String> getAuthor() {
        String[] authorArray = getProperty("feed:author");
        return Arrays.asList(authorArray);
    }

    @SyndicationElement(type = FeedType.ATOM, name = "subtitle", converter = StringToContentConverter.class)
    public String getSubtitle() {
        return getProperty("feed:subtitle");
    }

    @SyndicationElement(type = FeedType.ATOM, name = "updated", converter = CalendarToDateConverter.class)
    public Calendar getPublicationDate() {
        return getProperty("hippostdpubwf:publicationDate");
    }

    @SyndicationElement(type = FeedType.ATOM, name = "id", transformer = PathLinkResolver.class)
    public String getId() {
        return "/";
    }

    @SyndicationElement(type = FeedType.ATOM, name = "alternateLinks", transformer = DocumentAtomLinkResolver.class)
    public HippoDocument getLink() {
        return this;
    }

    /**
     * other
     */

    @SyndicationElement(type = FeedType.ATOM, name = "categories", converter = ListToAtomCategoryListConverter.class)
    public List<String> getCategory() {
        String[] categoryArray = getProperty("feed:category");
        return Arrays.asList(categoryArray);
    }

    @SyndicationElement(type = FeedType.ATOM, name = "contributors", converter = AuthorListToPersonListConverter.class)
    public List<String> getContributor() {
        String[] contributorArray = getProperty("feed:contributor");
        return Arrays.asList(contributorArray);
    }

    @SyndicationElement(type = FeedType.ATOM, name = "generator", converter = StringToGeneratorConverter.class)
    public String getGenerator() {
        return getProperty("feed:generator");
    }

    @SyndicationElement(type = FeedType.ATOM, name = "icon", transformer = DocumentLinkResolver.class)
    public HippoGalleryImageSet getIcon() {
        return getLinkedBean("feed:icon", HippoGalleryImageSet.class);
    }

    @SyndicationElement(type = FeedType.ATOM, name = "logo", transformer = DocumentLinkResolver.class)
    public HippoGalleryImageSet getLogo() {
        return getLinkedBean("feed:logo", HippoGalleryImageSet.class);
    }

    @SyndicationElement(type = FeedType.ATOM, name = "rights")
    public String getRights() {
        return getProperty("feed:rights");
    }


    @Override
    public Feed createSyndication() {
        return new Feed("atom_1.0");
    }

    @Override
    public Entry createEntry() {
        return new Entry();
    }


    @Override
    public void set(final Feed syndication, final List entries) {
        syndication.setEntries(entries);
    }

    @Override
    public String process(final Feed syndication) {
        Writer writer = null;
        String feed = null;
        try {
            writer = new StringWriter();
            WireFeedOutput output = new WireFeedOutput();
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
        return FeedType.ATOM;
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
