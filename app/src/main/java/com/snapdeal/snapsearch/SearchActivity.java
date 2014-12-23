package com.snapdeal.snapsearch;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.snapdeal.snapsearch.application.SnapSearchApplication;
import com.snapdeal.snapsearch.events.SearchEvent;


public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ProductListFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SnapSearchApplication.getEventBus().post(new SearchEvent(query, "ALL"));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.action_search:
                openSearch();
                return true;

        }


        return super.onOptionsItemSelected(item);
    }

   /* @Subscribe
    public void onNextLevelCategoryEvent(CategoryEvent categoryEvent) {
        SearchListFragment listFragment = new SearchListFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("categories", categoryEvent.categoryInfos);

        listFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .add(R.id.container, new SearchListFragment())
                .addToBackStack(null)
                .commit();
    }*/

    //////////// Private methods

    private void openSearch() {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
   /* public static class SearchListFragment extends ListFragment {

        public SearchListFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);

            if (getArguments() != null) {
                Bundle bundle = getArguments();
                if (bundle.containsKey("categories")) {
                    SearchResponse.CategoryInfo[] categoryInfos = (SearchResponse.CategoryInfo[]) bundle.getSerializable("categories");
                    setListAdapter(new SearchListAdapter(getActivity(), R.layout.fragment_search,
                            Arrays.asList(categoryInfos)));
                }
            }

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
                    Arrays.asList(searchCompleteEvent.searchResponse.searchResultDTOMobile.searchResultDTO.categoryInfos)));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            SearchResponse.CategoryInfo info = (SearchResponse.CategoryInfo) getListAdapter().getItem(position);
            if (info.categoryInfos.length > 0 ) {
//                SnapSearchApplication.getEventBus().post(new CategoryEvent( info.categoryInfos));
                SnapSearchApplication.getEventBus().post(new SearchEvent(info.name, info.link));
            }
        }
    }

    public static class SearchListAdapter extends ArrayAdapter<SearchResponse.CategoryInfo> {

        private List<SearchResponse.CategoryInfo> searchResponse;

        public SearchListAdapter(Context context, int resource, List<SearchResponse.CategoryInfo> searchResponse) {
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

            SearchResponse.CategoryInfo info = getItem(position);
            viewHolder.nameView.setText(info.name);

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
        public SearchResponse.CategoryInfo getItem(int position) {
            return searchResponse.get(position);
        }

        public static class ViewHolder {
            TextView nameView;
        }
    }*/

    //////////////

}
