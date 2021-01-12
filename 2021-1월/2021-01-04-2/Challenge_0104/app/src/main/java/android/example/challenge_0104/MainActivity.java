package android.example.challenge_0104;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE="com.android.twoactivities.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void launchSecondActivity_first(View view) {
        Log.d(LOG_TAG, "Button click!");
        Intent intent = new Intent(this,SecondActivity.class);
        setResult(RESULT_OK, intent);
        intent.putExtra("first",1);
        startActivity(intent);
    }

    public void launchSecondActivity_second(View view) {
        Log.d(LOG_TAG, "Button click!");
        Intent intent = new Intent(this,SecondActivity.class);
        setResult(RESULT_OK, intent);
        intent.putExtra("second",2);
        startActivity(intent);
    }

    public void launchSecondActivity_third(View view) {
        Log.d(LOG_TAG, "Button click!");
        Intent intent = new Intent(this,SecondActivity.class);
        setResult(RESULT_OK, intent);
        intent.putExtra("third",3);
        startActivity(intent);
    }
}