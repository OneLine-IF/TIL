package android.example.challenge_0104;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView first;
    private TextView second;
    private TextView third;
    private int num1;
    private int num2;
    private int num3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        first = findViewById(R.id.textView);
        second = findViewById(R.id.textView2);
        third = findViewById(R.id.textView3);
        num1 = intent.getIntExtra("first", 0);
        num2 = intent.getIntExtra("second", 0);
        num3 = intent.getIntExtra("third", 0);
        if (num1 == 1) {

            first.setVisibility(View.VISIBLE);

        } else if (num2 == 2) {

            second.setVisibility(View.VISIBLE);

        } else if (num3 == 3) {

            third.setVisibility(View.VISIBLE);

        }
    }
    
}