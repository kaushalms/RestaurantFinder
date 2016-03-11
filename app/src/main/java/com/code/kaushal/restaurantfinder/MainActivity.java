package com.code.kaushal.restaurantfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    TextView resultText=null;
    Button submit=null;
    String city= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //task = new FactualRetrievalTask();
        submit= (Button) findViewById(R.id.button) ;
       // resultText=(TextView) findViewById(R.id.textView);




        submit.setOnClickListener(new View.OnClickListener() {
            EditText cityText = (EditText) findViewById(R.id.editText);


            @Override
            public void onClick(View view) {
                //city = cityText.toString();
                String str = cityText.getText().toString();
                if(str.matches("")){
                    Toast.makeText(MainActivity.this, "Enter city name",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Intent ii = new Intent(MainActivity.this, RestaurantActivity.class);
                    ii.putExtra("city", str);
                    startActivity(ii);
                }
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
