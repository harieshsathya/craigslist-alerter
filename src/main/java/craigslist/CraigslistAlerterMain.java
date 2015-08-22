package craigslist;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main class to execute the craigslist alerter.
 */
public class CraigslistAlerterMain {
    private static Logger logger = LoggerFactory.getLogger(CraigslistAlerterMain.class);

    public static void main(String[] args) {
        logger.info("Starting application...");
        Injector injector = Guice.createInjector(new CraigslistModule(args[0]));

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(
                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("craigslist-daemon").build());

        CraigslistDaemon daemon = injector.getInstance(CraigslistDaemon.class);
        Configuration configuration = injector.getInstance(Configuration.class);

        executorService.schedule(daemon, configuration.getInterval(), TimeUnit.MINUTES);
        logger.info("Parsing data every " + configuration.getInterval() + " minutes");

        // Note: Loop forever until termination. Shutsdown gracefully since the thread used above
        // is a daemon thread. Could improve by using a lifecycle management.
        logger.info("Waiting for termination...");
        while (true) {}
    }
}
