package com.code.kaushal.restaurantfinder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.factual.driver.Factual;
import com.factual.driver.Query;
import com.factual.driver.ReadResponse;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantActivityFragment extends Fragment {


    Factual factual = new Factual("1jCF9JBdl0km6D2W0IGw0j4BI7RoXA1NpWiSoNcF", "sMFty780Ggvd28vGlWFtRPbmgp3alVE5EE6Rr3Gq");
    String city=null;
    Query query = new Query();

    //private RestaurantAdapter adapter;
    String[] cityList= null;
    String[] NewcityList=null;
    RecyclerView recyclerView;


    private ArrayAdapter<String> mForecastAdapter;

    public RestaurantActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        RestaurantActivity activity = (RestaurantActivity) getActivity();
        String city = activity.getCityData();

        /*recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleList);

        query = new Query().search("New York").only("name","address","tel");

        String[] titles = {"test1", "test2", "test3", "test4"};
        for (int i = 0; i < titles.length; i++) {
            Information current = new Information();
            current.name = titles[i];
            data.add(current);

        }
        task.execute(query);
        adapter= new RestaurantAdapter(getActivity(),data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
        String[] forecastArray;
        forecastArray = new String[]{
                "today - sunny - 88/63",
                "tomorrow - Foggy - 70/40",
                "Weds -cloudy - 50/40",
                "Thurs - asteroids -100/100",
                "Fri - Solar flairs - 110/100",
                "Sat - Hail storm - 90/100",
                "Sun - Sunny - 80/68"
        };

        List<String> weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray));


        mForecastAdapter
                = new ArrayAdapter<String>(
                getActivity(), R.layout.list_item_fragment, R.id.list_item_forecast_textview, weekForecast

        );
        mForecastAdapter.clear();
        ListView listview = (ListView) rootView.findViewById(R.id.listview_forcast);
        listview.setAdapter(mForecastAdapter);
        FactualRetrievalTask task= new FactualRetrievalTask();
        query = new Query().field("locality").search(city).only("name","address","locality","tel","rating");
        task.execute(query);
        Log.w("RestaurantActivityFragment---------------", city);
        return rootView;

    }

    /*public static List<Information> getinfo(String[] restaurant) {
        List<Information> data = new ArrayList<>();
        String[] titles = {"test1", "test2", "test3", "test4"};
        for (int i = 0; i < restaurant.length; i++) {
            Information current = new Information();
            current.name = restaurant[i];
            data.add(current);

        }
        return data;
    }*/

    protected class FactualRetrievalTask extends AsyncTask<Query, Integer, List<ReadResponse>> {
        @Override
        protected List<ReadResponse> doInBackground(Query... params) {
            List<ReadResponse> results = Lists.newArrayList();
            for (Query q : params) {
                results.add(factual.fetch("restaurants-us", q));
            }
            return results;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
        }


        @Override
        protected void onPostExecute(List<ReadResponse> responses) {

            String str= new String();
            StringBuffer sb = new StringBuffer();
            ArrayList<String> mylist = new ArrayList<>();
            for (ReadResponse response : responses) {
                for (Map<String, Object> restaurant : response.getData()) {
                    String name = (String) restaurant.get("name");
                    String address = (String) restaurant.get("address");
                    String locality = (String) restaurant.get("locality");
                    String phone = (String) restaurant.get("tel");
                    Number rating = (Number) restaurant.get("rating");
                    //String cuisine = (String) restaurant.get("cuisine");
                    //Number distance = (Number) restaurant.get("$distance");
                    str= "Name: "+ name+"\n" +"Address: "+address + "\n"+locality+"\nPhone: "+phone +"\nRating: "+rating;
                            //"\nRating: "+rating;
                    //str=sb.toString();
                    mForecastAdapter.add(str);
                    mylist.add(str);
                }
            }
            cityList = new String[mylist.size()];
            cityList = mylist.toArray(cityList);
            //return cityList;
            /*for (int i = 0; i < cityList.length; i++) {
                Information current = new Information();
                current.name = cityList[i];
                data.add(current);
            }*/


            //getdata(cityList);
            //resultText.setText(sb.toString());
           // Log.w("myApp", String.valueOf(mylist));*/

            /*if (cityList != null) {
                mForecastAdapter.clear();
                for (String dayForecastStr : cityList) {
                    mForecastAdapter.add(dayForecastStr);
                }

            }*/
        }



    }




}
