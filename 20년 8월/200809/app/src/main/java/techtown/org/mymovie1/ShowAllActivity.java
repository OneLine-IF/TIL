package techtown.org.mymovie1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.w3c.dom.Comment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import techtown.org.mymovie1.MainActivity.CommentAdapter;

public class ShowAllActivity extends AppCompatActivity {
    CommentAdapter adapter;
    ListView listView2;
    Button write_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        listView2 = (ListView) findViewById(R.id.listView2);

        adapter = new CommentAdapter();
        Intent intent = getIntent();
        adapter.items = intent.getParcelableArrayListExtra("CommentItem");
        listView2.setAdapter(adapter);



        write_button = (Button) findViewById(R.id.write_button);
        write_button.setOnClickListener(new View.OnClickListener() {
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

}