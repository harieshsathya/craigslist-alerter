package craigslist.rss;

import java.util.List;

/**
 * An interface to parse an RSS feed and convert it into an appropriate data object.
 */
public interface RssParser {

  /**
   * Parse a provided RSS link and perform necessary action on the data. The implementation handles any unknown
   * parse exceptions and bad data and returns an empty list.
   *
   * @return A list of parsed data objects.
   */
   List<RssData> parse();
}
