package techtown.org.counterhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCount=0;
    private TextView mShowCount;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current", mCount);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView)findViewById(R.id.show_count);

        if(savedInstanceState != null) {
            int currentState = savedInstanceState.getInt("current");
            String current = Integer.toString(currentState);
            mShowCount.setText(current);
            mCount = currentState;
        }
    }

    public void countUp(View view) {
        mCount++;
        if(mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }
    }


}