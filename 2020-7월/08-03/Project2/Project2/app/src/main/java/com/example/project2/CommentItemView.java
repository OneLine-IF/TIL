package com.example.project2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CommentItemView extends LinearLayout {
    TextView writting;

    public CommentItemView(Context context) {
        super(context);
        init(context);//초기화하는 함수.
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);//초기화하는 함수.
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.review_item,this,true);
        //인플레이션 해주어서 데이터 연결하기.
        writting = (TextView) findViewById(R.id.writting);
        }

        public void setName(String name){
            writting.setText(name);
        }

}
