package com.example.summary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class CommentReadActivity extends AppCompatActivity {
    CommentAdapter adapter;// 여기서도, 어뎁터를 활용하여 리스트뷰에 받아온 정보를 띄워줘야 하기 때문에 필요
    ArrayList<CommentItem> newItems;// 작성하기 화면에서 새로 추가한 정보들을 분류하여 저장라기 위해 필요

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 액션바 객체를 받고 이름을 변경하기 위한 코드
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("한줄평 목록");
        setContentView(R.layout.activity_comment_read);

        ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new CommentAdapter();
        listView.setAdapter(adapter);

        newItems = new ArrayList<CommentItem>();

        Intent intent = getIntent();// 이 액티비티를 호출한 액티비티에서 보낸 인텐트를 받음
        if(intent != null) {// 비어있지 않을 경우
            ArrayList<CommentItem> items = (ArrayList<CommentItem>) intent.getSerializableExtra("items");// ArrayList<CommentItem> 자료형의 변수를 선언해 인텐트 속 자료를 담음
            if(items != null) {// 이 ArrayList가 비어있지 않을 경우
                for(int i = 0; i < items.size(); i++){// 정보의 수만큼
                    adapter.addItem(items.get(i));// 어뎁터에 정보를 저장하고
                    adapter.notifyDataSetChanged();// 리스트뷰에 보여지는 어뎁터를 갱신
                }
            }
        }

        actionBar.setDisplayHomeAsUpEnabled(true);// 상단의 액션바에 뒤로가기 버튼이 보여지도록 함

        Button writeButton = (Button) findViewById(R.id.writeButton);
        writeButton.setOnClickListener(new View.OnClickListener() {// 작성하기 버튼이 눌렸을 때
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);//인텐트를 만들어 새로운 액티비티로 보냄
                startActivityForResult(intent, 103);
            }
        });

    }

    @Override
    public void onBackPressed() {// 휴대폰 자체의 뒤로가기 버튼이 눌렸을 경우

        Intent intent = new Intent();// 인텐트를 만들어
        intent.putExtra("newItems", newItems);// 새로 추가된 정보를 담은 ArrayList를 이전 액티비티로 보냄
        setResult(RESULT_OK, intent);

        finish();// 현재 액티비티를 종료함

        super.onBackPressed();
    }

    public boolean onOptionsItemSelected(MenuItem item) {// 상단의 액션바의 뒤로가기 버튼이 눌렸을 경우
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this); -> 현재 Activity는 종료되면서 stack 상단에 있는 녀석이 호출.
                // 그러므로 위 코드가 있으면 아래의 finis(); 코드가 실행되지 않아 메인에서 onActivityResult 메서드로 받아주지 못한다.
                Intent intent = new Intent();// 인텐트를 만들어
                intent.putExtra("newItems", newItems);// 새로 추가된 정보를 담은 ArrayList를 이전 액티비티로 보냄
                setResult(RESULT_OK, intent);

                finish();// 현재 액티비티를 종료함

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {// 이 액티비티에서 호출했던 액티비티에서 다시 돌아왔을 때 실행
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 103) {// 요청코드가 103일 경우, 작성하기 화면에서 돌아왔을 경우
            if(intent != null) {// 인텐트가 비어있지 않다면
                String contents = intent.getStringExtra("contents");// 사용자가 입력했던 한줄평 정보를 저장
                Float rating = intent.getFloatExtra("rating",0.0f);// 사용자가 지정했던 별점 정보를 저장

                Toast.makeText(this, "작성하기 화면에서 돌아왔습니다.", Toast.LENGTH_SHORT).show();

                CommentItem item = (CommentItem) new CommentItem("kwh05**","방금 전", contents, R.drawable.user1, rating);// 이를 CommentItem 자료형의 item 변수에 저장
                adapter.addItem(item);// 어뎁터에 추가된 정보를 저장
                adapter.notifyDataSetChanged();// 리스트뷰에 보여지는 어뎁터 갱신

                newItems.add(item);// 나중에 메인 화면으로 돌아갈 때에도 추가된 정보를 보내줘야하기 때문에 따로 저장
            }
        }
    }

    class CommentAdapter extends BaseAdapter {// 리스트뷰로 보여지는 정보를 관리하기 위한 어뎁터 생성
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
            view.setRatingBar(item.getRating());

            return view;
        }
    }
}