package com.example.summary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class CommentReadActivity extends AppCompatActivity {
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_read);

        ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new CommentAdapter();
        listView.setAdapter(adapter);

        Intent intent = getIntent();

        if(intent != null) {
            ArrayList<CommentItem> items = (ArrayList<CommentItem>) intent.getSerializableExtra("items");
            if(items != null) {
                Toast.makeText(getApplicationContext(), "전달받은 이름 리스트 갯수 : " + items.size(), Toast.LENGTH_LONG).show();

                for(int i = 0; i < items.size(); i++){
                    adapter.addItem(items.get(i));
                    adapter.notifyDataSetChanged();
                }
            }
        }

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
            view.setRatingBar(item.getRating());

            return view;
        }
    }
}