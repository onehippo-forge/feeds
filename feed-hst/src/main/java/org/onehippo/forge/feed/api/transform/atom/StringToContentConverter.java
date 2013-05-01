package org.onehippo.forge.feed.api.transform.atom;

import com.sun.syndication.feed.atom.Content;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class StringToContentConverter implements Converter<String, Content> {

    private static Logger log = LoggerFactory.getLogger(StringToContentConverter.class);

    @Override
    public Content convert(final String k) {
        Content content = new Content();
        content.setValue(k);
        //content.setType(Content.TEXT);
        return content;
    }
}
