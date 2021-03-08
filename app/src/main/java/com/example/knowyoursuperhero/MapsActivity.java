package com.example.knowyoursuperhero;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kotlin.jvm.internal.Ref;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private FusedLocationProviderClient clientLOC;
    private SupportMapFragment mapFragment;
    private String TAG = "NearbyUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //Set user's location
        Utility.Companion.setLocation(new latlng(25, 60));
        //Get all users' location
        FirebaseFirestore.getInstance().collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Users user = document.toObject(Users.class);
                            Log.d(TAG, user.getUsername()+": "+ user.getLocation().getLatitude() + " " + user.getLocation().getLongitude());
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });


        //TODO: make sure user has location associated with them before service can run
        startService(new Intent(this, GPService.class));

        mapFragment.getMapAsync(this);

        //get client location
//        clientLOC = LocationServices.getFusedLocationProviderClient(this);
//        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        Task<Location> task = clientLOC.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //Log.e("*******", "onSuccess");
                if (location != null)
                    //Log.e("*****", "location not null");

                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {

                            // Add a marker in Sydney and move the camera
                            LatLng sydney = new LatLng(-34, 151);
                            Utility.Companion.setLocation(new latlng(sydney.latitude, sydney.longitude));
                            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                            MarkerOptions options = new MarkerOptions().position(latLng).title("You Are Here");
//
//                            Log.e("******", "lat and long: " + latLng.latitude + "," + latLng.longitude);
//
//                            //zoom to point on map
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                            //add marker
//                            googleMap.addMarker(options);
                        }
                    });
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                getLocation();
        }
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        Log.e("******", "onMapReady: " + sydney.longitude + "," +  sydney.latitude );
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}