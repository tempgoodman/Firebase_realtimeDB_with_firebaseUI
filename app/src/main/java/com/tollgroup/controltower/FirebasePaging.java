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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.firebase.database.Query;

import java.util.ArrayList;

//Firebase UI  , no paging
public class FirebasePaging extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase db;
    private Intent intent;
    private ArrayList<ServerStatusLog> allServerStatusLog;
    private RCPagingAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paging);
        context = this.context;
        db = FirebaseDatabase.getInstance();
        mRecyclerView = (RecyclerView) findViewById(R.id.receyclerview);
        allServerStatusLog = new ArrayList<ServerStatusLog>();
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setLayoutManager(llm);

//Get Data from Firebase realtime DB
//        DatabaseReference dbf = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-a0f69.firebaseio.com");
        //       dbf = FirebaseDatabase.getInstance().getReference();
        //       Query mQuery = dbf.child("ServerStatus").orderByChild("Timestamp").limitToLast(20);
        //       mQuery.addChildEventListener(new ChildEventListener() {
        db.getReference().child("ServerStatus").orderByChild("Timestamp").limitToLast(30)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                /*for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    ServerStatusLog ssl = singleSnapshot.getValue(ServerStatusLog.class);
                    allServerStatusLog.add(ssl);
                }*/
                        ServerStatusLog ssl = dataSnapshot.getValue(ServerStatusLog.class);
                        allServerStatusLog.add(0, ssl);
                        adapter = new RCPagingAdapter(context, allServerStatusLog);

                        mRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                 }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        intent = new Intent(FirebasePaging.this, AlertService.class);
        startService(intent);
    }

    public void sendMessage(View view) {
        Intent intent1 = new Intent(FirebasePaging.this, MainActivity.class);
        startActivity(intent1);
    }
}