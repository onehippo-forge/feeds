package org.onehippo.forge.feed.api;

import java.util.List;

/**
 * @version "$Id$"
 */
public interface FeedDescriptor<T, E> {

    /**
     * Create top level element
     * @return
     */
    public T createSyndication();

    /**
     * Create entry element to include in top level element
     * @return
     */
    public E createEntry();

    /**
     * Add entry list to the syndication
     * @param syndication
     * @param entries
     */
    public void set(T syndication, List<E> entries);

    /**
     * Process syndication to XML format
     * @param syndication
     * @return
     */
    public String process(T syndication);

    /***
     * FeedType
     * @return
     */
    public FeedType type();

    /**
     * Right side elements
     */

    public String getScope();

    public String getDocumentType();

    public Long getItemCount();

    public String getSortByField();

    public String getExclude();

}
