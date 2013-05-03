package org.onehippo.forge.feed.api.transform.gen;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.rss.Category;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class ListToSyndCategoryListConverter implements Converter<List<String>, List<SyndCategory>> {

    private static Logger log = LoggerFactory.getLogger(ListToSyndCategoryListConverter.class);


    @Override
    public List<SyndCategory> convert(final List<String> k) {
        List<SyndCategory> categories = new ArrayList<SyndCategory>();
        if (k != null) {
            for (String category : k) {
                SyndCategory cat = new SyndCategoryImpl();
                cat.setName(category);
                categories.add(cat);
            }
        }
        return categories;

    }
}
