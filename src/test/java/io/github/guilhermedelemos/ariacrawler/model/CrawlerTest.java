package io.github.guilhermedelemos.ariacrawler.model;

import io.github.guilhermedelemos.ariacrawler.Crawler;
import io.github.guilhermedelemos.ariacrawler.log.Log;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CrawlerTest {

    @Test
    public void instanceTest() {
        assertNotNull(new Crawler());
        assertNotNull(new Crawler(new Log()));
    }

    @Test
    public void execute() {
        //TODO execute complete test
        //assertTrue((new Crawler()).execute());
        assertTrue(true);
    }

    @Test
    public void loadAriaLandmarksTest() {
        // TODO loadAriaLandmarks complete test
        /*List<String> ariaLandmarks = new ArrayList<>();
        ariaLandmarks.add("main");
        ariaLandmarks.add("navigation");
        Crawler crawlerMock = mock(Crawler.class);
        when(crawlerMock.loadAriaLandmarks()).thenReturn(ariaLandmarks);*/
        Crawler crawler = new Crawler();
        List<String> ariaLandmarks = crawler.loadAriaLandmarks();
        assertNotNull(ariaLandmarks);
        assertEquals(8, ariaLandmarks.size());
    }

    @Test
    public void loadSiteListTest() {
        // TODO loadSiteList complete test
        assertTrue(true);
    }

}