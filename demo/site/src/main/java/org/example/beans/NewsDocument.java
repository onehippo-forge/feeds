package org.example.beans;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSetBean;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.forge.feed.api.FeedType;
import org.onehippo.forge.feed.api.annot.SyndicationElement;
import org.onehippo.forge.feed.api.annot.SyndicationRefs;
import org.onehippo.forge.feed.api.transform.CalendarToDateConverter;
import org.onehippo.forge.feed.api.transform.atom.StringToContentConverter;
import org.onehippo.forge.feed.api.transform.rss.HippoHtmlToDescriptionTransformer;

@Node(jcrType = "myhippoproject:newsdocument")
public class NewsDocument extends BaseDocument {

    @SyndicationElement(type = FeedType.RSS, name = "pubDate", converter = CalendarToDateConverter.class)
    public Calendar getDate() {
        return getProperty("myhippoproject:date");
    }

    @SyndicationElement(type = FeedType.RSS, name = "description", transformer = HippoHtmlToDescriptionTransformer.class)
    public HippoHtml getHtml() {
        return getHippoHtml("myhippoproject:body");
    }

    /**
     * Get the imageset of the newspage
     *
     * @return the imageset of the newspage
     */
    public HippoGalleryImageSetBean getImage() {
        return getLinkedBean("myhippoproject:image", HippoGalleryImageSetBean.class);
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
