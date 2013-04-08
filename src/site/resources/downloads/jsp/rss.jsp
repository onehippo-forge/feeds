<%--
  Copyright 2009-2013 Hippo B.V. (http://www.onehippo.com)

  Licensed under the Apache License, Version 2.0 (the  "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS"
  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. 
--%>
<%@ page pageEncoding="UTF-8" contentType="application/rss+xml; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://www.hippoecm.org/jsp/hst/core" prefix='hst'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US"/>
<rss version="2.0">
   <channel>
      <title>${feed.title}</title>
      <link>http://www.example.com/</link>
      <description>${feed.description}</description>
      <language>${feed.language}</language>
      <pubDate><fmt:formatDate value="${feed.pubDate.time}" pattern="EE, dd MMM yyyy HH:mm:ss Z"/></pubDate>
      <lastBuildDate><fmt:formatDate value="${feed.lastBuildDate.time}" pattern="EE, dd MMM yyyy HH:mm:ss Z"/></lastBuildDate>
      <docs>http://blogs.law.harvard.edu/tech/rss</docs>
      <generator>Hippo CMS</generator>
      <managingEditor>${feed.managingEditor}</managingEditor>
      <webMaster>${feed.webMaster}</webMaster>
      <c:forEach var="item" items="${feed.items}">
      <hst:link var="link" hippobean="${item}" external="true"/>
         <item>
			<%-- Please use c:out to escape HTML characters. Otherwise the rss feed will most certainly break 
			if a HTML character is used in a title or a link. --%>         
            <title><c:out value='${item.title}' /></title>
            <link><c:out value='${link}' /></link>
            <description><![CDATA[<hst:html hippohtml="${item.html}"/>]]></description>
            <pubDate><fmt:formatDate value="${item.date.time}" pattern="EE, dd MMM yyyy HH:mm:ss Z"/></pubDate>
            <guid><c:out value='${link}' /></guid>
         </item>
      </c:forEach>
   </channel>
</rss>