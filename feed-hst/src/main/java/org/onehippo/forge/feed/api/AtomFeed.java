package org.onehippo.forge.feed.api;

import java.util.Calendar;

/**
 * @version "$Id$"
 */
public interface AtomFeed {

    public String getTitle();
    public String[] getAuthor();
    public Calendar getPublicationDate();
}
