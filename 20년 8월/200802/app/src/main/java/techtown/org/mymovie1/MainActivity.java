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
    ImageButton likeButton, dislikeButton;
    TextView likeCountView, dislikeCountView;
    int likeCount = 15, dislikeCount = 1;
    boolean likeState = false, dislikeState = false;
    ListView listView;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = (ImageButton) findViewById(R.id.like_button);
        dislikeButton = (ImageButton) findViewById(R.id.dislike_button);

        Button.OnClickListener button = new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.like_button:
                        likeCount();
                        break;
                    case R.id.dislike_button:
                        dislikeCount();
                        break;

                }
            }
        };
        likeButton.setOnClickListener(button);
        dislikeButton.setOnClickListener(button);
        likeCountView = (TextView) findViewById(R.id.likecount_textview);
        dislikeCountView = (TextView) findViewById(R.id.dislikecount_textview);

        listView = (ListView) findViewById(R.id.listView);
        scrollView = (ScrollView) findViewById(R.id.ScrollView);

        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kym71**","10분전","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","추천  0","신고하기",R.drawable.user1,R.id.ratings_ratingbar));
        adapter.addItem(new CommentItem("kym71**","10분전","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","추천  0","신고하기",R.drawable.user1,R.id.ratings_ratingbar));
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
            view.SetRatings(item.getRatings());
            view.SetComment(item.getComment());
            view.SetRecommend(item.getRecommend());
            view.SetWarning(item.getWarning());


            return view;
        }
    }

    public void likeCount(){
        if(likeState){
            decrLikeCount();
        } else if(dislikeState){
            dislikeState=false;
            decrDisLikeCount();
            incrLikeCount();
        } else {
            incrLikeCount();
        }
        likeState = !likeState;
    }

    public void dislikeCount(){
        if(dislikeState) {
            decrDisLikeCount();
        } else if(likeState) {
            likeState = false;
            decrLikeCount();
            incrDisLikeCount();
        } else{
            incrDisLikeCount();
        }
        dislikeState = !dislikeState;
    }

    public void incrLikeCount() {
        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));
        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
    }

    public void decrLikeCount() {
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));
        likeButton.setBackgroundResource(R.drawable.thumb_up);
    }


    public void incrDisLikeCount() {
        dislikeCount += 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));
        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrDisLikeCount() {
        dislikeCount -= 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));
        dislikeButton.setBackgroundResource(R.drawable.thumb_down);
    }
}