package com.example.detailpart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class CommentItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    ImageView imageView;
    RatingBar ratingBar;

    public CommentItemView(Context context) {
        super(context);

        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view, this, true);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        imageView = (ImageView) findViewById(R.id.imageView);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    public void setId(String Id){
        textView.setText(Id);
    }

    public void setTime(String Time){
        textView2.setText(Time);
    }

    public void setComment(String Comment){ textView3.setText(Comment); }

    public void setRecommend(int Recommend){ textView4.setText(String.valueOf(Recommend)); }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

    public void setRatingBar(float rating){
        ratingBar.setRating(rating);
    }
}
