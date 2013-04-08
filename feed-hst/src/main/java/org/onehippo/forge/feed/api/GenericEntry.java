package org.onehippo.forge.feed.api;

import java.util.Calendar;

/**
 * @version "$Id$"
 */
public interface GenericEntry {

    public String getTitleAsGenericFeedEntry();

    public Object getDescriptionAsGenericFeedEntry();

    public Calendar getPublishedDateAsGenericFeedEntry();

    public Calendar getUpdatedDateAsGenericFeedEntry();

    public String[] getAuthorsAsGenericFeedEntry();

    public String[] getContributorsAsGenericFeedEntry();

    public String[] getCategoriesAsGenericFeedEntry();

    public String getSourceAsGenericFeedEntry();


}
