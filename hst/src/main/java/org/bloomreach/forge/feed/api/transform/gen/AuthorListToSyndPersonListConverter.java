/*
 * Copyright 2013 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bloomreach.forge.feed.api.transform.gen;

import java.util.ArrayList;
import java.util.List;


import org.bloomreach.forge.feed.api.transform.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.feed.synd.SyndPersonImpl;


public class AuthorListToSyndPersonListConverter implements Converter<List<String>, List<SyndPerson>> {


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
