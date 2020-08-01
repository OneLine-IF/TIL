package com.example.summary2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView likeCountView;
    Button likeButton;

    int likeCount = 1;
    boolean likeState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = (Button) findViewById(R.id.likeButton); // 보통 버튼은 다른 메서드에서 사용하는 경우가 적다.
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(likeState){
                    decrLikeCount();
                } else {
                    incrLikeCount();
                }

                likeState = !likeState;
            }
        });

        likeCountView = (TextView) findViewById(R.id.likeCountView); // 텍스트뷰는 다른 메서드에서도 찾을 수 있도록 전역변수로!

        ListView listView = (ListView) findViewById(R.id.listView);
        CommentAdapter adapter = new CommentAdapter();
        listView.setAdapter(adapter);

    }

    class CommentAdapter extends BaseAdapter {


    }
    public void incrLikeCount(){
        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.thumb_up_selector);
    }

    public void decrLikeCount(){
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.thumb_up_selector);
    }
}