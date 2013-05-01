package org.onehippo.forge.feed.api.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.xml.bind.annotation.XmlElementRef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @version "$Id$"
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface SyndicationRefs {
    SyndicationElement[] value();
}
