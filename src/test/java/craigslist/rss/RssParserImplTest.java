package craigslist.rss;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class RssParserImplTest {
  private static final String RSS_LINK =
      "https://sfbay.craigslist.org/search/pen/apa?bedrooms=1&max_price=1600&min_price=1500&format=rss";

  @Test
  @Ignore("Ignoring network calls.")
  public void testParse() throws Exception {
    RssParserImpl parser = new RssParserImpl(RSS_LINK);
    List<RssData> data = parser.parse();

    // Empty data possible.
    Assert.assertNotNull(data);
  }

  @Test
  @Ignore("Ignoring network calls.")
  public void testBadLink() throws Exception {
    RssParserImpl parser = new RssParserImpl("https://www.google.com/");
    List<RssData> data = parser.parse();

    Assert.assertNotNull(data);
    Assert.assertTrue(data.isEmpty());
  }
}
