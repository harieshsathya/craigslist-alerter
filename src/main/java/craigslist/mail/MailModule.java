package craigslist.mail;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class MailModule extends AbstractModule {
    @Override
    protected void configure() {
        requireBinding(SmtpSettings.class);
        bind(Mailer.class).to(SmtpMailer.class);
        bind(SmtpMailer.class).in(Singleton.class);
    }
}
