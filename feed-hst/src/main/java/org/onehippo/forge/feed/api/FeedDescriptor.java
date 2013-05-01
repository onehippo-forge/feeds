package org.onehippo.forge.feed.api;

import java.util.List;

/**
 * @version "$Id$"
 */
public interface FeedDescriptor<T, E> {

  //  public T convert();

    public T createSyndication();

    public E createEntry();

    public void set(T syndication, List<E> entries);

    public String process(T syndication);

    public FeedType type();

    public String getScope();

    public String getDocumentType();

    public Long getItemCount();

    public String getSortByField();

    public String getExclude();

}
