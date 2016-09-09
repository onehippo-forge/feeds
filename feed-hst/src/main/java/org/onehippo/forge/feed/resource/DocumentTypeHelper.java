/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
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
package org.onehippo.forge.feed.resource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This this implements functionality to exclude a subDocumentTypes from a set of documentTypes.
 * If the list of {@link #excludedDocTypes}
 * contains a subType of one of the {@link #includedDocTypes} the includedDocumentType is replace by its subTypes without the documentType
 * to be excluded.
 *
 */

class DocumentTypeHelper {

    private final Set<DocType> includedDocTypes;
    private final Set<String> excludedDocTypes;
    private final Set<String> docTypes = new HashSet<>();

    public DocumentTypeHelper(final Set<DocType> includedDocTypes, final Set<String> excludedDocTypes) {
        this.includedDocTypes = includedDocTypes;
        this.excludedDocTypes = excludedDocTypes;
    }

    public static String[] getDocTypes(final Set<DocType> docTypes, final Set<String> excludedElements) {
        DocumentTypeHelper helper = new DocumentTypeHelper(
                docTypes, excludedElements);
        helper.process();
        return helper.getDocTypes().toArray(new String[]{});
    }


    public void process() {
        for (DocType docType : includedDocTypes) {
            Set<String> add = hasExcludedSubTypes(docType) ? subTypesWithoutExcludedTypes(docType) :
                    asSet(docType);
            docTypes.addAll(add);
        }
    }

    private boolean hasExcludedSubTypes(final DocType docType) {
        Set<String> intersection = new HashSet<>();
        intersection.addAll(docType.getSubTypes());
        intersection.retainAll(excludedDocTypes);
        return (intersection.size() > 0);
    }

    private Set<String> asSet(DocType docType) {
        return new HashSet<>(Arrays.asList(new String[]{docType.getDocType()}));
    }

    private Set<String> subTypesWithoutExcludedTypes(final DocType docType) {
        Set<String> result = new HashSet<>();
        result.addAll(docType.getSubTypes());
        result.removeAll(excludedDocTypes);
        return result;
    }


    public Set<String> getDocTypes() {
        return docTypes;
    }

}
