package com.snapdeal.snapsearch.network;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.snapdeal.snapsearch.BuildConfig;
import com.snapdeal.snapsearch.application.SnapSearchApplication;
import com.snapdeal.snapsearch.events.SearchCompleteEvent;
import com.snapdeal.snapsearch.network.response.SearchResponse;

import java.io.IOException;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by adarshpandey on 11/22/14.
 */
public class APIClient {

    public static void loadSearchData(Map<String, String> queryMap) {

        IService service = SnapSearchRestAdapter.createRestAdapter().create(IService.class);

        service.getSearchResult(queryMap, new Callback<SearchResponse>() {
            @Override
            public void success(SearchResponse searchResponse, Response response) {
                SearchCompleteEvent event = new SearchCompleteEvent();
                event.searchResponse = searchResponse;
                SnapSearchApplication.getEventBus().post(event);
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("Error ============== >");
            }
        });
    }



    public static class SnapSearchRestAdapter {
        private static RestAdapter restAdapter;

        private static synchronized RestAdapter createRestAdapter() {
            if (restAdapter == null) {
                /*Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                        .registerTypeAdapter(Boolean.class, new DateTypeAdapter())
                        .create();*/

                restAdapter = new RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setEndpoint(BuildConfig.URL).build();


            }

            return restAdapter;
        }
    }

    public class BooleanTypeAdapter extends TypeAdapter<Boolean> {

        public BooleanTypeAdapter() {
        }

        @Override
        public void write(JsonWriter out, Boolean value) throws IOException {

        }

        @Override
        public Boolean read(JsonReader in) throws IOException {
            return null;
        }
    }


}
