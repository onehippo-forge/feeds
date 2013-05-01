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
