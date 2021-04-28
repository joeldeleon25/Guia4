package com.example.guia4;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private UiSettings mUiSettings;

    private FollowPosition followPosition;


    Marker park1;
    Marker park2;
    Marker park3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (followPosition != null) {
            followPosition.register(MapsActivity.this);
        }
    }

    @Override
    protected void onPause() {
        followPosition.unRegister(MapsActivity.this); super.onPause();
        }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        followPosition = new FollowPosition(this.mMap, MapsActivity.this);
        followPosition.register(MapsActivity.this);

        // Add a marker in Sydney and move the camera
        LatLng sv = new LatLng(13.6989, 	-89.1914);


//        Marker myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sv));

        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);



        mMap.setOnMarkerClickListener(this);
        drawShapes();


        parksMarker();

    }



    //El siguiente método custom permite agregar diferentes figuras
    private void drawShapes() {
        ShapesMap dangerous = new ShapesMap(this.mMap); //PolyLines
        ArrayList<LatLng> lines = new ArrayList<>();
        lines.add(new LatLng(13.677181, -89.248788));
        lines.add(new LatLng(13.676973, -89.248584));
        lines.add(new LatLng(13.676663, -89.249158));
        lines.add(new LatLng(13.676762, -89.249485));
        lines.add(new LatLng(13.677181, -89.248788));

        //Llamado al método custom drawLine de shapesMap
        dangerous.drawLine(lines,5, Color.RED);


        ShapesMap river = new ShapesMap(this.mMap); //PolyLines
        ArrayList<LatLng> riverLines = new ArrayList<>();
        riverLines.add(new LatLng(13.678577, -89.245781));
        riverLines.add(new LatLng(13.678467, -89.245667));
        riverLines.add(new LatLng(13.678275, -89.245894));
        riverLines.add(new LatLng(13.678227, -89.246506));
        riverLines.add(new LatLng(13.678318, -89.246615));
        riverLines.add(new LatLng(13.678429, -89.245934));
        riverLines.add(new LatLng(13.678577, -89.245781));

        //Llamado al método custom drawLine de shapesMap
        river.drawLine(riverLines,5, Color.BLUE);
    }

    private void parksMarker(){
        park1 = mMap.addMarker(new MarkerOptions().position(new LatLng(13.689356, -89.251648)).title("Parque 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.baseline_park_green_700_24dp)));
        park2 = mMap.addMarker(new MarkerOptions().position(new LatLng(13.683515, -89.263691)).title("Parque 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.baseline_park_green_700_24dp)));
        park3 = mMap.addMarker(new MarkerOptions().position(new LatLng(13.668838, -89.247072)).title("Parque 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.baseline_park_green_700_24dp)));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(park1)){
            Toast.makeText(getApplicationContext(),"Parque Bicentenario",Toast.LENGTH_SHORT).show();
            return  true;
        }
        else if (marker.equals(park2)){
            Toast.makeText(getApplicationContext(),"Parque Principito",Toast.LENGTH_SHORT).show();
            return  true;
        }
        else if (marker.equals(park3)){
            Toast.makeText(getApplicationContext(),"Parque Jardin Botanico",Toast.LENGTH_SHORT).show();
            return  true;
        }
        return false;
    }
}