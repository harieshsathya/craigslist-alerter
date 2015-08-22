package craigslist.rss;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Singleton;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class RssModule extends AbstractModule {

    @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
    @BindingAnnotation
    public @interface RssUrl { }

    private final String feedUrl;

    public RssModule(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(RssUrl.class).toInstance(feedUrl);
        bind(RssParser.class).to(RssParserImpl.class);
        bind(RssParserImpl.class).in(Singleton.class);
    }
}
