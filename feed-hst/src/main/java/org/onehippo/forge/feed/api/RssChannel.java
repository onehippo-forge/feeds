package org.onehippo.forge.feed.api;

import java.util.Calendar;

/**
 * @version "$Id$"
 */
public interface RssChannel {

    public String getTitle();
    public String getDescription();
    public Calendar getPublicationDate();

}
