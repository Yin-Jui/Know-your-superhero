package com.example.knowyoursuperhero;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private FusedLocationProviderClient clientLOC;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
        //Set default location first, maps seems to be less glitchy this wa
        Utility.Companion.setLocation(new latlng(25, 60));

        //get client location
        clientLOC = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //ask for permission if not initially given it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
        }

        clientLOC.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("You Are Here");

                            //add location to firebase
                            Utility.Companion.setLocation(new latlng(location.getLatitude(), location.getLongitude()));
                            //zoom to point on map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            //add marker
                            googleMap.addMarker(options);

                            //run service only when location is discovered
                            startService(new Intent(getBaseContext(), GPService.class));
                        }
                    });
            }
        });


//        Task<Location> task = clientLOC.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                //Log.e("*******", "onSuccess");
//                if (location != null)
//                    //Log.e("*****", "location not null");
//
//                    mapFragment.getMapAsync(new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(GoogleMap googleMap) {
//                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                            MarkerOptions options = new MarkerOptions().position(latLng).title("You Are Here");
//
//                            Log.e("******", "lat and long: " + latLng.latitude + "," + latLng.longitude);
//
//                            //zoom to point on map
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                            //add marker
//                            googleMap.addMarker(options);
//                        }
//                    });
//            }
//        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                getLocation();
            else
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(33.59689, -117.658154);
        Log.e("******", "onMapReady: " + sydney.longitude + "," +  sydney.latitude );
        mMap.addMarker(new MarkerOptions().position(sydney).title("Nearby User"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}