package org.example.beans;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;

import org.bloomreach.forge.feed.api.FeedType;
import org.bloomreach.forge.feed.api.annot.SyndicationElement;
import org.bloomreach.forge.feed.api.annot.SyndicationRefs;
import org.bloomreach.forge.feed.api.transform.CalendarToDateConverter;
import org.bloomreach.forge.feed.api.transform.atom.StringToContentConverter;
import org.bloomreach.forge.feed.api.transform.rss.HippoHtmlToDescriptionTransformer;

@Node(jcrType="myhippoproject:textdocument")
public class TextDocument extends BaseDocument{

    @SyndicationElement(type = FeedType.RSS, name = "pubDate", converter = CalendarToDateConverter.class)
    public Calendar getDate() {
        return getProperty("hippostdpubwf:publicationDate");
    }

    @SyndicationElement(type = FeedType.RSS, name = "description", transformer = HippoHtmlToDescriptionTransformer.class)
    public HippoHtml getHtml(){
        return getHippoHtml("myhippoproject:body");
    }

    public String getSummary() {
        return getProperty("myhippoproject:summary");
    }

    @SyndicationRefs({
        @SyndicationElement(type = FeedType.RSS, name = "title"),
        @SyndicationElement(type = FeedType.ATOM, name = "title", converter = StringToContentConverter.class)
    })
    public String getTitle() {
        return getProperty("myhippoproject:title");
    }

}
