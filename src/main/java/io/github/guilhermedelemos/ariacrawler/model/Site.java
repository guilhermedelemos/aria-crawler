package io.github.guilhermedelemos.ariacrawler.model;

import java.util.Date;

public class Site {
    private int rank;
    private String url;
    private Date data;
    private String urlAfterRequest;
    private int httpStatusCode;

    public Site() {
        super();
    }

    public Site(int rank, String url, Date data) {
        this.rank = rank;
        this.url = url;
        this.data = data;
    }

    public Site(String url, String urlAfterRequest, int httpStatusCode) {
        this.url = url;
        this.urlAfterRequest = urlAfterRequest;
        this.httpStatusCode = httpStatusCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlAfterRequest() {
        return urlAfterRequest;
    }

    public void setUrlAfterRequest(String urlAfterRequest) {
        this.urlAfterRequest = urlAfterRequest;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
