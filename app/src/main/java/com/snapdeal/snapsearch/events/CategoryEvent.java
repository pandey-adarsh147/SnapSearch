package com.snapdeal.snapsearch.events;

import com.snapdeal.snapsearch.network.response.SearchResponse;

/**
 * Created by adarshpandey on 11/22/14.
 */
public class CategoryEvent {
    public SearchResponse.CategoryInfo[] categoryInfos;

    public CategoryEvent(SearchResponse.CategoryInfo[] categoryInfos) {
        this.categoryInfos = categoryInfos;
    }
}
