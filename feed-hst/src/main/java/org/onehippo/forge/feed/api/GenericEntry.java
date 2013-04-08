package org.onehippo.forge.feed.api;

import java.util.Calendar;

/**
 * @version "$Id$"
 */
public interface GenericEntry {

    public String getTitle();

    public Object getDescription();

    public Calendar getPublishedDate();

    public Calendar getUpdatedDate();

    public String[] getAuthor();

    public String[] getContributor();

    public String[] getCategory();

    public String getSource();


}
