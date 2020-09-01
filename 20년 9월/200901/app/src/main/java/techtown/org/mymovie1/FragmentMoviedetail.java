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
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import techtown.org.mymovie1.data.CommentList;
import techtown.org.mymovie1.data.CommentListInfo;
import techtown.org.mymovie1.data.MovieDetail;
import techtown.org.mymovie1.data.MovieDetailInfo;
import techtown.org.mymovie1.data.MovieList;
import techtown.org.mymovie1.data.ResponseCheck;

import static java.lang.Float.parseFloat;

public class FragmentMoviedetail extends Fragment {
    CommentAdapter adapter;
    ImageButton likeButton, dislikeButton;
    TextView likeCountView, dislikeCountView,detailmovienametextview,opendaytextview,genretimetextview,reservationtextview,userratingimageview,audiencetextview,directortextview,actortextview,synopsistextview;
    RatingBar userratingbar,commentrating;
    int likeCount, dislikeCount;
    boolean likeState = false, dislikeState = false;
    ListView commentListview;
    ScrollView allcoverScrollview;
    Button writeButton,showAllButton;
    MainActivity activity;
    public static int detailid;
    ImageView posterimageview,gradeimageview;
    MovieDetailInfo movieDetailInfo;
    MovieDetail movieDetail;
    CommentList commentList;
    CommentListInfo commentListInfo;


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
        detailmovienametextview = (TextView) rootView.findViewById(R.id.detailmoviename_textview);
        gradeimageview = (ImageView) rootView.findViewById(R.id.grade_imageview);
        opendaytextview = (TextView) rootView.findViewById(R.id.openday_textview);
        genretimetextview = (TextView) rootView.findViewById(R.id.genretime_textview);
        reservationtextview = (TextView) rootView.findViewById(R.id.reservation_textview);
        userratingbar = (RatingBar) rootView.findViewById(R.id.user_ratingbar);
        userratingimageview = (TextView) rootView.findViewById(R.id.userrating_imageview);
        audiencetextview = (TextView) rootView.findViewById(R.id.audience_textview);
        directortextview = (TextView) rootView.findViewById(R.id.director_textview);
        actortextview = (TextView) rootView.findViewById(R.id.actor_textview);
        synopsistextview = (TextView) rootView.findViewById(R.id.synopsis_textview);
        commentrating = (RatingBar) rootView.findViewById(R.id.ratings_ratingbar);
        if(getArguments()!=null) {
            detailid = getArguments().getInt("list1");
        }

        requestMovieDetail();

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

        requestCommentList();


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


        return rootView;
    }

    public void sendImageRequest() {
        String url = movieDetailInfo.thumb;

        ImageLoadTask task = new ImageLoadTask(url,posterimageview);
        task.execute();
    }

    public void requestCommentList() {
        String url = "http://"  + AppHelper.host + ":" + AppHelper.port +"/movie/readCommentList";

        if(detailid == 1) {
            url += "?" + "id=1";
        }
        if(detailid == 2) {
            url += "?" + "id=2";
        }
        if(detailid == 3) {
            url += "?" + "id=3";
        }
        if(detailid == 4) {
            url += "?" + "id=4";
        }
        if(detailid == 5) {
            url += "?" + "id=5";
        }
        final StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processComment(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    public void requestMovieDetail() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovie";

        if(detailid == 1) {
            url += "?" + "id=1";
        }
        if(detailid == 2){
            url += "?" + "id=2";
        }
        if (detailid == 3) {
            url += "?" + "id=3";
        }
        if (detailid == 4) {
            url += "?" + "id=4";
        }
        if (detailid == 5) {
            url += "?" + "id=5";
        }

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                        sendImageRequest();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

    }

    public void processComment(String response) {
        Gson gson = new Gson();

        ResponseCheck check = gson.fromJson(response, ResponseCheck.class);
        if(check.code == 200) {
            commentList = gson.fromJson(response,CommentList.class);

            if(detailid == 1) {

                adapter = new CommentAdapter();
                for(int i = 0; i < commentList.result.size(); i++) {
                    commentListInfo = commentList.result.get(i);
                    adapter.addItem(new CommentItem(commentListInfo.writer,"10분전",commentListInfo.contents,"추천  0","신고하기",R.drawable.user1,commentListInfo.rating));
                    commentListview.setAdapter(adapter);
                }
            }
            if(detailid == 2) {
                adapter = new CommentAdapter();
                for(int i = 0; i < commentList.result.size(); i++) {
                    commentListInfo = commentList.result.get(i);
                    adapter.addItem(new CommentItem(commentListInfo.writer,"10분전",commentListInfo.contents,"추천  0","신고하기",R.drawable.user1,commentListInfo.rating));
                    commentListview.setAdapter(adapter);
                }
            }
            if(detailid == 3) {
                adapter = new CommentAdapter();
                for(int i = 0; i < commentList.result.size(); i++) {
                    commentListInfo = commentList.result.get(i);
                    adapter.addItem(new CommentItem(commentListInfo.writer,"10분전",commentListInfo.contents,"추천  0","신고하기",R.drawable.user1,commentListInfo.rating));
                    commentListview.setAdapter(adapter);
                }
            }
            if(detailid == 4) {
                adapter = new CommentAdapter();
                for(int i = 0; i < commentList.result.size(); i++) {
                    commentListInfo = commentList.result.get(i);
                    adapter.addItem(new CommentItem(commentListInfo.writer,"10분전",commentListInfo.contents,"추천  0","신고하기",R.drawable.user1,commentListInfo.rating));
                    commentListview.setAdapter(adapter);
                }
            }
            if(detailid == 5) {
                adapter = new CommentAdapter();
                for(int i = 0; i < commentList.result.size(); i++) {
                    commentListInfo = commentList.result.get(i);
                    adapter.addItem(new CommentItem(commentListInfo.writer,"10분전",commentListInfo.contents,"추천  0","신고하기",R.drawable.user1,commentListInfo.rating));
                    commentListview.setAdapter(adapter);
                }
            }
        }
    }

    public void processResponse(String response) {
        Gson gson = new Gson();

        ResponseCheck check = gson.fromJson(response, ResponseCheck.class);
        if(check.code == 200) {
            movieDetail = gson.fromJson(response,MovieDetail.class);

            if(detailid == 1) {
                movieDetailInfo = movieDetail.result.get(0);
                detailmovienametextview.setText(movieDetailInfo.title);
                gradeimageview.setImageResource(R.drawable.ic_15);
                opendaytextview.setText(movieDetailInfo.date.replaceAll("-",".") +" 개봉");
                genretimetextview.setText(movieDetailInfo.genre +" / " +movieDetailInfo.duration + "분");
                likeCountView.setText(Integer.toString(movieDetailInfo.like));
                likeCount = movieDetailInfo.like;
                dislikeCountView.setText(Integer.toString(movieDetailInfo.dislike));
                dislikeCount = movieDetailInfo.dislike;
                reservationtextview.setText(movieDetailInfo.reservation_grade + "위" +" " +movieDetailInfo.reservation_rate+"%");
                userratingbar.setRating(movieDetailInfo.user_rating);
                userratingimageview.setText("6.4");
                audiencetextview.setText(Integer.toString(movieDetailInfo.audience)+"명");
                directortextview.setText(movieDetailInfo.director);
                actortextview.setText(movieDetailInfo.actor);
                synopsistextview.setText(movieDetailInfo.synopsis);

                
            }
            if(detailid == 2) {
                movieDetailInfo = movieDetail.result.get(0);
                detailmovienametextview.setText(movieDetailInfo.title);
                gradeimageview.setImageResource(R.drawable.ic_12);
                opendaytextview.setText(movieDetailInfo.date.replaceAll("-",".") +" 개봉");
                genretimetextview.setText(movieDetailInfo.genre +" / " +movieDetailInfo.duration + "분");
                likeCountView.setText(Integer.toString(movieDetailInfo.like));
                likeCount = movieDetailInfo.like;
                dislikeCountView.setText(Integer.toString(movieDetailInfo.dislike));
                dislikeCount = movieDetailInfo.dislike;
                reservationtextview.setText(movieDetailInfo.reservation_grade + "위" +" " +movieDetailInfo.reservation_rate+"%");
                userratingbar.setRating(movieDetailInfo.user_rating);
                userratingimageview.setText("7.9");
                audiencetextview.setText(Integer.toString(movieDetailInfo.audience)+"명");
                directortextview.setText(movieDetailInfo.director);
                actortextview.setText(movieDetailInfo.actor);
                synopsistextview.setText(movieDetailInfo.synopsis);
            }
            if(detailid == 3) {
                movieDetailInfo = movieDetail.result.get(0);
                detailmovienametextview.setText(movieDetailInfo.title);
                gradeimageview.setImageResource(R.drawable.ic_12);
                opendaytextview.setText(movieDetailInfo.date.replaceAll("-",".") +" 개봉");
                genretimetextview.setText(movieDetailInfo.genre +" / " +movieDetailInfo.duration + "분");
                likeCountView.setText(Integer.toString(movieDetailInfo.like));
                likeCount = movieDetailInfo.like;
                dislikeCountView.setText(Integer.toString(movieDetailInfo.dislike));
                dislikeCount = movieDetailInfo.dislike;
                reservationtextview.setText(movieDetailInfo.reservation_grade + "위" +" " +movieDetailInfo.reservation_rate+"%");
                userratingbar.setRating(movieDetailInfo.user_rating);
                userratingimageview.setText("6.1");
                audiencetextview.setText(Integer.toString(movieDetailInfo.audience)+"명");
                directortextview.setText(movieDetailInfo.director);
                actortextview.setText(movieDetailInfo.actor);
                synopsistextview.setText(movieDetailInfo.synopsis);
            }
            if(detailid == 4) {
                movieDetailInfo = movieDetail.result.get(0);
                detailmovienametextview.setText(movieDetailInfo.title);
                gradeimageview.setImageResource(R.drawable.ic_15);
                opendaytextview.setText(movieDetailInfo.date.replaceAll("-",".") +" 개봉");
                genretimetextview.setText(movieDetailInfo.genre +" / " +movieDetailInfo.duration + "분");
                likeCountView.setText(Integer.toString(movieDetailInfo.like));
                likeCount = movieDetailInfo.like;
                dislikeCountView.setText(Integer.toString(movieDetailInfo.dislike));
                dislikeCount = movieDetailInfo.dislike;
                reservationtextview.setText(movieDetailInfo.reservation_grade + "위" +" " +movieDetailInfo.reservation_rate+"%");
                userratingbar.setRating(movieDetailInfo.user_rating);
                userratingimageview.setText("7.3");
                audiencetextview.setText(Integer.toString(movieDetailInfo.audience)+"명");
                directortextview.setText(movieDetailInfo.director);
                actortextview.setText(movieDetailInfo.actor);
                synopsistextview.setText(movieDetailInfo.synopsis);
            }
            if(detailid == 5) {
                movieDetailInfo = movieDetail.result.get(0);
                detailmovienametextview.setText(movieDetailInfo.title);
                gradeimageview.setImageResource(R.drawable.ic_19);
                opendaytextview.setText(movieDetailInfo.date.replaceAll("-",".") +" 개봉");
                genretimetextview.setText(movieDetailInfo.genre +" / " +movieDetailInfo.duration + "분");
                likeCountView.setText(Integer.toString(movieDetailInfo.like));
                likeCount = movieDetailInfo.like;
                dislikeCountView.setText(Integer.toString(movieDetailInfo.dislike));
                dislikeCount = movieDetailInfo.dislike;
                reservationtextview.setText(movieDetailInfo.reservation_grade + "위" +" " +movieDetailInfo.reservation_rate+"%");
                userratingbar.setRating(movieDetailInfo.user_rating);
                userratingimageview.setText("6.1");
                audiencetextview.setText(Integer.toString(movieDetailInfo.audience)+"명");
                directortextview.setText(movieDetailInfo.director);
                actortextview.setText(movieDetailInfo.actor);
                synopsistextview.setText(movieDetailInfo.synopsis);
            }
        }
    }

    public void postComment() {
        String url = "http://"  + AppHelper.host + ":" + AppHelper.port +"/movie/createComment";
        if(FragmentMoviedetail.detailid == 1) {
            url += "?" + "id=1";
        }
        if(FragmentMoviedetail.detailid == 2) {
            url += "?" + "id=2";
        }
        if(FragmentMoviedetail.detailid == 3) {
            url += "?" + "id=3";
        }
        if(FragmentMoviedetail.detailid == 4) {
            url += "?" + "id=4";
        }
        if(FragmentMoviedetail.detailid == 5) {
            url += "?" + "id=5";
        }



        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("check","post complete");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("check","post fail");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("comments",WriteActivity.comments);
                params.put("rating",WriteActivity.rate);
                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
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
                float rating = intent.getFloatExtra("rating",0.0f);
                adapter.addItem(new CommentItem("kym71**","10분전",comments,"추천  0","신고하기",R.drawable.user1,rating));
                postComment();
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