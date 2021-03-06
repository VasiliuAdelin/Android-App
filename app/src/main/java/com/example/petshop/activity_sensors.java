package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.petshop.R;

import java.util.ArrayList;
import java.util.List;


public class activity_sensors extends AppCompatActivity implements SensorEventListener, LocationListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Permission Denied");
            return;
        }
        if (!checkAndRequestPermissions()) {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            if (location != null) {
                onLocationChanged(location);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }

        }


    }

    private boolean checkAndRequestPermissions() {
        List<String> listPermissionsNeeded = new ArrayList<>();


        int internet = ContextCompat.checkSelfPermission(activity_sensors.this,
                Manifest.permission.INTERNET);
        int location= ContextCompat.checkSelfPermission(activity_sensors.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int location2 = ContextCompat.checkSelfPermission(activity_sensors.this,
                Manifest.permission.ACCESS_FINE_LOCATION);


        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (location != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (location2 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) activity_sensors.this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), 1);
            return false;
        }
        return true;
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            TextView textX = findViewById(R.id.xView);
            TextView textY = findViewById(R.id.yView);
            TextView textZ = findViewById(R.id.zView);
            textX.setText(String.valueOf(event.values[0]));
            textY.setText(String.valueOf(event.values[1]));
            textZ.setText(String.valueOf(event.values[2]));
        } else if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            TextView textTemperature = findViewById(R.id.temperaturaView);
            System.out.println("Temperatura actuala este: " + event.values.length);
            textTemperature.setText(String.valueOf(event.values[0]));
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        double Longitude = location.getLongitude();
        double Latitude = location.getLatitude();

        TextView longitude = findViewById(R.id.longitudeView);
        TextView latitude = findViewById(R.id.latitudeView);
        longitude.setText(String.valueOf(Longitude));
        latitude.setText(String.valueOf(Latitude));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                openHomePage();
                return true;
            case R.id.settings:
                openSettingsPage();
                return true;
            case R.id.register:
                openRegisterPage();
                return true;
            case R.id.login:
                openLoginPage();
                return true;
            case R.id.camera:
                openCameraPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openLoginPage() {
        Intent intent = new Intent(this, activity_login.class);
        startActivity(intent);
    }

    public void openSettingsPage() {
        Intent intent = new Intent(this, activity_settings.class);
        startActivity(intent);
    }

    public void openHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
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