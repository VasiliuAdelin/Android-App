package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    String[] listItem;
    ArrayList<String> clickedItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        Log.d("lifecycle","onCreate invoked");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView);

        listItem = getResources().getStringArray(R.array.array_technology);

        if (savedInstanceState != null) {
            clickedItems = savedInstanceState.getStringArrayList("savedItems");
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = adapter.getItem(position);
                clickedItems.add(selectedItem);
                String aux = "";
                for(String i:clickedItems){
                    aux += i;
                    aux+= '\n';
                }
                Toast.makeText(getApplicationContext(), aux, Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("savedItems", clickedItems);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop invoked");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "onRestart invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy invoked");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.register:
                openRegisterPage();
                return true;
            case R.id.login:
                openLoginPage();
                return true;
            case R.id.settings:
                openSettingsPage();
                return true;
            case R.id.sensors:
                openSensorsPage();
                return true;
            case R.id.camera:
                openCameraPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSensorsPage() {
        Intent intent = new Intent(this, activity_sensors.class);
        startActivity(intent);
    }

    public void openSettingsPage() {
        Intent intent = new Intent(this, activity_settings.class);
        startActivity(intent);
    }

    public void openLoginPage() {
        Intent intent = new Intent(this, activity_login.class);
        startActivity(intent);
    }

    public void openRegisterPage() {
        Intent intent = new Intent(this, activity_register.class);
        startActivity(intent);
    }
    public void openCameraPage() {
        Intent intent = new Intent(this, activity_camera.class);
        startActivity(intent);
    }

}