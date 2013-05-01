package org.onehippo.forge.feed.api.transform.atom;

import com.sun.syndication.feed.atom.Generator;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class StringToGeneratorConverter implements Converter<String, Generator> {

    private static Logger log = LoggerFactory.getLogger(StringToGeneratorConverter.class);

    @Override
    public Generator convert(final String k) {
        Generator generator = new Generator();
        generator.setValue(k);
        return generator;
    }
}
