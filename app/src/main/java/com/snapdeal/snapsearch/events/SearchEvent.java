package com.snapdeal.snapsearch.events;

/**
 * Created by adarshpandey on 11/22/14.
 */
public class SearchEvent {
    public String query;
    public String categoryXPath;
    public SearchEvent(String query, String categoryXPath) {
        this
                .query = query;
        this.categoryXPath = categoryXPath;
    }
}
