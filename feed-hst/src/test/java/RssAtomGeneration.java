import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Calendar;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.WireFeedOutput;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class RssAtomGeneration {

    private static Logger log = LoggerFactory.getLogger(RssAtomGeneration.class);

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
}
