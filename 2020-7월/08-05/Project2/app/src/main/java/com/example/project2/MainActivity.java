package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView likeCountView;
    TextView dislikeCountView;
    Button likeButton;
    Button dislikeButton;
    int likeCount = 15;
    int dislikeCount = 1;
    ReViewAdapter adapter;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.ListView);

        adapter = new ReViewAdapter();

        adapter.addItem(new reViewitem("적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        adapter.addItem(new reViewitem("적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        adapter.addItem(new reViewitem("아직  안 봄."));
        adapter.addItem(new reViewitem("그냥 그럼."));

        listView.setAdapter(adapter);

        likeButton = (Button) findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(likeButton.isSelected()){
                    likeButton.setSelected(true);
                    likeCount +=1;
                    likeCountView.setText(String.valueOf(likeCount));
                    Toast.makeText(getApplicationContext(), "좋아요 on", Toast.LENGTH_SHORT).show();
                }else if(likeButton.isSelected()){
                    likeButton.setSelected(false);
                    likeCount -=1;
                    likeCountView.setText(String.valueOf(likeCount));
                    Toast.makeText(getApplicationContext(), "좋아요 off", Toast.LENGTH_SHORT).show();
                }else if(dislikeButton.isSelected()){
                    dislikeButton.setSelected(true);
                    likeButton.setSelected(false);
                    dislikeCount +=1;
                    likeCount -=1;
                    likeCountView.setText(String.valueOf(likeCount));
                    dislikeCountView.setText(String.valueOf(dislikeCount));
                    Toast.makeText(getApplicationContext(), "싫어요 on", Toast.LENGTH_SHORT).show();
                }
            }
        });

        likeCountView = (TextView) findViewById(R.id.likeCountView);

        dislikeButton = (Button) findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dislikeButton.isSelected()){
                    dislikeButton.setSelected(true);
                    dislikeCount +=1;
                    dislikeCountView.setText(String.valueOf(dislikeCount));
                    Toast.makeText(getApplicationContext(), "싫어요 on", Toast.LENGTH_SHORT).show();
                }else if(dislikeButton.isSelected()){
                    dislikeButton.setSelected(false);
                    dislikeCount -=1;
                    dislikeCountView.setText(String.valueOf(dislikeCount));
                    Toast.makeText(getApplicationContext(), "싫어요 off", Toast.LENGTH_SHORT).show();
                }else if(likeButton.isSelected()){
                    likeButton.setSelected(true);
                    dislikeButton.setSelected(false);
                    likeCount +=1;
                    dislikeCount -=1;
                    dislikeCountView.setText(String.valueOf(dislikeCount));
                    likeCountView.setText(String.valueOf(likeCount));
                    Toast.makeText(getApplicationContext(), "좋아요 on", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dislikeCountView = (TextView) findViewById(R.id.dislikeCountView);
        scrollView = (ScrollView)findViewById(R.id.ScrollView);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


    }

    public void onreVIewWritingButtonClick(View V) {
        Toast.makeText(this, "작성하기를 클릭하셨습니다!", Toast.LENGTH_LONG).show();
    }

    public void onallButtonClick(View V) {
        Toast.makeText(this, "모두보기를 클릭하셨습니다!", Toast.LENGTH_LONG).show();
    }

    public void incrLikeCount() {
        likeCount +=1;

        likeCountView.setText(String.valueOf(likeCount));//setText 에는 숫자를 넣을 수 없으므로 String.valueOf(likeCount)이렇게 변환시키기.


        Toast.makeText(getApplicationContext(), "좋아요 on", Toast.LENGTH_SHORT).show();
    }

    public void decrLikeCount() {
        likeCount -=1;

        likeCountView.setText(String.valueOf(likeCount));


        Toast.makeText(getApplicationContext(), "좋아요 off", Toast.LENGTH_SHORT).show();
    }

    public void incrDislikeCount() {
        dislikeCount +=1;
        dislikeCountView.setText(String.valueOf(dislikeCount));
        Toast.makeText(getApplicationContext(), "싫어요 on", Toast.LENGTH_SHORT).show();
    }

    public void decrDislikeCount() {
        dislikeCount -=1;
        dislikeCountView.setText(String.valueOf(dislikeCount));
        Toast.makeText(getApplicationContext(), "싫어요 off", Toast.LENGTH_SHORT).show();
    }

    class ReViewAdapter extends BaseAdapter {
        ArrayList<reViewitem> items = new ArrayList<reViewitem>();

        @Override
        public int getCount() {
            return items.size();//아이템의 갯수를 알려줌
        }

        public void addItem(reViewitem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i); //몇 번째 아이템인지 알려줌(i)
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            CommentItemView view = new CommentItemView(getApplicationContext());

            reViewitem item = items.get(i);
            view.setName(item.getReView());

            return view;
        }
    }


}