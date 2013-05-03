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
