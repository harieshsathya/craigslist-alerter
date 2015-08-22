package craigslist.mail;

public class SmtpSettings {
    private final String username;
    private final String password;
    private final String host;
    private final String port;
    private final String toEmail;

    public SmtpSettings(String username,
                        String password,
                        String host,
                        String port,
                        String toEmail) {

        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.toEmail = toEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getToEmail() {
        return toEmail;
    }
}
