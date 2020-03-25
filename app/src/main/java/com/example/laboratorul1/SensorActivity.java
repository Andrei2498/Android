package com.example.laboratorul1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.SENSOR_SERVICE;

public class SensorActivity extends Activity implements SensorEventListener, LocationListener {

    private  SensorManager mSensorManager;
    private  Sensor mAccelerometer;
    private  Sensor mTemperature;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        if(!checkAndRequestPermissions()){
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            if(location != null){
                onLocationChanged(location);
            }
            else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }
    }

    public boolean checkAndRequestPermissions() {
        int internet = ContextCompat.checkSelfPermission(SensorActivity.this,
                Manifest.permission.INTERNET);
        int loc = ContextCompat.checkSelfPermission(SensorActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int loc2 = ContextCompat.checkSelfPermission(SensorActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (loc != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (loc2 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) SensorActivity.this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), 1);
            return false;
        }
        return true;
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            TextView textX = findViewById(R.id.textView7);
            TextView textY = findViewById(R.id.textView9);
            TextView textZ = findViewById(R.id.textView11);
            textX.setText(String.valueOf(event.values[0]));
            textY.setText(String.valueOf(event.values[1]));
            textZ.setText(String.valueOf(event.values[2]));
        }
        else if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            TextView textTemperature = findViewById(R.id.temperatureView);
            textTemperature.setText(String.valueOf(event.values[0]));
        }
        else if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            TextView textLight = findViewById(R.id.lightView);
            textLight.setText(String.valueOf(event.values[0]));
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
}

