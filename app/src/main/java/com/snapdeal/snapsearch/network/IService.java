package com.snapdeal.snapsearch.network;

import com.snapdeal.snapsearch.network.response.SearchResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;


/**
 * Created by adarshpandey on 11/22/14.
 */
public interface IService {

    @GET("/search")
    public void getSearchResult(@QueryMap Map<String, String> queryMap, Callback<SearchResponse> searchResponseCallback);
}
