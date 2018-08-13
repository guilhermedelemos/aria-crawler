package io.github.guilhermedelemos.ariacrawler.model;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    @Test
    public void instanceTest() {
        Date currentDate = Calendar.getInstance().getTime();
        Log log = new Log();
        log.setId(1);
        log.setMessage("A");
        log.setLogDate(currentDate);
        log.setLevel(Log.LOG_LEVEL_INFO);
        assertNotNull(log);
        assertEquals(1, (int)log.getId());
        assertEquals("A",log.getMessage());
        assertEquals(currentDate, log.getLogDate());
        assertEquals(Log.LOG_LEVEL_INFO, log.getLevel());
    }

    @Test
    public void constructorTest() {
        Date currentDate = Calendar.getInstance().getTime();
        Log log = new Log(1, "A", currentDate, Log.LOG_LEVEL_ERROR);
        assertNotNull(log);
        assertEquals(1, (int)log.getId());
        assertEquals("A",log.getMessage());
        assertEquals(currentDate, log.getLogDate());
        assertEquals(Log.LOG_LEVEL_ERROR, log.getLevel());
    }

}