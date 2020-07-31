package com.example.summary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CommentAdapter adapter;

    TextView likeCountView;
    TextView dislikeCountView;
    Button likeButton;
    Button dislikeButton;

    int likeCount = 15;
    int dislikeCount = 1;
    boolean likeState = false;
    boolean dislikeState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = (Button) findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(likeState){
                    decrLikeCount();
                } else if(dislikeState){
                    incrLikeCount();
                    decrdisLikeCount();
                    dislikeState = !dislikeState;
                } else {
                    incrLikeCount();
                }
                likeState = !likeState;
            }
        });

        dislikeButton = (Button) findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dislikeState){
                    decrdisLikeCount();
                } else if(likeState) {
                    incrdisLikeCount();
                    decrLikeCount();
                    likeState = !likeState;
                } else {
                    incrdisLikeCount();
                }
                dislikeState = !dislikeState;
            }
        });

        likeCountView = (TextView) findViewById(R.id.likeCountView);
        dislikeCountView = (TextView) findViewById(R.id.dislikeCountView);

        ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kwh05**","15분전", "적당히 재미없다. 오랜만에 잠 오는 영화 봤네요.",R.drawable.user1));
        adapter.addItem(new CommentItem("kwh05**","15분전", "적당히 재미없다. 오랜만에 잠 오는 영화 봤네요.",R.drawable.user1));

        listView.setAdapter(adapter);
    }

    public void incrLikeCount(){
        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
    }

    public void decrLikeCount(){
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.thumb_up);
    }

    public void incrdisLikeCount(){
        dislikeCount += 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrdisLikeCount(){
        dislikeCount -= 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.thumb_down);
    }

    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() { // 몇개의 아이템이 있는지
            return items.size();
        }

        public void addItem(CommentItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        // 뷰는 레이아웃으로 구성이 돼야 될 거에요. 그러면 레이아웃에 해당하는 거를 부분 화면으로
        // 하나 정의를 하고 이걸 이용해서 객체를 만든 다음 데이터를 설정하고 리턴을 해주는 게 가장 좋은 방법 중 하나!
        @Override
        public View getView(int position, View ConvertView, ViewGroup parent) {
            CommentItemView view = null;
            if(ConvertView == null) { // 쓸데없는 메모리 낭비를 막기 위해서
                view = new CommentItemView(getApplicationContext());
            } else {
                view = (CommentItemView) ConvertView;
            }
            CommentItem item = items.get(position); // ArrayList 에서 해당 인덱스(position)의 값 가져온다.
            view.setId(item.getId());
            view.setTime(item.getTime());
            view.setComment(item.getComment());
            view.setImage(item.getResId());

            return view;
        }
    }

}