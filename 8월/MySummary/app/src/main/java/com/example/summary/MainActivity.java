package com.example.summary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;

    CommentAdapter adapter;
    ArrayList<CommentItem> items; // 전역 변수로 바꾼 것

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

        items = new ArrayList<CommentItem>();// 여기서 정의

        likeButton = (Button) findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(likeState){
                    decrCount(likeButton);
                } else if(dislikeState){
                    incrCount(likeButton);
                    decrCount(dislikeButton);
                    dislikeState = !dislikeState;
                } else {
                    incrCount(likeButton);
                }
                likeState = !likeState;
            }
        });

        dislikeButton = (Button) findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dislikeState){
                    decrCount(dislikeButton);
                } else if(likeState) {
                    incrCount(dislikeButton);
                    decrCount(likeButton);
                    likeState = !likeState;
                } else {
                    incrCount(dislikeButton);
                }
                dislikeState = !dislikeState;
            }
        });

        likeCountView = (TextView) findViewById(R.id.likeCountView);
        dislikeCountView = (TextView) findViewById(R.id.dislikeCountView);

        ListView listView = (ListView) findViewById(R.id.listView);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kwh05**","15분 전", "적당히 재미없다. 오랜만에 잠 오는 영화 봤네요.",R.drawable.user1, (float) 1.5));
        adapter.addItem(new CommentItem("kwh05**","15분 전", "적당히 재미없다. 오랜만에 잠 오는 영화 봤네요.",R.drawable.user1, (float) 2.5));
        adapter.addItem(new CommentItem("kwh05**","15분 전", "적당히 재미없다. 오랜만에 잠 오는 영화 봤네요.",R.drawable.user1, (float) 3.5));
        adapter.addItem(new CommentItem("kwh05**","15분 전", "적당히 재미없다. 오랜만에 잠 오는 영화 봤네요.",R.drawable.user1, (float) 4.5));

        listView.setAdapter(adapter);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        Button writeButton = (Button) findViewById(R.id.writeButton);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        Button showAllButton = (Button) findViewById(R.id.showAllButton);
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentReadActivity.class);

                intent.putExtra("items", items);

                startActivityForResult(intent, 102);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101) {
            if(intent != null) {
                String contents = intent.getStringExtra("contents");
                Float rating = intent.getFloatExtra("rating",0.0f);

                Toast.makeText(this, "넘어온 별점 개수 : " + rating, Toast.LENGTH_LONG).show();

                adapter.addItem(new CommentItem("kwh05**","방금 전", contents,R.drawable.user1, rating));
                adapter.notifyDataSetChanged();// 이거 없으면 안바뀐다 ㄷㄷ;;
            }
        }
    }

    public void incrCount(Button button){
        if(button == likeButton) {
            likeCount += 1;
            likeCountView.setText(String.valueOf(likeCount));

            likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        } else {
            dislikeCount += 1;
            dislikeCountView.setText(String.valueOf(dislikeCount));

            dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        }
    }

    public void decrCount(Button button){
        if(button == likeButton) {
            likeCount -= 1;
            likeCountView.setText(String.valueOf(likeCount));

            likeButton.setBackgroundResource(R.drawable.ic_thumb_up);
        } else {
            dislikeCount -= 1;
            dislikeCountView.setText(String.valueOf(dislikeCount));

            dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down);
        }
    }

    class CommentAdapter extends BaseAdapter {
        // ArrayList<CommentItem> items;

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
            view.setRatingBar(item.getRating());

            return view;
        }
    }

}