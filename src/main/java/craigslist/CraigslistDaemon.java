package craigslist;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import craigslist.mail.Mailer;
import craigslist.rss.RssData;
import craigslist.rss.RssParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * A daemon to perform necessary action using the Rss parser and mailer.
 */
class CraigslistDaemon implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(CraigslistDaemon.class);

    private final RssParser parser;
    private final Mailer mailer;
    private final Set<String> seenLinks;

    @Inject
    CraigslistDaemon(RssParser parser, Mailer mailer) {
        this.parser = parser;
        this.mailer = mailer;
        this.seenLinks = Sets.newHashSet();
    }

    public void run() {
        logger.debug("Fetching data.");
        for (RssData data : parser.parse()) {
            if (!seenLinks.contains(data.getLink())) {
                seenLinks.add(data.getLink());
                mailer.sendMail(data.getData());
            }
        }
    }
}
