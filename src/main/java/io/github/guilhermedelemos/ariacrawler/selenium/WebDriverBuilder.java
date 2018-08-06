package io.github.guilhermedelemos.ariacrawler.selenium;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverBuilder {

    public static final String LANGUAGE_PT_BR = "pt-BR";
    public static final String LANGUAGE_EN_US = "en-US";

    public static WebDriver buildChromeDriver() {
        return buildChromeDriver(false, "");
    }

    public static WebDriver buildChromeDriver(boolean headless) {
        return buildChromeDriver(headless, "");
    }

    public static WebDriver buildChromeDriver(boolean headless, String language) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if(headless) {
            chromeOptions.addArguments("headless");
        }
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        if(!language.trim().isEmpty()) {
            //chromeOptions.addArguments("--lang=en-us"); // Debian/Ubuntu bug?
            DesiredCapabilities jsCapabilities = DesiredCapabilities.chrome();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("intl.accept_languages", language);
            chromeOptions.setExperimentalOption("prefs", prefs);
            jsCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        return new ChromeDriver(chromeOptions);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
