package org.onehippo.forge.feed.api.annot.noop;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class NoopConverter implements Converter {

    private static Logger log = LoggerFactory.getLogger(NoopConverter.class);

    @Override
    public Object convert(final Object o) {
        return o;
    }
}
