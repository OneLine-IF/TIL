package techtown.org.mymovie1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentMoviedetail extends Fragment {
    CommentAdapter adapter;
    ImageButton likeButton, dislikeButton;
    TextView likeCountView, dislikeCountView;
    int likeCount = 15, dislikeCount = 1;
    boolean likeState = false, dislikeState = false;
    ListView commentListview;
    ScrollView allcoverScrollview;
    Button writeButton,showAllButton;
    FragmentMovielist2 fragmentMovielist2;
    MainActivity activity;
    View header1,header2;
    Button showdetail3,showdetail2;
    private int btn,btn3;
    ImageView posterimageview;
    FragmentMovielist1 fragmentMovielist1;
    FragmentMoviedetail fragmentMoviedetail;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity.getSupportActionBar().setTitle("영화 목록");
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_moviedetail,container,false);


        likeButton = (ImageButton) rootView.findViewById(R.id.like_button);
        dislikeButton = (ImageButton) rootView.findViewById(R.id.dislike_button);
        posterimageview = (ImageView) rootView.findViewById(R.id.poster_imageview);

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
        likeCountView = (TextView) rootView.findViewById(R.id.likecount_textview);
        dislikeCountView = (TextView) rootView.findViewById(R.id.dislikecount_textview);

        commentListview = (ListView) rootView.findViewById(R.id.comment_listview);
        allcoverScrollview = (ScrollView) rootView.findViewById(R.id.allcover_scrollview);

        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kym71**","10분전","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","추천  0","신고하기",R.drawable.user1,R.id.ratings_ratingbar));
        adapter.addItem(new CommentItem("kym71**","10분전","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","추천  0","신고하기",R.drawable.user1,R.id.ratings_ratingbar));
        commentListview.setAdapter(adapter);


        commentListview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                allcoverScrollview.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        writeButton = (Button) rootView.findViewById(R.id.write_button);
        showAllButton = (Button) rootView.findViewById(R.id.showall_button);
        Button.OnClickListener buttons = new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                switch (view.getId()){
                    case R.id.write_button:
                        showWriteActivity();
                        break;
                    case R.id.showall_button:
                        showAllActivity();
                        break;
                }
            }
        };
        writeButton.setOnClickListener(buttons);
        showAllButton.setOnClickListener(buttons);

        if(getArguments() != null) {
            btn = getArguments().getInt("list1");
            switch (btn) {
                case 1:
                    posterimageview = (ImageView) rootView.findViewById(R.id.poster_imageview);
                    posterimageview.setImageResource(R.drawable.image11);
                    break;
                case 2:
                    posterimageview = (ImageView) rootView.findViewById(R.id.poster_imageview);
                    posterimageview.setImageResource(R.drawable.image22);
                    break;

            }
        }

        return rootView;
    }


    public void showWriteActivity(){
        Intent intent = new Intent(getContext(),WriteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                Intent.FLAG_ACTIVITY_SINGLE_TOP|
                Intent.FLAG_ACTIVITY_NO_HISTORY);


        startActivityForResult(intent, 101);
    }

    public void showAllActivity() {
        Intent intent = new Intent(getContext(),ShowAllActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                Intent.FLAG_ACTIVITY_SINGLE_TOP|
                Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putParcelableArrayListExtra("CommentItem",adapter.items);
        startActivityForResult(intent,102);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){
            if (intent != null) {
                String comments = intent.getStringExtra("comments");
                float rating = intent.getFloatExtra("rating", 0.0f);
                adapter.addItem(new CommentItem("kym71**","10분전",comments,"추천  0","신고하기",R.drawable.user1,rating));
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 102) {
            if (intent != null) {
                adapter.items = intent.getParcelableArrayListExtra("CommentItem");
            }
        }

    }



    public class CommentAdapter extends BaseAdapter {
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
                view = new CommentItemView(getContext());
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