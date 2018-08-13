package io.github.guilhermedelemos.ariacrawler;

import io.github.guilhermedelemos.ariacrawler.db.LocalConnection;
import io.github.guilhermedelemos.ariacrawler.db.Migration;
import io.github.guilhermedelemos.ariacrawler.log.Log;
import io.github.guilhermedelemos.ariacrawler.model.Site;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {

    private WebDriver driver;
    private Log log;

    public App() {
        super();
        this.log = new Log();
        this.prepareLocalDatabase();
    }

    public String getGreeting() {
        return "Aria Crawler.";
    }
    public String farewell() {
        return "Aria Crawler ended";
    }

    public static void main(String[] args) {
        App app = new App();
        app.execute();
    }

    public void prepareLocalDatabase() {
        try {
            LocalConnection lc = LocalConnection.getInstance();
            Migration migration = new Migration();
            migration.localMigration(lc.getLocal());
            migration.logMigration(lc.getLog());
            if (lc.getLocal().getConnection().isValid(1))
                System.out.println("OK");
            else
                System.out.println("ERRO");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("SQLite");
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
