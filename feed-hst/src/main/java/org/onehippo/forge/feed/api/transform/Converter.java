package org.onehippo.forge.feed.api.transform;

/**
 * @version "$Id$"
 */
public interface Converter<V, K> {

    public K convert(V k);

}
