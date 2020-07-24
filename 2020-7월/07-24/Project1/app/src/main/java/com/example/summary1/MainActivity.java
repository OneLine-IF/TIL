package com.example.summary1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textView);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar1, float rating, boolean fromUser) {
                textView.setText("Rating " + rating);
            }
        });
    }
}