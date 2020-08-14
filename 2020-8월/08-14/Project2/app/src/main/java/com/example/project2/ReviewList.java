package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReviewList extends AppCompatActivity {
ListView listView;
MainActivity.ReViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_review_list);

        listView = (ListView)findViewById(R.id.listView2);
        //Adapter adapter = ReViewAdapter();
        listView.setAdapter(adapter);


        Intent passedIntent = getIntent();
        processIntent(passedIntent);

        //((MainActivity.ReViewAdapter) adapter).items = passedIntent.getParcelableArrayListExtra("key");

        //listView.setAdapter((ListAdapter) adapter);

        Button reVIewWriting2 = (Button)findViewById(R.id.reVIewWriting2);
        reVIewWriting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CommentWriteActivity.class);
                startActivityForResult(intent,101);
            }
        });
    }

    private void processIntent(Intent intent){
        reViewitem data = (reViewitem)intent.getParcelableExtra("data");
    }

}
//adapter = new ReViewAdapter();intent2.putParcelableArrayListExtra("key", (ArrayList<? extends Parcelable>) adapter.items)