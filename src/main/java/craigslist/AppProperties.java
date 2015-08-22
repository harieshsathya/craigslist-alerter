package craigslist;

/**
 * Container class to hold application properties.
 */
public class AppProperties {
    public String feedUrl;
    public int parseInterval;
    public int bedrooms;
    public int bathrooms;
    public int minPrice;
    public int maxPrice;
    public boolean isRentControlled;

    public String toEmail;
    public String smtpUsername;
    public String smtpPassword;
    public String smtpServer;
    public String smtpServerPort;

    public String getFeedUrl() {
        return feedUrl;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getSmtpUsername() {
        return smtpUsername;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public String getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public boolean isRentControlled() {
        return isRentControlled;
    }

    public int getParseInterval() {
        return parseInterval;
    }
}


