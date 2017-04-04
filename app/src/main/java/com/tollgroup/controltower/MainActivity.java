package com.tollgroup.controltower;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.firebase.client.utilities.Utilities;
import com.firebase.ui.FirebaseUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//Firebase UI  , no paging
public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.ListView);
//Get Data from Firebase realtime DB
        DatabaseReference dbf = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-a0f69.firebaseio.com/ServerStatus");
        FirebaseListAdapter<String> fbla = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                dbf
        ) {
            @Override
            protected String parseSnapshot(DataSnapshot snapshot) {
                return snapshot.child("LogDetail").getValue(String.class);
            }

            @Override
            protected void populateView(View view, String s, int i) {
                TextView textview = (TextView) view.findViewById(android.R.id.text1);
                textview.setText(s);
            }
        };
        //bind data to listview
        mListView.setAdapter(fbla);
        //Start service to listen  realtime db
        intent = new Intent(MainActivity.this, AlertService.class);
        startService(intent);
    }
    public void sendMessage(View view)
    {
        Intent intent1 = new Intent(MainActivity.this, FirebasePaging.class);
        startActivity(intent1);
    }
}