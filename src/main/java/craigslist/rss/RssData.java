package craigslist.rss;

import com.google.common.base.MoreObjects;
import com.rometools.rome.feed.synd.SyndEntry;

public class RssData {
  private final String link;
  private final String title;
  private final String description;

  public RssData(SyndEntry entry) {
    this.link = entry.getLink();
    this.title = entry.getTitle();
    this.description = entry.getDescription().getValue();
  }

  public String getLink() {
    return link;
  }

  public String getData() {
    return "link: " + link
            + "\n title: " + title
            + "\n description: " + description;
  }
}
