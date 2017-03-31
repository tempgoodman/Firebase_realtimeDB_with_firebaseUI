package com.tollgroup.controltower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.FirebaseUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
private ListView mListView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.ListView);

        DatabaseReference dbf = FirebaseDatabase.getInstance().getReferenceFromUrl("ttps://fir-a0f69.firebaseio.com/USERS");

        FirebaseListAdapter<String> fbla = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                dbf
        ) {
            @Override
            protected void populateView(View view, String s, int i) {
                TextView textview = (TextView) view.findViewById(android.R.id.text1);
                textview.setText(s);
            }
        };
     mListView.setAdapter(fbla);

    }
}
