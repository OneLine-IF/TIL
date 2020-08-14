package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ReviewList extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        listView = (ListView)findViewById(R.id.listView2);
        MainActivity.ReViewAdapter adapter = new MainActivity.ReViewAdapter();

        Intent intent = getIntent();
        adapter.items = (ArrayList<reViewitem>) intent.getParcelableArrayListExtra("key");

        listView.setAdapter(adapter);

        Button reVIewWriting2 = (Button)findViewById(R.id.reVIewWriting2);
        reVIewWriting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CommentWriteActivity.class);
                startActivityForResult(intent,1001);
            }
        });
    }
}
//adapter = new ReViewAdapter();intent2.putParcelableArrayListExtra("key", (ArrayList<? extends Parcelable>) adapter.items)