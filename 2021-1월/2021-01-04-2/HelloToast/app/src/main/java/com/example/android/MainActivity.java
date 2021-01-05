package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount=0;
    private TextView mShowCount;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void CountUP(View view) {
        mCount++;
        if (mShowCount != null){
            mShowCount.setText(Integer.toString(mCount));
        }
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG,"Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mShowCount.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}