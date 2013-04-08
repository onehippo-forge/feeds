package org.onehippo.forge.feed.api;

import java.util.Calendar;

/**
 * @version "$Id$"
 */
public interface AtomEntry {

    public String getTitle();

    public Calendar getUpdated();

    public String[] getAuthor();

    public Object getContent();

    public Object getSummary();

}
