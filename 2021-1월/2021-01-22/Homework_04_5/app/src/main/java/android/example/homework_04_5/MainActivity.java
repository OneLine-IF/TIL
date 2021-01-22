package android.example.homework_04_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWordList.addLast(getString(R.string.first_food) + getString(R.string.first_food_describe));
        mWordList.addLast(getString(R.string.second_food) + getString(R.string.second_food_describe));
        mWordList.addLast(getString(R.string.third_food) + getString(R.string.third_food_describe));
        mWordList.addLast(getString(R.string.forth_food) + getString(R.string.forth_food_describe));
        mWordList.addLast(getString(R.string.fifth_food) + getString(R.string.fifth_food_describe));
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}