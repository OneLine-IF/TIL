package com.example.mydrawer2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class CommentWriteActivity extends AppCompatActivity {
    RatingBar ratingBar;// 사용자가 지정한 별점 정보를 받기 위한 변수
    EditText contentsInput;// 사용자가 입력한 한줄평 정보를 받기 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        contentsInput = (EditText) findViewById(R.id.contentsInput);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {// 저장 버튼이 눌릴 경우
            @Override
            public void onClick(View view) {
                returnToMain();// 전달하기 위한 정보를 생성해준 intent에 담고 액티비티를 종료해주는 메서드 실행
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {// 취소 버튼이 눌릴 경우
            @Override
            public void onClick(View view) {
                finish();// 액티비티를 종료함
            }
        });

    }

    public void returnToMain() {
        String contents = contentsInput.getText().toString();
        float rating = ratingBar.getRating();

        Intent intent = new Intent();// 인텐트를 생성하여
        intent.putExtra("contents", contents);// 담고자하는 정보를 담고
        intent.putExtra("rating", rating);

        setResult(RESULT_OK, intent);// 이전 액티비티로 데이터 전달

        finish();// 현재 액티비티 종료
    }
}