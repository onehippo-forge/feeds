package org.onehippo.forge.feed.api.transform.rss;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.rss.Category;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class ListToRssCategoryListConverter implements Converter<List, List<Category>> {

    private static Logger log = LoggerFactory.getLogger(ListToRssCategoryListConverter.class);


    @Override
    public List<Category> convert(final List k) {
        List<Category> categories = new ArrayList<Category>();
        if (k != null) {
            for (Object category : k) {
                Category cat = new Category();
                cat.setValue((String) category);
                categories.add(cat);
            }
        }
        return categories;

    }
}
