package com.tollgroup.controltower;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.utilities.Utilities;
import com.firebase.ui.FirebaseUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tollgroup.controltower.MainActivity;
import com.tollgroup.controltower.R;

/**
 * Created by pong on 2017/4/3.
 */

public class AlertService  extends Service {
    Context context;
    private FirebaseDatabase db;
    static String TAG = "FirebaseService";
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        db = FirebaseDatabase.getInstance();
        setupNotificationListener();
    }
    private void setupNotificationListener() {
    //create listener  to listen the DB change
        db.getReference().child("USERS")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if(dataSnapshot != null){
                            showNotification(context,"user","testing");
                        }
                    }
                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                     //   Utilities.log("onChildChanged",dataSnapshot);
                    }
                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    //    Utilities.log("onChildRemoved",dataSnapshot);
                    }
                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    //    Utilities.log("onChildMoved",dataSnapshot);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                     //   Utilities.log("onCancelled",databaseError);
                    }
                });
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private void showNotification(Context context, String notification, String notification_key){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("description")
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentText("content")
                .setAutoCancel(false);
        Intent backIntent = new Intent(context, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent intent = new Intent(context, MainActivity.class);
        /*  Use the notification type to switch activity to stack on the main activity*/
        if(1==1){
            intent = new Intent(context, MainActivity.class);
        }
        final PendingIntent pendingIntent = PendingIntent.getActivities(context, 900,
                new Intent[] {backIntent}, PendingIntent.FLAG_ONE_SHOT);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotificationManager =  (NotificationManager)context. getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
        /* Update firebase set notifcation with this key to 1 so it doesnt get pulled by our notification listener*/
        //flagNotificationAsSent(notification_key);
    }
}
