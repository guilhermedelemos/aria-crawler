package io.github.guilhermedelemos.crawler;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandmarkAriaTest {

    private WebDriver driver;

    public LandmarkAriaTest() {
    }

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setUp() {
        driver = WebDriverBuilder.buildChromeDriver(false, "en-US");
    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void ariaSpecificationAssistiveTechnologyTest() {
        driver.get("https://www.w3.org/TR/wai-aria-practices/examples/landmarks/at.html");
        // BANNER / HEADER
        assertTrue(driver.findElement(By.tagName("header")).isDisplayed());
        // NAVIGATION
        assertTrue(driver.findElement(By.tagName("nav")).isDisplayed());
        // MAIN
        assertTrue(driver.findElement(By.tagName("main")).isDisplayed());
        // COMPLEMENTARY
        assertTrue(driver.findElement(By.tagName("aside")).isDisplayed());
        // REGION
        assertTrue(driver.findElement(By.tagName("section")).isDisplayed());
    }

    @Test
    public void youTubeTest() {
        driver.get("https://www.youtube.com");
        // BANNER / HEADER
        assertTrue(driver.findElement(By.cssSelector("[role=banner]")).isDisplayed());
        // NAVIGATION
        assertTrue(driver.findElement(By.cssSelector("[role=navigation]")).isEnabled());
        // MAIN
        assertTrue(driver.findElement(By.cssSelector("[role=main]")).isDisplayed());
    }

    @Test
    public void yahooTest() {
        driver.get("https://www.yahoo.com");
        // NAVIGATION
        assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[1]/ul")).isDisplayed());
        // xpath: não é possível generalizar para outras páginas
    }

    @Test
    public void navigationTest() {
        // Tentativa genérica de oráculo para aria landmark navigation
        //String[] enderecos = {"https://www.yahoo.com", "https://www.youtube.com"};
        String[] enderecos = {"https://guilhermedelemos.github.io/aria-crawler/aria-navigation2.html"};
        for (int i = 0; i < enderecos.length; i++) {
            assertTrue(genericNavigationTest(enderecos[i]));
        }
    }

    public boolean genericNavigationTest(String url) {
        driver.get(url);
        List<WebElement> elementos = null;
        try {
            elementos = driver.findElements(By.cssSelector("[role=navigation]"));
        } catch (Exception e) {
            return false;
        }
        if (elementos.isEmpty()) {
            // Não possui o elemento, mas deveria possuir? Não sabemos pela simples verificação do HTML/DOM
            return false;
        } else if (elementos.size() == 1) {
            // o elementoexiste mas não sabemos se está sendo usado corretamente.
            return elementos.get(0).isDisplayed();
        } else {
            // existe mais de um, portanto deve possuir um label
            boolean labelOk = false;
            List<String> labels = new ArrayList<>();
            for (int i = 0; i < elementos.size(); i++) {
                String label = elementos.get(i).getAttribute("aria-labelledby");
                if (label == null) {
                    return false;
                } else if (label.trim().isEmpty()) {
                    return false;
                } else {
                    if(labels.contains(label)) {
                        return false;
                    }
                    labelOk = driver.findElement(By.id(label.trim())).isDisplayed();
                    if(!labelOk) {
                        return false;
                    }
                }
            }
            return labelOk;
        }
    }

}
