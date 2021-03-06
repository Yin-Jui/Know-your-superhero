package com.example.knowyoursuperhero;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.maps.model.LatLng;

public class GPService extends Service {
    private Context appContext;

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        appContext = getBaseContext();
//        showToast();
//        return Service.START_NOT_STICKY;    //tells system to not rerun service if has stopped
//    }


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getBaseContext();
        showToast();
    }

    void showToast(){
        if(appContext != null){
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //check to see if user logged in
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user != null) {

                        //TODO: grab all users location from database
                        //mDatabase mDatabase = FirebaseDatabase.getInstance().getReference();

                        //user1 is logged-in user, user 2 is everyone else from database
                        LatLng user1 = new LatLng(32.9697, -96.80322);
                        LatLng user2 = new LatLng(29.46786, -98.53506);
                        double distance = distance(user1.latitude, user1.longitude,
                                user2.latitude, user2.longitude, "M");

                        //if distance isn't user(distance == 0) and within certain range
                        if (distance > 0 & distance < 5)
                            Toast.makeText(appContext, "User logged in", Toast.LENGTH_SHORT).show();
                    }

                    //to make the handler call itself recursively every 4.5 sec
                    handler.postDelayed(this, 4500);
                }
            });
        }
    }

    //different units K(ilometers) or M(iles)
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K"))
                dist = dist * 1.609344;

            return (dist);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
