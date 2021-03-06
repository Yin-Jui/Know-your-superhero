package com.example.knowyoursuperhero;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

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
                    Toast.makeText(appContext, "Found Nearby User!", Toast.LENGTH_SHORT).show();

                    //to make the handler call itself recursively every 2 sec
                    handler.postDelayed(this, 4500);
                }
            });
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
