package com.snapdeal.snapsearch;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.snapdeal.snapsearch.application.SnapSearchApplication;
import com.snapdeal.snapsearch.events.SearchCompleteEvent;
import com.snapdeal.snapsearch.events.SearchEvent;
import com.snapdeal.snapsearch.network.APIClient;
import com.snapdeal.snapsearch.network.response.SearchResponse;
import com.squareup.otto.Subscribe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adarshpandey on 11/22/14.
 */
public class ProductListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);


        return view;
    }



    @Override
    public void onResume() {
        super.onResume();


        setListShown(true);


        SnapSearchApplication.getEventBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SnapSearchApplication.getEventBus().unregister(this);
    }

    @Subscribe
    public void onSearchCompleteEvent(SearchCompleteEvent searchEvent) {
        updateList(searchEvent);
    }

    @Subscribe
    public void onSearchCallEvent(SearchEvent searchEvent) {
        searchQuery(searchEvent);
        setListShown(false);
    }



    private void searchQuery(SearchEvent event) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("keyword", event.query);
        queryMap.put("start", "0");
        queryMap.put("number", "10");
        queryMap.put("sortBy", "rlvncy");
        queryMap.put("categoryXPath", event.categoryXPath);
        APIClient.loadSearchData(queryMap);
    }

    private void updateList(SearchCompleteEvent searchCompleteEvent) {
        setListShown(true);
        setListAdapter(new SearchListAdapter(getActivity(), R.layout.fragment_search,
                Arrays.asList(searchCompleteEvent.searchResponse.searchResultDTOMobile.productInfos)));
    }


    public static class SearchListAdapter extends ArrayAdapter<SearchResponse.ProductInfo> {

        private List<SearchResponse.ProductInfo> searchResponse;

        public SearchListAdapter(Context context, int resource, List<SearchResponse.ProductInfo> searchResponse) {
            super(context, resource);
            this.searchResponse = searchResponse;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.search_category_row, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.nameView = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            SearchResponse.ProductInfo info = getItem(position);
            viewHolder.nameView.setText(info.title);

            return convertView;
        }

        @Override
        public int getCount() {
            if (searchResponse != null) {
                return searchResponse.size();
            }

            return 0;
        }

        @Override
        public SearchResponse.ProductInfo getItem(int position) {
            return searchResponse.get(position);
        }

        public static class ViewHolder {
            TextView nameView;
        }
    }
}
