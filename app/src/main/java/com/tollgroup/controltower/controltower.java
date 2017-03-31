package com.tollgroup.controltower;
import android.app.Application;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pong on 2017/3/31.
 */

public class controltower extends Application {
    @Override
    public void onCreate(){
        super.onCreate();;
        //      Firebase.setAndroidContext(this);
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}