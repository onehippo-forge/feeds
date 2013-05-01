package org.onehippo.forge.feed.api.transform.atom;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.atom.Person;

import org.onehippo.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class AuthorListToPersonListConverter implements Converter<List<String>, List<Person>> {

    private static Logger log = LoggerFactory.getLogger(AuthorListToPersonListConverter.class);


    @Override
    public List<Person> convert(final List<String> authors) {
        List<Person> categories = new ArrayList<Person>();
        if (authors != null) {
            for (String author : authors) {
                Person person = new Person();
                person.setName(author);
                categories.add(person);
            }
        }
        return categories;
    }
}
