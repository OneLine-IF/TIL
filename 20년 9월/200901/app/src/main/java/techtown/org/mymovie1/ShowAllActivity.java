package techtown.org.mymovie1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

import techtown.org.mymovie1.data.CommentList;
import techtown.org.mymovie1.data.MovieDetail;
import techtown.org.mymovie1.data.MovieDetailInfo;
import techtown.org.mymovie1.data.MovieList;
import techtown.org.mymovie1.data.ResponseCheck;

public class ShowAllActivity extends AppCompatActivity {
    CommentAdapter showAllAdapter;
    ListView showAllListView;
    Button writeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        Intent intent = getIntent();

        showAllListView = (ListView) findViewById(R.id.showall_listview);




        showAllAdapter = new CommentAdapter();

        showAllAdapter.showAllItems = intent.getParcelableArrayListExtra("CommentItem");
        showAllListView.setAdapter(showAllAdapter);


        writeButton = (Button) findViewById(R.id.write_button);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWriteActivity();
            }
        });


    }
    public void showWriteActivity(){
        Intent intent = new Intent(getApplicationContext(),WriteActivity.class);
        startActivityForResult(intent, 1001);
    }



    public class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> showAllItems = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return showAllItems.size();
        }
        public void addItem(CommentItem item) {showAllItems.add(item);}

        @Override
        public Object getItem(int position) {
            return showAllItems.get(position);
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
            CommentItem item = showAllItems.get(position);
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

}