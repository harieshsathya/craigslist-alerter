package craigslist.mail;

/**
 * An interface that sends an email using a mail server.
 */
public interface Mailer {

    /**
     * Send an email to the intended recepient with the provided subject and message. Implementing
     * classes determine recovery upon failure.
     *
     * @param message Email message.
     */
    public void sendMail(String message);
}
