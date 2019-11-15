/*
 * Copyright 2013-2014 Hippo B.V. (http://www.onehippo.com)
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

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.WireFeedOutput;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.bloomreach.forge.feed.api.FeedType;
import org.bloomreach.forge.feed.api.annot.ContextTransformable;
import org.bloomreach.forge.feed.api.annot.SyndicationElement;
import org.bloomreach.forge.feed.api.annot.SyndicationRefs;
import org.bloomreach.forge.feed.api.annot.noop.NoopConverter;
import org.bloomreach.forge.feed.api.annot.noop.NoopTransformer;
import org.bloomreach.forge.feed.api.transform.CalendarToDateTransformer;
import org.bloomreach.forge.feed.api.transform.Converter;
import org.bloomreach.forge.feed.api.transform.atom.StringToContentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class RssAtomGenerationTest {

    private static Logger log = LoggerFactory.getLogger(RssAtomGenerationTest.class);

    @Test
    public void testRssChannelGeneration() throws Exception {

        Channel channel = new Channel("rss_2.0");
        channel.setTitle("test");
        channel.setDescription("description");
        channel.setPubDate(Calendar.getInstance().getTime());
        channel.setLink("http://www.google.com");

        channel.setLanguage("nl");
        channel.setManagingEditor("managingeditor");
        channel.setWebMaster("webmaster");
        //channel.setCategories(Arrays.asList(new String[]{"test1, test2"}));
        channel.setGenerator("generator");
        channel.setCopyright("copyright");

        Writer writer = new StringWriter();
        WireFeedOutput output = new WireFeedOutput();
        output.output(channel, writer);
        System.out.println(writer.toString());

        final SyndFeedImpl syndFeed = new SyndFeedImpl(channel, true);

        Writer writer1 = new StringWriter();
        SyndFeedOutput syndFeedOutput = new SyndFeedOutput();
        syndFeedOutput.output(syndFeed, writer1);

        System.out.println(writer1.toString());

    }


    @Test
    public void testReflectionUtils() throws Exception {
        TestBean bean = new TestBean();
        final Channel channel = convertObject(new Channel(), bean, FeedType.RSS, new Integer(23324324));
        final Feed feed = convertObject(new Feed(), bean, FeedType.ATOM, new Integer(23324324));
        final String title = channel.getTitle();

    }

//    public static <T> T convertObject(final T destination, final Object source, FeedType type, Object... context) {
//        Map<Class, Object> contentMap = new HashMap<Class, Object>();
//        for (Object o : context) {
//            contentMap.put(o.getClass(), o);
//        }
//        final Method[] methods = source.getClass().getMethods();
//        for (Method method : methods) {
//            if (method.isAnnotationPresent(SyndicationElement.class) && method.getAnnotation(SyndicationElement.class).type().equals(type)) {
//                final SyndicationElement annotation = method.getAnnotation(SyndicationElement.class);
//                final String name = annotation.name();
//                try {
//                    final Object initValue = method.invoke(source);
//                    Object value = null;
//                    if (!(annotation.converter().isAssignableFrom(NoopConverter.class))) {
//                        final Converter converter = annotation.converter().newInstance();
//                        value = converter.convert(initValue);
//                    }
//                    if (!(annotation.transformer().isAssignableFrom(NoopTransformer.class))) {
//                        contentMap.put(initValue.getClass(), initValue);
//                        final Class<?> transformer = annotation.transformer();
//                        final Method[] transformerMethods = transformer.getMethods();
//                        for (Method transformerMethod : transformerMethods) {
//                            if (transformerMethod.isAnnotationPresent(ContextTransformable.class)) {
//                                final Class<?>[] parameterTypes = transformerMethod.getParameterTypes();
//                                List<Object> parameters = new ArrayList<Object>();
//                                for (Class clazz : parameterTypes) {
//                                    if (contentMap.containsKey(clazz)) {
//                                        parameters.add(contentMap.get(clazz));
//                                    } else {
//                                        parameters.add(null);
//                                    }
//                                }
//                                final Object tranformInstance = transformer.newInstance();
//                                value = transformerMethod.invoke(tranformInstance, parameters.toArray());
//                            }
//                        }
//
//                    }
//                    BeanUtils.setProperty(destination, name, value);
//                } catch (Exception e) {
//                    log.error("test", e);
//                    e.printStackTrace();
//                /* catch (InvocationTargetException e) {
//                    log.error("", e);
//                } catch (InstantiationException e) {
//                    log.error("", e);
//                } catch (IllegalAccessException e) {
//                    log.error("", e);
//                }*/
//                }
//
//            }
//        }
//        return destination;
//    }

    public class TestBean {

        @SyndicationRefs({
                @SyndicationElement(type = FeedType.RSS, name = "title"),
                @SyndicationElement(type = FeedType.ATOM, name = "title", converter = StringToContentConverter.class)
        })
        public String getTitle() {
            return "testtitle";
        }

//        @SyndicationElement(type = FeedType.RSS, name = "description")
//        public String getDescription() {
//            return "testdescription";
//        }


        @SyndicationElement(type = FeedType.RSS, name = "pubDate", transformer = CalendarToDateTransformer.class)
        public Calendar getPublicationDate() {
            return Calendar.getInstance();
        }

    }

    public static <T> T convertObject(final T destination, final Object source, FeedType type, Object... context) {
        Map<Class, Object> contentMap = new HashMap<Class, Object>();
        for (Object o : context) {
            contentMap.put(o.getClass(), o);
        }
        final Method[] methods = source.getClass().getMethods();
        for (Method method : methods) {
            final boolean refPresent = method.isAnnotationPresent(SyndicationRefs.class);
            if (refPresent || (method.isAnnotationPresent(SyndicationElement.class) && method.getAnnotation(SyndicationElement.class).type().equals(type))) {
                SyndicationElement annotation = null;
                if (refPresent) {
                    final SyndicationElement[] value = method.getAnnotation(SyndicationRefs.class).value();
                    for (SyndicationElement element : value) {
                        if (element.type().equals(type)) {
                            annotation = element;
                            break;
                        }
                    }
                    if (annotation == null) {
                        continue;
                    }
                } else {
                    annotation = method.getAnnotation(SyndicationElement.class);
                }
                //final SyndicationElement annotation =
                final String name = annotation.name();
                try {
                    final Object initValue = method.invoke(source);
                    Object value = null;
                    if (!(annotation.converter().isAssignableFrom(NoopConverter.class))) {
                        final Converter converter = annotation.converter().newInstance();
                        value = converter.convert(initValue);
                    }
                    if (!(annotation.transformer().isAssignableFrom(NoopTransformer.class))) {
                        contentMap.put(initValue.getClass(), initValue);
                        final Class<?> transformer = annotation.transformer();
                        final Method[] transformerMethods = transformer.getMethods();
                        for (Method transformerMethod : transformerMethods) {
                            if (transformerMethod.isAnnotationPresent(ContextTransformable.class)) {
                                final Class<?>[] parameterTypes = transformerMethod.getParameterTypes();
                                List<Object> parameters = new ArrayList<Object>();
                                for (Class clazz : parameterTypes) {
                                    if (contentMap.containsKey(clazz)) {
                                        parameters.add(contentMap.get(clazz));
                                    } else {
                                        boolean found = false;
                                        for (Object obj : contentMap.values()) {
                                            if (clazz.isInstance(obj)) {
                                                parameters.add(obj);
                                                found = true;
                                                break;
                                            }
                                        }
                                        if (!found) {
                                            parameters.add(null);
                                        }
                                    }
                                }
                                if (Modifier.isStatic(transformerMethod.getModifiers())) {
                                    value = transformerMethod.invoke(null, parameters.toArray());
                                } else {
                                    value = transformerMethod.invoke(transformer.newInstance(), parameters.toArray());
                                }
                                break;
                            }
                        }

                    }
                    BeanUtils.setProperty(destination, name, value);
                } catch (Exception e) {
                    log.error("test", e);
                    e.printStackTrace();
                }

            }
        }
        return destination;
    }


    public static List<Method> getSyndicationElementsOfType(Class clazz, FeedType type) {
        List<Method> methodList = new ArrayList<Method>();
        final Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(SyndicationElement.class) && method.getAnnotation(SyndicationElement.class).type().equals(type)) {
                methodList.add(method);
            }
        }
        return methodList;
    }
//    public class TestTransformer {
//
//        @ContextTransformable
//        public String getDesriptionFromString(Integer s) {
//            return String.valueOf(s);
//        }
//
//    }
}
