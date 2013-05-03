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

package org.onehippo.forge.feed.api.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.onehippo.forge.feed.api.FeedType;
import org.onehippo.forge.feed.api.transform.Converter;
import org.onehippo.forge.feed.api.annot.noop.NoopConverter;
import org.onehippo.forge.feed.api.annot.noop.NoopTransformer;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @version "$Id$"
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface SyndicationElement {

    FeedType type();

    String name();

    Class<? extends Converter> converter() default NoopConverter.class;

    Class<?> transformer() default NoopTransformer.class;

}
