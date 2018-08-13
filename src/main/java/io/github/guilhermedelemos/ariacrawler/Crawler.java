package io.github.guilhermedelemos.ariacrawler;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.guilhermedelemos.ariacrawler.log.Log;
import io.github.guilhermedelemos.ariacrawler.model.Site;
import io.github.guilhermedelemos.ariacrawler.selenium.WebDriverBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Crawler {

    private Log log;

    public Crawler() {
        super();
        this.log = new Log();
    }

    public Crawler(Log log) {
        this.log = log;
    }

    public boolean execute(List<Site> sites) {
        if (sites == null || sites.size() <= 0) {
            return false;
        }
        try {
            List<String> ariaLandmarks = this.loadAriaLandmarks();
            //List<URL> sites = this.loadSiteList();

            WebDriverManager.chromedriver().setup();
            WebDriver webDriver = WebDriverBuilder.buildChromeDriver(true, WebDriverBuilder.LANGUAGE_EN_US);

            return this.scanSites(sites, ariaLandmarks, webDriver);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> loadAriaLandmarks() {
        List<String> ariaLandmarks = new ArrayList<>();
        ariaLandmarks.add("banner");
        ariaLandmarks.add("complementary");
        ariaLandmarks.add("contentinfo");
        ariaLandmarks.add("form");
        ariaLandmarks.add("main");
        ariaLandmarks.add("navigation");
        ariaLandmarks.add("region");
        ariaLandmarks.add("search");
        return ariaLandmarks;
    }

    public boolean scanSites(List<Site> sites, List<String> ariaLandmarks, WebDriver webDriver) {
        try {
            boolean result = true;
            Iterator<Site> it = sites.iterator();
            while (it.hasNext()) {
                Site site = it.next();
                result = (this.scanSite(new URL(site.getUrl()), ariaLandmarks, webDriver)) ? result : false;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean scanSite(URL site, List<String> ariaLandmarks, WebDriver webDriver) {
        try {
            WebPage webPage = this.getWebPageMetadata(site);

            this.log.separator();
            this.log.log(webPage.getUrl());
            this.log.log("HTTP Status Code: " + webPage.getHttpStatusCode());

            if (!this.siteIsValid(webPage)) {
                this.log.log("Invalid site skipped.");
                return false;
            }

            webDriver.get(webPage.getUrl().toString());
            webPage.setUrlAfterRequest(webDriver.getCurrentUrl());
            this.log.log("URL after request: " + webPage.getUrlAfterRequest());

            this.scanSiteForLandmarks(webPage, ariaLandmarks, webDriver);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean scanSiteForLandmarks(WebPage webPage, List<String> ariaLandmarks, WebDriver webDriver) {
        try {
            Iterator<String> it = ariaLandmarks.iterator();
            while (it.hasNext()) {
                String landmark = it.next();
                List<WebElement> elements = webDriver.findElements(By.cssSelector("[role=" + landmark + "]"));
                if (elements.size() > 0) {
                    this.log.log("# Landmark " + landmark + " found");
                    Iterator<WebElement> itWe = elements.iterator();
                    while (itWe.hasNext()) {
                        WebElement targetElement = itWe.next();
                        DomElement domElement = new DomElement();
                        domElement.setId(targetElement.getAttribute("id"));
                        domElement.setTagName(targetElement.getTagName());
                        domElement.setWebElement(targetElement);
                        webPage.addElement(domElement);
                        this.extractElements(domElement);
                    }
                } else {
                    log.log("# Landmark " + landmark + " NOT found");
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public WebPage getWebPageMetadata(URL url) {
        WebPage webPage = new WebPage();
        webPage.setUrl(url.toString());
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            webPage.setHttpStatusCode(connection.getResponseCode());
            webPage.setUrlAfterRequest(connection.getHeaderField("Location"));
            return webPage;
        } catch (IOException e) {
            e.printStackTrace();
            webPage.setHttpStatusCode(404);
            return webPage;
        }
    }

    public boolean siteIsValid(WebPage site) {
        return site.getHttpStatusCode() != 404;
    }

    public void extractElements(DomElement element) {
        this.extractElements(element, 0);
    }

    public void extractElements(DomElement element, int level) {
        if (element == null) {
            return;
        }
        this.log.logWebElement(element.getWebElement(), level);
        //subElements
        List<WebElement> children = element.getWebElement().findElements(By.xpath(".//*"));
        Iterator<WebElement> it = children.iterator();
        while (it.hasNext()) {
            WebElement targetElement = it.next();
            DomElement domElement = new DomElement();
            domElement.setId(targetElement.getAttribute("id"));
            domElement.setTagName(targetElement.getTagName());
            domElement.setWebElement(targetElement);
            element.addChild(domElement);
            this.extractElements(domElement, level + 1);
        }
    }

}
