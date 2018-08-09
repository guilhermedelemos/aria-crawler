package io.github.guilhermedelemos.ariacrawler.model;

public class Site {
    private String url;
    private String urlAfterRequest;
    private int httpStatusCode;

    public Site() {
        super();
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
