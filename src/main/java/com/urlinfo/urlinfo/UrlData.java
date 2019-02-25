package com.urlinfo.urlinfo;

public class UrlData {
    private final long id;
    private final String response;

    public UrlData(long id, String content) {
        this.id = id;
        this.response = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return response;
    }
}
