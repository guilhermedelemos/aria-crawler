package io.github.guilhermedelemos.ariacrawler;

import io.github.guilhermedelemos.ariacrawler.db.LocalConnection;
import io.github.guilhermedelemos.ariacrawler.db.Migration;
import io.github.guilhermedelemos.ariacrawler.log.Log;
import io.github.guilhermedelemos.ariacrawler.model.Site;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String GREETING_MESSAGE = "Aria Crawler.";
    public static final String FAREWELL_MESSAGE = "Aria Crawler ended.";
    private Log log;

    public App() {
        super();
        this.log = new Log();
        this.prepareLocalDatabase();
    }

    public String getGreeting() {
        return GREETING_MESSAGE;
    }
    public String farewell() {
        return FAREWELL_MESSAGE;
    }

    public static void main(String[] args) {
        App app = new App();
        app.execute();
    }

    public void prepareLocalDatabase() {
        LocalConnection lc = LocalConnection.getInstance();
        Migration migration = new Migration();
        migration.localMigration(lc.getLocal());
        migration.logMigration(lc.getLog());
    }

    public void execute() {
        this.getGreeting();
        // https://www.alexa.com/topsites
        // http://s3-us-west-1.amazonaws.com/umbrella-static/index.html
        // https://blog.majestic.com/development/majestic-million-csv-daily
        // https://www.similarweb.com/top-websites

        List<Site> controlSites = null;
        List<Site> alexaSites = null;
        try {
            controlSites = (new StrategySiteCSV("./data/20180802-control-sample.csv")).read();
            alexaSites = (new StrategySiteCSV("./data/20180809-alexa-top-50.csv")).read();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found.");
            System.exit(1);
        }

        List<Site> sites = new ArrayList<>();
        sites.addAll(controlSites);
        //sites.addAll(alexaSites);

        Crawler crawler = new Crawler();
        crawler.execute(sites);

        this.farewell();
    }

}
