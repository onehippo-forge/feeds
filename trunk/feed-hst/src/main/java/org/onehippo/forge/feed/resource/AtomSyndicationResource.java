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
package org.onehippo.forge.feed.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

/**
 * @version "$Id: RssFeedResource.java 9 2013-04-08 08:29:25Z ksalic $"
 */
@Produces({MediaType.APPLICATION_XML})
@Path("/feed:atom10descriptor/")
public class AtomSyndicationResource extends AbstractSyndicationResource<Feed, Entry> {


}
