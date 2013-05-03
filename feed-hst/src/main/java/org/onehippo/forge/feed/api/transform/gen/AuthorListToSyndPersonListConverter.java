package org.onehippo.forge.feed.api.transform.gen;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.feed.synd.SyndPersonImpl;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class AuthorListToSyndPersonListConverter implements Converter<List<String>, List<SyndPerson>> {

    private static Logger log = LoggerFactory.getLogger(AuthorListToSyndPersonListConverter.class);


    @Override
    public List<SyndPerson> convert(final List<String> authors) {
        List<SyndPerson> categories = new ArrayList<SyndPerson>();
        if (authors != null) {
            for (String author : authors) {
                SyndPerson person = new SyndPersonImpl();
                person.setName(author);
                categories.add(person);
            }
        }
        return categories;
    }
}
