package craigslist.rss;

import java.net.URL;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import craigslist.rss.RssModule.RssUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of a Craigslist RSS parser using ROME.
 */
public class RssParserImpl implements RssParser {
    private static Logger logger = LoggerFactory.getLogger(RssParserImpl.class);

    private final String link;

    @Inject
    RssParserImpl(@RssUrl String link) {
        this.link = link;
    }

    public List<RssData> parse() {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed results;

        try {
            results = input.build(new XmlReader(new URL(link)));
        } catch (Exception e) {
            logger.error("Exception when parsing rss feed.", e);
            // NOTE: Catching exception here since any errors are not propagated to the caller.
            return Lists.newArrayList();
        }

        ImmutableList.Builder<RssData> data = ImmutableList.builder();
        for (SyndEntry feed : results.getEntries()) {
            data.add(new RssData(feed));
        }

        return data.build();
    }
}
