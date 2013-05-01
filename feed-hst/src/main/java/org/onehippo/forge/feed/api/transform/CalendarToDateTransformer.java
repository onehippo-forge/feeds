package org.onehippo.forge.feed.api.transform;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.onehippo.forge.feed.api.annot.ContextTransformable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class CalendarToDateTransformer  {

    private static Logger log = LoggerFactory.getLogger(CalendarToDateTransformer.class);

    @ContextTransformable
    public static Date convert(final Calendar k) {
        return k.getTime();
    }
}
