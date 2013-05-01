package org.onehippo.forge.feed.api.transform;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class CalendarToDateConverter implements Converter<Calendar, Date> {

    private static Logger log = LoggerFactory.getLogger(CalendarToDateConverter.class);

    @Override
    public Date convert(final Calendar k) {
        return k.getTime();
    }
}
