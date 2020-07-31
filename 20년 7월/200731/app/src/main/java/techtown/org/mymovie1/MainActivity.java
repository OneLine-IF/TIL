package techtown.org.mymovie1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CommentAdapter adapter;
    ImageButton likeButton, DislikeButton;
    TextView likeCountView, DislikeCountView;
    int likeCount = 15, DislikeCount = 1;
    boolean likeState = false, DislikeState = false;
    ListView listView;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = (ImageButton) findViewById(R.id.likeButton);
        DislikeButton = (ImageButton) findViewById(R.id.DislikeButton);

        likeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(likeState) {
                    decrLikeCount();
                } else {
                    if(DislikeState){
                        decrDisLikeCount();
                        incrLikeCount();
                    }
                    else {
                        incrLikeCount();
                    }

                }
                likeState = !likeState;
            }
        });
        DislikeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(DislikeState) {
                    decrDisLikeCount();
                } else {
                    if(likeState){
                        decrLikeCount();
                        incrDisLikeCount();
                    }
                    else{
                        incrDisLikeCount();
                    }

                }

                DislikeState = !DislikeState;
            }

        });
        likeCountView = (TextView) findViewById(R.id.likeCountView);
        DislikeCountView = (TextView) findViewById(R.id.DislikeCountView);

        listView = (ListView) findViewById(R.id.listView);
        scrollView = (ScrollView) findViewById(R.id.ScrollView);

        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kym71**","10분전","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","추천  0","신고하기",R.drawable.user1,R.id.Ratings));
        adapter.addItem(new CommentItem("kym71**","10분전","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","추천  0","신고하기",R.drawable.user1,R.id.Ratings));
        listView.setAdapter(adapter);


        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }

    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return items.size();
        }
        public void addItem(CommentItem item) {items.add(item);}

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommentItemView view = null;
            if(convertView == null) {
                view = new CommentItemView(getApplicationContext());
            } else{
                view = (CommentItemView) convertView;
            }
            CommentItem item = items.get(position);
            view.SetUserImage(item.getResId());
            view.SetUserId(item.getUserId());
            view.SetWriteTime(item.getWriteTime());
            view.SetRatings();
            view.SetComment(item.getComment());
            view.SetRecommend(item.getRecommend());
            view.SetWarning(item.getWarning());


            return view;
        }
    }





    public void incrLikeCount() {
        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));
        DislikeState=false; // 초기화를 시키지 않으면 버튼을 누른 상태의 값이 true로 남아있다가 false로 됐다가 혼선이 생기므로 매 버튼을 누를때마다 초기화해서 기능을 하게끔 해야함
        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
    }

    public void decrLikeCount() {
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));
        DislikeState=false;
        likeButton.setBackgroundResource(R.drawable.thumb_up);
    }

    public void incrDisLikeCount() {
        DislikeCount += 1;
        DislikeCountView.setText(String.valueOf(DislikeCount));
        likeState=false;
        DislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrDisLikeCount() {
        DislikeCount -= 1;
        DislikeCountView.setText(String.valueOf(DislikeCount));
        likeState=false;
        DislikeButton.setBackgroundResource(R.drawable.thumb_down);
    }



}