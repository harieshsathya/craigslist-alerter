package craigslist.mail;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Implementation of a mailer using SMTP. This implementation uses TLS.
 */
public class SmtpMailer implements Mailer {
    private static Logger logger = LoggerFactory.getLogger(SmtpMailer.class);

    private final SmtpSettings settings;

    @Inject
    SmtpMailer(SmtpSettings settings) {
        this.settings = settings;
    }

    public void sendMail(String message) {
        logger.debug("Sending email.");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", settings.getHost());
        props.put("mail.smtp.port", settings.getPort());

        final Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(settings.getUsername(), settings.getPassword());
                    }
                });

        try {
            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress("no-reply@craigslist-alert-daemon"));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(settings.getToEmail()));
            email.setSubject("Craigslist Email Alert");
            email.setText(message);
            Transport.send(email);

        } catch (MessagingException e) {
            logger.error("Exception when sending an email", e);
            throw new RuntimeException(e);
        }
    }
}
