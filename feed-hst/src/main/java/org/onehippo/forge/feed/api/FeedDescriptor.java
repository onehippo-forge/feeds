package org.onehippo.forge.feed.api;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * @version "$Id$"
 */
public interface FeedDescriptor<T> {

    public T convert();

    public String getScope();

    public String getDocumentType();

    public Long getItemCount();

    public String getSortByField();

    public String getExclude();

}
