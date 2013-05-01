package org.onehippo.forge.feed.api.transform.rss;

import com.sun.syndication.feed.rss.Description;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class StringToDescriptionConverter implements Converter<String, Description> {

    private static Logger log = LoggerFactory.getLogger(StringToDescriptionConverter.class);

    @Override
    public Description convert(final String k) {
        Description description = new Description();
        description.setValue(k);
        return description;
    }
}
