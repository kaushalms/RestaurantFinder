package com.code.kaushal.restaurantfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class RestaurantActivity extends FragmentActivity {
    String city = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            city =(String) b.get("city");

        }
        else{
            Toast.makeText(this, "Enter city name",
                    Toast.LENGTH_SHORT).show();
            Intent ii=new Intent(this, MainActivity.class);
            startActivity(ii);
        }

        Log.w("RestaurantActivity-------", city);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new RestaurantActivityFragment())
                    .commit();
            }
    }

    public String getCityData() {
        return city;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant, menu);
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
}
