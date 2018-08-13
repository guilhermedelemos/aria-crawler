package io.github.guilhermedelemos.ariacrawler.model;

import io.github.guilhermedelemos.ariacrawler.App;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {

    private App app;

    public AppTest() {
        this.app = new App();
    }

    @Test
    public void instanceTest() {
        assertNotNull(this.app);
    }

    @Test
    public void getGreetingTest() {
        assertEquals("Aria Crawler.", app.getGreeting());
    }

    @Test
    public void farewellTest() {
        assertEquals("Aria Crawler ended.", app.farewell());
    }

}
