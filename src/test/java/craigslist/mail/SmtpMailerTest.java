package craigslist.mail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SmtpMailerTest {
    private SmtpMailer mailer;

    @Before
    public void setup() {
        SmtpSettings settings =
                new SmtpSettings("username@gmail.com", "password", "smtp.gmail.com", "587", "username@gmail.com");
        mailer = new SmtpMailer(settings);
    }

    @Test
    @Ignore
    public void testEmail() {
        mailer.sendMail("Test");
    }
}
