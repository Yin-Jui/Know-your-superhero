package com.example.knowyoursuperhero;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public class GPService extends Service {
    private Context appContext;
    private String TAG = "NearbyUser";
    private String uid = "";
    private double lat = 0, lon = 0;

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

        //grab logged in user's info
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore.getInstance().collection("users").document(uid).get().addOnCompleteListener(new OnCompleteListener() {
            public final void onComplete(@NotNull Task task) {
                Intrinsics.checkParameterIsNotNull(task, "task");
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = (DocumentSnapshot) task.getResult();
                    Users user = snapshot != null ? (Users) snapshot.toObject(Users.class) : null;
                    if (user != null) {
                        lat = user.getLocation().getLatitude();
                        lon = user.getLocation().getLongitude();
                        showToast();
                    }
                }
            }
        });
    }

    void showToast(){
        if(appContext != null){
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //check to see if user logged in
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {

                        //check to see if user even has updated location
                        if (lat != 0 & lon != 0) {

                            //Get all users' location
                            FirebaseFirestore.getInstance().collection("users")
                                    .get()
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Users Appuser = document.toObject(Users.class);

                                                //check to see if any in distance
                                                double distance = distance(lat, lon,
                                                        Appuser.getLocation().getLatitude(), Appuser.getLocation().getLongitude(), "M");

                                                //if distance isn't user(distance == 0) and within certain range
                                                if (distance != 0 & distance < 20) {
                                                    //Log.e("****", String.valueOf(distance));
                                                    Toast.makeText(appContext, "User nearby!", Toast.LENGTH_SHORT).show();
                                                    //should probably break after first found user
                                                }
                                            }
                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }
                                    });
                        }

                        //to make the handler call itself recursively every 6 sec
                        handler.postDelayed(this, 6000);
                    }
                }
            }, 2000);       //initial call takes 2 sec before running
        }
    }

    //different units K(ilometers) or M(iles)
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
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
