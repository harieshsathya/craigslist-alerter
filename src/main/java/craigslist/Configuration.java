package craigslist;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.google.common.base.Throwables;
import craigslist.mail.SmtpSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Handles application related configuration.
 */
public class Configuration {
    private final AppProperties properties;

    Configuration(String propertiesFilePath) {
        try {
            YamlReader reader = new YamlReader(new FileReader(propertiesFilePath));
            this.properties = reader.read(AppProperties.class);
        } catch (FileNotFoundException e) {
            throw Throwables.propagate(e);
        } catch (YamlException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * Format the rss feed url based on the available filter options such as bedrooms, bathrooms,
     * min price, max price and rent controlled.
     *
     * @return A formatted RSS url.
     */
    public String constructFeedUrl() {
        String baseUrl = "";
        baseUrl += properties.getFeedUrl();
        baseUrl += "&bedrooms=" + properties.getBedrooms();
        baseUrl += "&bathrooms=" + properties.getBathrooms();
        baseUrl += "&min_price=" + properties.getMinPrice();
        baseUrl += "&maxPrice=" + properties.getMaxPrice();
        if (properties.isRentControlled()) {
          baseUrl += "&query=rent%20controlled";
        }

        return baseUrl;
    }

    public SmtpSettings getSmtpSettings() {
        return new SmtpSettings(
                properties.getSmtpUsername(),
                properties.getSmtpPassword(),
                properties.getSmtpServer(),
                properties.getSmtpServerPort(),
                properties.getToEmail());
    }

    public int getInterval() {
        return properties.getParseInterval();
    }
}
