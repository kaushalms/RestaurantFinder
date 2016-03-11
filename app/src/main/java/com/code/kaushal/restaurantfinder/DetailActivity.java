package com.code.kaushal.restaurantfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.factual.driver.Factual;
import com.factual.driver.Query;
import com.factual.driver.ReadResponse;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailActivity extends Activity {
    //FactualRetrievalTask task= new FactualRetrievalTask();

    Factual factual = new Factual("1jCF9JBdl0km6D2W0IGw0j4BI7RoXA1NpWiSoNcF", "sMFty780Ggvd28vGlWFtRPbmgp3alVE5EE6Rr3Gq");
    String city=null;
    Query query = new Query();
    FactualRetrievalTask task= new FactualRetrievalTask();
    //private RestaurantAdapter adapter;
    String[] cityList= null;
    String[] NewcityList=null;
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //RecyclerView recyclerView= new RecyclerView(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycleList);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            city =(String) b.get("city");

        }

        query = new Query().search("New York").only("name","address","tel");
        task.execute(query);


    }

    /*public static List<Information> getdata(String[] restaurant) {
        List<Information> data = new ArrayList<>();
        String[] titles = {"test1", "test2", "test3", "test4"};
        for (int i = 0; i < titles.length; i++) {
            Information current = new Information();
            current.name = titles[i];
            data.add(current);

        }
        return data;
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
                    String phone = (String) restaurant.get("tel");
                    //Number distance = (Number) restaurant.get("$distance");
                    sb.append("Name: "+ name+"\n" +"Address: "+address + "\nPhone: "+phone);
                    str=sb.toString();
                    mylist.add(str);
                }
            }
            cityList = new String[mylist.size()];
            cityList = mylist.toArray(cityList);

            //adapter= new RestaurantAdapter(getBaseContext(),getdata(cityList));
            //recyclerView.setAdapter(adapter);
            //recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext() ));

            //getdata(cityList);
            //resultText.setText(sb.toString());
            Log.w("myApp", String.valueOf(mylist));
        }



    }

}
