package craigslist;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import craigslist.mail.MailModule;
import craigslist.mail.SmtpSettings;
import craigslist.rss.RssModule;

public class CraigslistModule extends AbstractModule {
    private final Configuration configuration;

    CraigslistModule(String appPropertyFilePath) {
        this.configuration = new Configuration(appPropertyFilePath);
    }

    @Override
    protected void configure() {
        install(new RssModule(configuration.constructFeedUrl()));

        bind(SmtpSettings.class).toInstance(configuration.getSmtpSettings());
        install(new MailModule());

        bind(Configuration.class).toInstance(configuration);
        bind(CraigslistDaemon.class).in(Singleton.class);
    }
}
