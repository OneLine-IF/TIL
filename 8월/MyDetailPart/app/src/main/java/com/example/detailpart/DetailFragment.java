package com.example.detailpart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.detailpart.data.MovieInfo;
import com.example.detailpart.data.MovieList;
import com.example.detailpart.data2.CommentInfo;
import com.example.detailpart.data2.CommentList;
import com.example.detailpart.data2.ResponseInfo2;
import com.google.gson.Gson;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailFragment extends Fragment {
    MainActivity activity;

    // 보통 다른 메서드 혹은 내부 클래스에서 사용해야 하는 경우 전역 변수 형태로 정의
    ScrollView scrollView;

    CommentAdapter adapter;
    ArrayList<CommentItem> items;// 어뎁터에 넣어줄 CommentItem 자료형의 ArrayList

    TextView likeCountView;
    TextView dislikeCountView;
    Button likeButton;
    Button dislikeButton;

    int likeCount = 15;
    int dislikeCount = 1;
    boolean likeState = false;
    boolean dislikeState = false;

    int index;

    ImageView imageView;

    TextView title;
    ImageView grade;
    TextView date;
    TextView genreDuration;
    TextView reservation_gradeRate;
    RatingBar rb;
    TextView audience_rating;
    TextView audience;
    TextView synopsis;
    TextView director;
    TextView actor;

    String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList" + "?" + "id=";

    ArrayList<CommentInfo> comments = new ArrayList<CommentInfo>();

    ListView listView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity.toolbarText.setText("영화 목록");
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.detail_fragment, container, false);

        // 액티비티에서 번들에 담아 보내준 인덱스값 받기
        Bundle bundle = getArguments();
        if(bundle != null) {
            index = bundle.getInt("index2");
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 한줄평 코멘트 데이터 가져오기 관련 코드
        AppHelper.requestQueue = Volley.newRequestQueue(activity.getApplicationContext());
        requestCommentList(index+1);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 좋아요, 싫어요 버튼 관련 코드
        likeButton = (Button) rootView.findViewById(R.id.likeButton);// 좋아요 버튼 증감 관련 코드
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

        dislikeButton = (Button) rootView.findViewById(R.id.dislikeButton);// 싫어요 버튼 증감 관련 코드
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

        likeCountView = (TextView) rootView.findViewById(R.id.likeCountView);
        dislikeCountView = (TextView) rootView.findViewById(R.id.dislikeCountView);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 리스트뷰 관련 코드
        items = new ArrayList<CommentItem>();
        listView = (ListView) rootView.findViewById(R.id.listView);
        adapter = new CommentAdapter();

        // 스크롤뷰와 리스트뷰 스크롤 충돌 관련 메서드
        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 화면 전환 관련 코드

        // 작성하기 화면으로 넘어가기 위한 메서드
        Button writeButton = (Button) rootView.findViewById(R.id.writeButton);
        writeButton.setOnClickListener(new View.OnClickListener() {// 작성하기 버튼이 눌렸을 때
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getApplicationContext(), CommentWriteActivity.class);
                startActivityForResult(intent, 101);
            }
        });

        // 모두보기 화면으로 넘어가기 위한 메서드
        Button showAllButton = (Button) rootView.findViewById(R.id.showAllButton);
        showAllButton.setOnClickListener(new View.OnClickListener() {// 모두보기 버튼이 눌렸을 때
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getApplicationContext(), CommentReadActivity.class);
                intent.putExtra("items", items);// 현재 액티비티에 있는 ArrayList<CommentItem> 자료형 items를 intent에 담아 호출하는 액티비티로 전달
                startActivityForResult(intent, 102);
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        // 액티비티에서 웹을 통해 받은 데이터로 영화 상세화면 꾸미기

        // 액티비티에서 넘어온 인덱스의 영화 상세 정보를 movieInfo 변수에 담기
        MovieInfo movieInfo = activity.movies.get(index);

        // 상세 화면 구성 요소 찾기
        imageView = (ImageView)rootView.findViewById(R.id.imageView);
        title = (TextView)rootView.findViewById(R.id.title);
        grade = (ImageView)rootView.findViewById(R.id.grade);
        date = (TextView)rootView.findViewById(R.id.date);
        genreDuration = (TextView)rootView.findViewById(R.id.genreDuration);
        reservation_gradeRate = (TextView)rootView.findViewById(R.id.reservation_gradeRate);
        rb = (RatingBar)rootView.findViewById(R.id.ratingBar);
        audience_rating = (TextView)rootView.findViewById(R.id.audience_rating);
        audience = (TextView)rootView.findViewById(R.id.audience);
        synopsis = (TextView)rootView.findViewById(R.id.synopsis);
        director = (TextView)rootView.findViewById(R.id.director);
        actor = (TextView)rootView.findViewById(R.id.actor);

        // 상세 화면 꾸며주기
        sendImageRequest(movieInfo.thumb);

        title.setText(movieInfo.title);

        gradeImage(movieInfo.grade);

        date.setText(movieInfo.date + " 개봉");
        genreDuration.setText(movieInfo.genre + " / " + movieInfo.duration + "분");

        likeCount = movieInfo.like;
        dislikeCount = movieInfo.dislike;
        likeCountView.setText(String.valueOf(likeCount));
        dislikeCountView.setText(String.valueOf(dislikeCount));

        reservation_gradeRate.setText(movieInfo.reservation_grade + "위 " + movieInfo.reservation_rate + "%");
        rb.setRating(movieInfo.audience_rating/2);
        audience_rating.setText("" + movieInfo.audience_rating);
        audience.setText(movieInfo.audience + "명");
        synopsis.setText(movieInfo.synopsis);
        director.setText(movieInfo.director);
        actor.setText(movieInfo.actor);

        return rootView;
    }
    // 새로 추가된 한줄평 데이터를 서버로 보내주는 메서드
    public void postCommentList(int idx) {
        String urlstr = url + idx;

        StringRequest request = new StringRequest(
                Request.Method.POST,
                urlstr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("comment", items.get(items.size()-1).toString());
                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

    }

    // 데이터를 요청하고 응답을 받는 메서드 (volley, gson 라이브러리를 gradle에 추가)
    public void requestCommentList(int idx) {
        String urlstr = url + idx;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlstr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity.getApplicationContext(),"코멘트 정보 에러 발생 : " + index, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

    }

    // 받은 응답을 Gson으로 변환해주는 메서드
    public void processResponse(String response) {
        Gson gson = new Gson();

        // 응답이 정상인지 4개의 정보만 먼저 파싱
        ResponseInfo2 info = gson.fromJson(response, ResponseInfo2.class);

        if(info.code == 200){
            CommentList commentList = gson.fromJson(response, CommentList.class);

            comments = commentList.result;

            for(int i=0; i<comments.size(); i++) {
                CommentInfo coInfo = comments.get(i);
                adapter.addItem(new CommentItem(coInfo.writer, coInfo.contents, coInfo.contents, coInfo.recommend, R.drawable.user1, coInfo.rating/2));
            }
            listView.setAdapter(adapter);// 어뎁터에 있는 정보들을 리스트뷰에서 보여줌

            Toast.makeText(activity.getApplicationContext(),"한줄평 개수 : " + comments.size(), Toast.LENGTH_SHORT).show();
        }
    }

    // 이미지를 다운로드 받아서 이미지뷰에 표시하기 위한 메서드
    public void sendImageRequest(String urlStr) {

        ImageLoadTask task = new ImageLoadTask(urlStr, imageView);
        task.execute();// 실행됨

    }

    // 관람 등급 이미지 설정 관련 메서드
    public void gradeImage(int gradeValue) {
        if(gradeValue == 12) {
            grade.setImageResource(R.drawable.ic_12);
        }
        else if(gradeValue == 15) {
            grade.setImageResource(R.drawable.ic_15);
        }
        else if(gradeValue == 19) {
            grade.setImageResource(R.drawable.ic_19);
        }
        else {
            grade.setImageResource(R.drawable.ic_all);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {// 호출했던 액티비티들이 종료되면 실행되는 메서드
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 101) {// 요청코드가 101일 경우, 작성하기 화면에서 돌아온 것
            if(intent != null) {
                //Toast.makeText(this, "작성하기 화면에서 돌아왔습니다.", Toast.LENGTH_SHORT).show();

                String contents = intent.getStringExtra("contents");// 사용자가 입력했던 한줄평 정보
                Float rating = intent.getFloatExtra("rating",0.0f);// 사용자가 지정했던 별점 정보

                // 새로운 추가해준 한줄평 데이터 서버에도 보내주기
                postCommentList(index+1);

                adapter.addItem(new CommentItem("kwh0525","방금 전", contents, 0,R.drawable.user1, rating));// 받아온 정보를 CommentItem 자료형의 형태로 어뎁터에 삽입
                adapter.notifyDataSetChanged();// 리스트뷰에 보여지는 어뎁터 정보를 갱신
            }
        } else if(requestCode == 102) {// 요청코드가 102일 경우, 모두보기 화면에서 돌아온 것
            if(intent != null) {
                ArrayList<CommentItem> newItems = (ArrayList<CommentItem>) intent.getSerializableExtra("newItems");// 모두보기 화면에서 작성하기 버튼을 통해 추가되었던 정보들

                adapter.addItem(newItems.get(newItems.size()-1));
                adapter.notifyDataSetChanged();
                // 새로운 추가해준 한줄평 데이터 서버에도 보내주기
                postCommentList(index+1);

                /*
                for(int i = 0; i < newItems.size(); i++){// ArrayList<CommentItem> 자료형으로 받아온 정보의 수만큼
                    adapter.addItem(newItems.get(i));// 어뎁터에 추가하고
                    adapter.notifyDataSetChanged();// 리스트뷰에 보여지는 어뎁터 정보를 갱신
                }

                 */
            }
        }
    }

    // 좋아요, 싫어요 버튼 증가 관련 메서드
    public void incrCount(Button button){// 버튼 클릭시 증가 관련 메서드
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

    // 좋아요, 싫어요 버튼 감소 관련 메서드
    public void decrCount(Button button){// 버튼 클릭시 감소 관련 메서드
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

    // 리스트뷰의 정보를 저장하기 위한 어뎁터 정의
    class CommentAdapter extends BaseAdapter {// 리스트뷰로 보여지는 정보를 관리하기 위한 어뎁터 생성

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
                view = new CommentItemView(activity.getApplicationContext());
            } else {
                view = (CommentItemView) ConvertView;
            }
            CommentItem item = items.get(position); // ArrayList 에서 해당 인덱스(position)의 값 가져온다.
            view.setId(item.getId());
            view.setTime(item.getTime());
            view.setComment(item.getComment());
            view.setRecommend(item.getRecommend());
            view.setImage(item.getResId());
            view.setRatingBar(item.getRating());

            return view;
        }
    }

}
