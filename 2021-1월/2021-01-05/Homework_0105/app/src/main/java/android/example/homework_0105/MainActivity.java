package android.example.homework_0105;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mCount=0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount=(TextView)findViewById(R.id.show_count);
        if(savedInstanceState != null){
            int count_exist = savedInstanceState.getInt("count_exist");
            if(count_exist!=0){
                mCount=count_exist;
                mShowCount.setText(Integer.toString(mCount));
            }
        }
    }

    public void CountUP(View view) {
        mCount++;
        mShowCount.setText(Integer.toString(mCount));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mCount != 0){
            outState.putInt("count_exist", mCount);
        }
    }
}