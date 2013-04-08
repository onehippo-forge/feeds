package org.onehippo.forge.feed.api;

import java.util.Calendar;

/**
 * @version "$Id$"
 */
public interface AtomEntry {

    public String getTitleAsAtomEntry();

    public Calendar getUpdatedAsAtomEntry();

    public String[] getAuthorsAsAtomEntry();

    public Object getContentAsAtomEntry();

    public Object getSummaryAsAtomEntry();

}
