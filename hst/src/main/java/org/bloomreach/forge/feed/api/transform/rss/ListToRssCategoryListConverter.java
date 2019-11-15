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

package org.bloomreach.forge.feed.api.transform.rss;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.rss.Category;

import org.bloomreach.forge.feed.api.transform.Converter;
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
