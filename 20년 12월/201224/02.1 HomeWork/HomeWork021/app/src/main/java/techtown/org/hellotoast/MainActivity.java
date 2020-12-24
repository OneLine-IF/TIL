package techtown.org.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = "Hello!\n";
        String count = Integer.toString(mCount);
        String result = message + count;
        intent.putExtra(EXTRA_MESSAGE,result);
        startActivity(intent);
    }

    public void countUp(View view) {
        ++mCount;
        if(mShowCount != null){
            mShowCount.setText(Integer.toString(mCount));
        }
    }
}