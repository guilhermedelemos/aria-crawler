package io.github.guilhermedelemos.ariacrawler.model;

import java.util.Date;

public class Log {
    public static final String LOG_LEVEL_EXCEPTION = "exception";
    public static final String LOG_LEVEL_ERROR = "error";
    public static final String LOG_LEVEL_WARNING = "warning";
    public static final String LOG_LEVEL_INFO = "info";

    private Integer id;
    private String message;
    private Date logDate;
    private String level;

    public Log() {
        super();
    }

    public Log(Integer id, String message, Date logDate, String level) {
        this.id = id;
        this.message = message;
        this.logDate = logDate;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
