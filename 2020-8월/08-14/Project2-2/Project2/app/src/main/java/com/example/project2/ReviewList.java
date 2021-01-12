package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReviewList extends AppCompatActivity {
ListView listView;
ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_review_list);

        listView = (ListView)findViewById(R.id.listView2);
        //Adapter adapter = ReViewAdapter();
        adapter = new ListAdapter();

        //adapter.addItem(new reViewitem("적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        //adapter.addItem(new reViewitem("적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        //adapter.addItem(new reViewitem("아직  안 봄."));
        //adapter.addItem(new reViewitem("그냥 그럼."));



        Intent passedIntent = getIntent();
        processIntent(passedIntent);


        adapter.items2 = passedIntent.getParcelableArrayListExtra("key");


        listView.setAdapter(adapter);
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

    class ListAdapter extends BaseAdapter{
        ArrayList<reViewitem> items2 = new ArrayList<reViewitem>();

        @Override
        public int getCount() {
            return items2.size();
        }

        public void addItem(reViewitem item){
            items2.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items2.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            CommentItemView view2 = new CommentItemView(getApplicationContext());
            reViewitem item = items2.get(i);
            view2.setName(item.getReView());
            return view2;
        }
    }


}
//adapter = new ReViewAdapter();intent2.putParcelableArrayListExtra("key", (ArrayList<? extends Parcelable>) adapter.items)