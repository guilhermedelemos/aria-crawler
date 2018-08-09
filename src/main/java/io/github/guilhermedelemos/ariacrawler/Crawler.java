package io.github.guilhermedelemos.ariacrawler;

import io.github.guilhermedelemos.ariacrawler.log.Log;
import io.github.guilhermedelemos.ariacrawler.model.Site;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class Crawler {

    private Log log;

    public Crawler() {
        super();
    }

    public Crawler(Log log) {
        this.log = log;
    }

    public Site getMetaData(String url) {
        Site site = new Site();
        site.setUrl(url);
        try {
            URL u = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            //huc.setRequestMethod ("GET");
            huc.setRequestMethod("HEAD");
            huc.connect();
            site.setHttpStatusCode(huc.getResponseCode());
            site.setUrlAfterRequest(huc.getHeaderField("Location"));
            return site;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean siteIsValid(Site site) {
        //return site.getHttpStatusCode() == 200;
        return site.getHttpStatusCode() != 404;
    }

    public void extractElements(WebElement element) {
        if(element == null) {
            return;
        }
        this.log.log("-----");
        this.log.log("TagName " + element.getTagName());
        this.log.log("Location X " + element.getLocation().getX());
        this.log.log("Location Y " + element.getLocation().getY());
        this.log.log("Height " + element.getSize().getHeight());
        this.log.log("Width " + element.getSize().getWidth());
        this.log.log("Displayed " + element.isDisplayed());
        this.log.log("Enabled " + element.isEnabled());
        this.log.log("Selected " + element.isSelected());
        this.log.log("Text " + element.getText());
        //this.log.log("Text " + element.getText());
        //subElements
        List<WebElement> children = element.findElements(By.xpath(".//*"));
        Iterator<WebElement> it = children.iterator();
        while(it.hasNext()) {
            WebElement e = it.next();
            this.log.log("-----");
            this.log.log("-->TagName " + e.getTagName());
            this.log.log("-->Location X " + e.getLocation().getX());
            this.log.log("-->Location Y " + e.getLocation().getY());
            this.log.log("-->Height " + e.getSize().getHeight());
            this.log.log("-->Width " + e.getSize().getWidth());
            this.log.log("-->Displayed " + e.isDisplayed());
            this.log.log("-->Enabled " + e.isEnabled());
            this.log.log("-->Selected " + e.isSelected());
            this.log.log("-->Text " + e.getText());
        }
    }
}
