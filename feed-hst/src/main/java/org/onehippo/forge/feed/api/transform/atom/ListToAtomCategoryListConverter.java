package org.onehippo.forge.feed.api.transform.atom;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.atom.Category;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class ListToAtomCategoryListConverter implements Converter<List<String>, List<Category>> {

    private static Logger log = LoggerFactory.getLogger(ListToAtomCategoryListConverter.class);


    @Override
    public List<Category> convert(final List<String> k) {
        List<Category> categories = new ArrayList<Category>();
        if (k != null) {
            for (String category : k) {
                Category cat = new Category();
                cat.setTerm(category);
                cat.setLabel(category);
                categories.add(cat);
            }
        }
        return categories;

    }
}
