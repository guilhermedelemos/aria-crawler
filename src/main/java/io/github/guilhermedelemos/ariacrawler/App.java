package io.github.guilhermedelemos.ariacrawler;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.guilhermedelemos.ariacrawler.log.Log;
import io.github.guilhermedelemos.ariacrawler.selenium.WebDriverBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {

    private WebDriver driver;
    private Log log;

    public App() {
        super();
        this.log = new Log();
    }

    public String getGreeting() {
        return "Aria Crawler.";
    }

    public static void main(String[] args) {
        // https://www.alexa.com/topsites
        // http://s3-us-west-1.amazonaws.com/umbrella-static/index.html
        // https://blog.majestic.com/development/majestic-million-csv-daily
        // https://www.similarweb.com/top-websites

        App app = new App();
        System.out.println(app.getGreeting());

        app.run();
    }

    public void run() {
        try {
            List<String> ariaLandmarks = new ArrayList<>();
            ariaLandmarks.add("banner");
            ariaLandmarks.add("complementary");
            ariaLandmarks.add("contentinfo");
            ariaLandmarks.add("form");
            ariaLandmarks.add("main");
            ariaLandmarks.add("navigation");
            ariaLandmarks.add("region");
            ariaLandmarks.add("search");

            List<String> sites = new ArrayList<>();
            // CONTROLE
            sites.add("https://guilhermedelemos.github.io/accessible/examples/banner-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/complementary-one-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/complementary-multiple-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/contentinfo-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/form-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/main-one-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/main-multiple-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/navigation-one-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/navigation-multiple-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/region-aria.html");
            sites.add("https://guilhermedelemos.github.io/accessible/examples/search-aria.html");
            // ALEXA TOP 50 (free)
            sites.add("http://google.com");
            sites.add("http://youtube.com");
            sites.add("http://facebook.com");
            sites.add("http://baidu.com");
            sites.add("http://wikipedia.org");
            sites.add("http://yahoo.com");
            sites.add("http://qq.com");
            sites.add("http://amazon.com");
            sites.add("http://taobao.com");
            sites.add("http://tmall.com");
            sites.add("http://twitter.com");
            sites.add("http://instagram.com");
            sites.add("http://google.co.in");
            sites.add("http://sohu.com");
            sites.add("http://live.com");
            sites.add("http://jd.com");
            sites.add("http://reddit.com");
            sites.add("http://vk.com");
            sites.add("http://sina.com.cn");
            sites.add("http://weibo.com");
            sites.add("http://google.co.jp");
            sites.add("http://360.cn");
            sites.add("http://login.tmall.com");
            sites.add("http://blogspot.com");
            sites.add("http://yandex.ru");
            sites.add("http://netflix.com");
            //sites.add("http://pornhub.com");
            sites.add("http://google.ru");
            sites.add("http://google.co.uk");
            sites.add("http://linkedin.com");
            sites.add("http://google.com.br");
            sites.add("http://google.com.hk");
            sites.add("http://yahoo.co.jp");
            sites.add("http://csdn.net");
            sites.add("http://twitch.tv");
            sites.add("http://t.co");
            sites.add("http://alipay.com");
            sites.add("http://pages.tmall.com");
            sites.add("http://google.fr");
            sites.add("http://ebay.com");
            sites.add("http://google.de");
            sites.add("http://microsoft.com");
            //sites.add("http://xvideos.com");
            sites.add("http://bing.com");
            sites.add("http://wikia.com");
            sites.add("http://msn.com");
            sites.add("http://mail.ru");
            sites.add("http://imdb.com");
            sites.add("http://aliexpress.com");
            sites.add("http://naver.com");
            //

            WebDriverManager.chromedriver().setup();
            driver = WebDriverBuilder.buildChromeDriver(false, WebDriverBuilder.LANGUAGE_EN_US);
            //ChromeDriverManager.getInstance().setup();

            Iterator<String> it = sites.iterator();
            while (it.hasNext()) {
                String site = it.next();
                log.separator();
                log.log(site);

                driver.get(site);

                Iterator<String> itLandmark = ariaLandmarks.iterator();
                while (itLandmark.hasNext()) {
                    String landmark = itLandmark.next();
                    List<WebElement> elements = driver.findElements(By.cssSelector("[role=" + landmark + "]"));
                    if (elements.size() > 0) {
                        log.log("Landmark " + landmark + " found");
                    } else {
                        log.log("Landmark " + landmark + " not found");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null)
                driver.close();
        }
    }
}
