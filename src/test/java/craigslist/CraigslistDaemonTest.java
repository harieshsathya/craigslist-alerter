package craigslist;

import craigslist.mail.Mailer;
import craigslist.rss.RssData;
import craigslist.rss.RssParser;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CraigslistDaemonTest {
    private static final String LINK = "link";

    private CraigslistDaemon daemon;
    private IMocksControl control;
    private RssParser parser;
    private RssData data;
    private Mailer mailer;

    @Before
    public void setup() {
        control = EasyMock.createControl();
        parser = control.createMock(RssParser.class);
        mailer = control.createMock(Mailer.class);
        data = control.createMock(RssData.class);
        daemon = new CraigslistDaemon(parser, mailer);
    }

    @Test
    public void testRun() {
        EasyMock.expect(parser.parse()).andReturn(Arrays.asList(data)).anyTimes();
        EasyMock.expect(data.getLink()).andReturn(LINK).anyTimes();
        EasyMock.expect(data.getData()).andReturn("data");
        mailer.sendMail("data");

        control.replay();

        daemon.run();

        // No call to send mail expected.
        daemon.run();
    }
}
