package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.List;

public class CommentWriteActivity extends AppCompatActivity {
RatingBar ratingBar4;
EditText contentsInput;
MainActivity.ReViewAdapter adapter;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        ratingBar4 = (RatingBar)findViewById(R.id.ratingBar4);
        contentsInput = (EditText)findViewById(R.id.contentInput);


        Button s_button = (Button)findViewById(R.id.Save);
        Button b_button = (Button)findViewById(R.id.Back);

        Intent intent = getIntent();
        processIntent(intent);

        s_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToMain();
            }
        });
        b_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //부가데이터를 꺼내서 처리하는 메소드 만들기.
    }
    private void processIntent(Intent intent){
        if(intent!=null){
            float rating = intent.getFloatExtra("rating",0.0f);//저쪽에서 넘겨준 값을 받는데 디폴트 값은 0.0임.
            ratingBar4.setRating(rating);//rating 값의 변수 할당.
        }
    }

    public void returnToMain(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        String contents = contentsInput.getText().toString();//content 문자열
        //float rating = ratingBar4.getRating();
        //intent.putExtra("rating",rating);
        intent.putExtra("contents", contents);
        setResult(Activity.RESULT_OK,intent);//이전 액티비티로 인텐트를 전달해줌.
        adapter.addItem(new reViewitem(contents));
        finish();
    }

}