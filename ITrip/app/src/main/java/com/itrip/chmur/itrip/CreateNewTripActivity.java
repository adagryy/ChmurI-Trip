package com.itrip.chmur.itrip;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class CreateNewTripActivity extends AppCompatActivity {

    public GoogleMap mMap;
    public static final int LOCATION_REQUEST = 500;
    ArrayList points_Lat = new ArrayList();
    ArrayList points_Lon = new ArrayList();
    LocationManager location_Manager;
    private boolean isCanceled = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_trip2);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }


        final Button btn_start = (Button) findViewById(R.id.btn_start);
        final Button btn_stop = (Button) findViewById(R.id.btn_stop);

        btn_stop.setEnabled(false);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_start.setEnabled(false);
                btn_stop.setEnabled(true);

                isCanceled = false;

                final long millisInfuture = 10000;
                final long countDownInterval = 10000;
                new CountDownTimer(millisInfuture, countDownInterval) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(isCanceled){
                            cancel();
                        }
                    }

                    @Override
                    public void onFinish() {
                        if(!isCanceled) {
                             localization_point();
                             //Log.i("HALLO","Im here");
                        }
                    }
                }.start();

            }
        });


        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCanceled = true;
                btn_start.setEnabled(true);
                btn_stop.setEnabled(false);
                create_save_gpx create_save_gpx = new create_save_gpx();
                create_save_gpx.creator(points_Lat,points_Lon);
            }
        });


    }


    public void localization_point() {

        location_Manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (location_Manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            location_Manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    //LatLng latLng = new LatLng(2, 155);

                    points_Lat.add(String.valueOf(latitude));
                    points_Lon.add(String.valueOf(longitude));
                    Log.i(String.valueOf(latitude), String.valueOf(longitude));

                    // System.out.println(latitude+ "     " + longitude);

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
            });

        } else if (location_Manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            location_Manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    //Log.i(String.valueOf(latitude), String.valueOf(longitude));
                    //LatLng latLng = new LatLng(2,133);
                    points_Lat.add(latitude);
                    points_Lon.add(longitude);
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
            });
        }

    }

}
