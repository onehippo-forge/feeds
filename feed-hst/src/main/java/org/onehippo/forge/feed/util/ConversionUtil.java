package org.onehippo.forge.feed.util;

import java.util.Calendar;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

import org.onehippo.forge.feed.api.AtomEntry;
import org.onehippo.forge.feed.api.GenericEntry;
import org.onehippo.forge.feed.api.RssItem;
import org.onehippo.forge.feed.beans.Atom10FeedDescriptor;
import org.onehippo.forge.feed.beans.GenericFeedDescriptor;
import org.onehippo.forge.feed.beans.RSS20FeedDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class ConversionUtil {

    private static Logger log = LoggerFactory.getLogger(ConversionUtil.class);

    public static SyndFeed convertGenericDescriptorToSyndFeed(final GenericFeedDescriptor descriptor) {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType(descriptor.getType());
        feed.setTitle(descriptor.getTitle());
        feed.setDescription(descriptor.getDescription());
       // feed.setAuthors(Arrays.asList(descriptor.getAuthorsAsAtomEntry()));
        feed.setPublishedDate(descriptor.getPublicationDate().getTime());
        feed.setLanguage(descriptor.getLanguage());
        //feed.setContributors(Arrays.asList(descriptor.getContributors()));
        feed.setCopyright(descriptor.getCopyright());
       // feed.setCategories(Arrays.asList(descriptor.getCategories()));
        return feed;
    }

    public static Channel convertRssDescriptorToChannel(final RSS20FeedDescriptor descriptor) {
        Channel channel = new Channel("rss_2.0");
        channel.setTitle(descriptor.getTitle());
        channel.setDescription(descriptor.getDescription());
        channel.setPubDate(descriptor.getPublicationDate().getTime());

        channel.setLanguage(descriptor.getLanguage());
        channel.setManagingEditor(descriptor.getManagingEditor());
        channel.setWebMaster(descriptor.getWebMaster());
//        channel.setCategories(Arrays.asList(descriptor.getCategoriesAsGenericFeedEntry()));    todo
        channel.setGenerator(descriptor.getGenerator());
        channel.setCopyright(descriptor.getCopyright());

        return channel;
    }

    public static Feed convertAtomDescriptorToFeed(final Atom10FeedDescriptor descriptor) {
        Feed feed = new Feed("atom_1.0");
        feed.setTitle(descriptor.getTitle());
       // feed.setAuthors(Arrays.asList(descriptor.getAuthorsAsAtomEntry()));
        Content content = new Content();
        content.setValue(descriptor.getSubtitle());
        feed.setSubtitle(content);
        final Calendar publicationDate = descriptor.getPublicationDate();
        if (publicationDate != null) {
            feed.setUpdated(publicationDate.getTime());
        }
        //feed.setCategories();   todo
        //feed.setContributors(descriptor.getcont);      todo
        //feed.setGenerator();                   todo
        feed.setRights(descriptor.getRights());
        //image + icon
        return feed;
    }

    public static SyndEntry convertGenericEntryToSyndEntry(final GenericEntry entry) {
        SyndEntry syndEntry = new SyndEntryImpl();
        syndEntry.setTitle(entry.getTitleAsGenericFeedEntry());
        final Calendar publishedDate = entry.getPublishedDateAsGenericFeedEntry();
        if (publishedDate != null) {
            syndEntry.setPublishedDate(publishedDate.getTime());
        }
        final Calendar updatedDate = entry.getUpdatedDateAsGenericFeedEntry();
        if (updatedDate != null) {
            syndEntry.setUpdatedDate(updatedDate.getTime());
        }
        //syndEntry.setAuthors(Arrays.asList(entry.getAuthorsAsAtomEntry()));
        //syndEntry.setContributors(Arrays.asList(entry.getContributorsAsGenericFeedEntry()));
        //syndEntry.setCategories(Arrays.asList(entry.getCategoriesAsGenericFeedEntry()));
        return syndEntry;
    }

    public static Item covertRssItemToItem(final RssItem rssItem) {
        Item item = new Item();
        item.setTitle(rssItem.getTitleAsRssItem());
        final Calendar publicationDate = rssItem.getPublicationDateAsRssItem();
        if (publicationDate != null) {
            item.setPubDate(publicationDate.getTime());
        }
        return item;
    }

    public static Entry covertAtomEntryToEntry(final AtomEntry atomEntry) {
        Entry entry = new Entry();
        entry.setTitle(atomEntry.getTitleAsAtomEntry());
        entry.setUpdated(atomEntry.getUpdatedAsAtomEntry().getTime());
        return entry;
    }

}
