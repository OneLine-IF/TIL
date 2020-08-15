package com.example.project2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView likeCountView;
    TextView dislikeCountView;
    Button likeButton;
    Button dislikeButton;
    int likeCount = 15;
    int dislikeCount = 1;
    ReViewAdapter adapter;
    ScrollView scrollView;
    RatingBar ratingBar2;
    TextView outputView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.ListView);


        Button all = (Button)findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListView();
            }
        });



        ratingBar2 = (RatingBar)findViewById(R.id.ratingBar2);
        outputView = (TextView)findViewById(R.id.writting);

        Button reviewWriting = (Button)findViewById(R.id.reVIewWriting);

        reviewWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommentWriteActivity();
            }
        });

        final ListView listView = (ListView) findViewById(R.id.ListView);

        adapter = new ReViewAdapter();

        listView.setAdapter(adapter);

        adapter.addItem(new reViewitem("적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        adapter.addItem(new reViewitem("적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        adapter.addItem(new reViewitem("아직  안 봄."));
        adapter.addItem(new reViewitem("그냥 그럼."));


        likeCountView = (TextView) findViewById(R.id.likeCountView);


        likeButton = (Button) findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeButton.setSelected(!view.isSelected());

                if(likeButton.isSelected()){
                    if(dislikeButton.isSelected()) {
                        dislikeButton.callOnClick();
                        int count = Integer.parseInt(likeCountView.getText().toString());
                        likeCountView.setText(Integer.toString(count+=1));
                        Toast.makeText(getApplicationContext(), "좋아요 on", Toast.LENGTH_SHORT).show();
                    }
                    else if(!dislikeButton.isSelected()){
                        int count2 = Integer.parseInt(likeCountView.getText().toString());
                        likeCountView.setText(Integer.toString(count2+=1));
                        Toast.makeText(getApplicationContext(), "좋아요 on", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    int count = Integer.parseInt(likeCountView.getText().toString());
                    likeCountView.setText(Integer.toString(count-=1));
                    Toast.makeText(getApplicationContext(), "좋아요 off", Toast.LENGTH_SHORT).show();
                }

            }
        });


        dislikeButton = (Button) findViewById(R.id.dislikeButton);

        dislikeCountView = (TextView) findViewById(R.id.dislikeCountView);

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dislikeButton.setSelected(!view.isSelected());

                if(dislikeButton.isSelected()){
                    if(likeButton.isSelected()) {
                        likeButton.callOnClick();
                        int count = Integer.parseInt(dislikeCountView.getText().toString());
                        dislikeCountView.setText(Integer.toString(count+=1));
                        Toast.makeText(getApplicationContext(), "싫어요 on", Toast.LENGTH_SHORT).show();
                    }
                    else if(!likeButton.isSelected()){
                        int count2 = Integer.parseInt(dislikeCountView.getText().toString());
                        dislikeCountView.setText(Integer.toString(count2+=1));
                        Toast.makeText(getApplicationContext(), "싫어요 on", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    int count = Integer.parseInt(dislikeCountView.getText().toString());
                    dislikeCountView.setText(Integer.toString(count-=1));
                    Toast.makeText(getApplicationContext(), "싫어요 off", Toast.LENGTH_SHORT).show();
                }

            }
        });


        scrollView = (ScrollView)findViewById(R.id.ScrollView);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


    }

    public void incrLikeCount() {
        likeCount +=1;

        likeCountView.setText(String.valueOf(likeCount));//setText 에는 숫자를 넣을 수 없으므로 String.valueOf(likeCount)이렇게 변환시키기.


        Toast.makeText(getApplicationContext(), "좋아요 on", Toast.LENGTH_SHORT).show();
    }

    public void decrLikeCount() {
        likeCount -=1;

        likeCountView.setText(String.valueOf(likeCount));


        Toast.makeText(getApplicationContext(), "좋아요 off", Toast.LENGTH_SHORT).show();
    }

    public void incrDislikeCount() {
        dislikeCount +=1;
        dislikeCountView.setText(String.valueOf(dislikeCount));
        Toast.makeText(getApplicationContext(), "싫어요 on", Toast.LENGTH_SHORT).show();
    }

    public void decrDislikeCount() {
        dislikeCount -=1;
        dislikeCountView.setText(String.valueOf(dislikeCount));
        Toast.makeText(getApplicationContext(), "싫어요 off", Toast.LENGTH_SHORT).show();
    }

    class ReViewAdapter extends BaseAdapter {
        ArrayList<reViewitem> items = new ArrayList<reViewitem>();

        @Override
        public int getCount() {
            return items.size();//아이템의 갯수를 알려줌
        }

        public void addItem(reViewitem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i); //몇 번째 아이템인지 알려줌(i)
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            CommentItemView view = new CommentItemView(getApplicationContext());

            reViewitem item = items.get(i);
            view.setName(item.getReView());

            return view;
        }
    }

    public void showCommentWriteActivity(){
        //float rating = ratingBar2.getRating();//4.1 처럼 사용자가 바꾼 값을 가져올 수 있음., 이 Rating 을 부가데이터에 넘겨서 전달.
        Intent intent = new Intent(getApplicationContext(),CommentWriteActivity.class);
        //intent.putExtra("rating",rating);//이렇게 부가 데이터를 넣어주면, CommentWriteActivity 로 전달이 됨.
        startActivityForResult(intent,101);//응답을 받을 거라 ForResult 로. 위에서 만든 Intent 를 넣어줌.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==101){
            if(intent!=null){
                String contents = intent.getStringExtra("contents");
                outputView.setText(contents);
            }
        }
    }

    public void showListView(){
        Intent intent2 = new Intent(getApplicationContext(),ReviewList.class);
        ArrayList list = new ArrayList();
        list.add(listView);

        reViewitem data = new reViewitem("simple");
        intent2.putExtra("data",data);

        intent2.putParcelableArrayListExtra("key", adapter.items);
        startActivityForResult(intent2,102);
    }

}