package techtown.org.mymovie1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton likeButton, DislikeButton;
    TextView likeCountView, DislikeCountView;

    int likeCount = 15, DislikeCount = 1;
    boolean likeState = false, DislikeState = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CommentAdapter adapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = (ImageButton) findViewById(R.id.likeButton);
        DislikeButton = (ImageButton) findViewById(R.id.DislikeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (likeState){
                    if(!DislikeState){
                        decrLikeCount();
                    }
                    else {
                        incrDislikeCount();
                        decrLikeCount();

                    }
                } else {
                    if(DislikeState) {
                        incrLikeCount();
                        decrDislikeCount();
                    }
                    else{
                        incrLikeCount();
                    }

                }
                likeState = !likeState;

            }
        });

        DislikeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (DislikeState){
                    if(!likeState){

                        decrDislikeCount();
                    }
                    else {
                        incrLikeCount();
                        decrDislikeCount();


                    }

                } else {
                    if(likeState) {
                        incrDislikeCount();
                        decrLikeCount();
                    }
                    else{
                        incrDislikeCount();
                    }
                }
                DislikeState = !DislikeState;

            }
        });

        likeCountView = (TextView) findViewById(R.id.likeCountView);
        DislikeCountView = (TextView) findViewById(R.id.DislikeCountView);

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kym71***","10분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","추천","0","|","신고하기",R.id.Rating,R.id.UserImage));
        adapter.addItem(new CommentItem("kym71***","10분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.","추천","0","|","신고하기",R.id.Rating,R.id.UserImage));
        listView.setAdapter(adapter);

        Button write_button = (Button) findViewById(R.id.write_button);
        write_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast toast = Toast.makeText(getApplicationContext(),"작성하기 버튼을 눌렀습니다.",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button show_all = (Button) findViewById(R.id.Showall);
        show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"모두보기 버튼을 눌렀습니다.",Toast.LENGTH_LONG);
                toast.show();
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
            if(convertView==null){
                view = new CommentItemView(getApplicationContext());
            } else{
                view = (CommentItemView) convertView;
            }
            CommentItem item = items.get(position);
            view.setUserImage(item.getUserImage());
            view.setRatings();
            view.setUserId(item.getUserId());
            view.SetWritetime(item.getWrite_time());
            view.setComment(item.getComment());
            view.setRec(item.getRec());
            view.setRecNum(item.getRecNum());
            view.setBar(item.getBar());
            view.setWarning(item.getWarning());
            return view;
        }
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

    public void incrDislikeCount() {
        DislikeCount += 1;
        DislikeCountView.setText(String.valueOf(DislikeCount));

        DislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrDislikeCount() {
        DislikeCount -= 1;
        DislikeCountView.setText(String.valueOf(DislikeCount));

        DislikeButton.setBackgroundResource(R.drawable.thumb_down);
    }
}