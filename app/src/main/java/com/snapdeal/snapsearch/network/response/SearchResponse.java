package com.snapdeal.snapsearch.network.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adarshpandey on 11/22/14.
 */
public class SearchResponse implements Serializable {
    public boolean     successful;
    public String      code;
    public String      message;


    public SearchResultDTOMobile searchResultDTOMobile;




    public static class SearchResultDTOMobile implements Serializable {

        public SearchResultDTO searchResultDTO;

        @SerializedName("catalogSearchDTOMobile")
        public ProductInfo[] productInfos;

    }

    public static class MatchingCategory {


    }

    public static class SearchResultDTO implements Serializable {
        @SerializedName("matchingCategories")
        public CategoryInfo[] categoryInfos;



    }

    public static class CategoryInfo implements Serializable {
        public int      id;
        public int      priority;
        public String   name;
        public String   link;
        public String   noOfResults;
        public String   vertical;

        @SerializedName("subCategories")
        public CategoryInfo[] categoryInfos;


    }

    public static class ProductInfo {
        public long id;
        public String type;
        public String title;
        public double price;
        public double sellingPrice;
        public double displayPrice;
        public String img;
        public double discount;
        public boolean codAvailable;
    }
}
