/*
 * Copyright 2013-2020 Hippo B.V. (http://www.onehippo.com)
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

import org.bloomreach.forge.feed.api.transform.Converter;

import com.rometools.rome.feed.rss.Category;

public class ListToRssCategoryListConverter implements Converter<List, List<Category>> {

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
