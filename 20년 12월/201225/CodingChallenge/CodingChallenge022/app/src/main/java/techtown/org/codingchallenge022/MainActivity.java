package techtown.org.codingchallenge022;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyOneTextView;
    private TextView mReplyTwoTextView;
    private TextView mReplyThreeTextView;
    private TextView mReplyFourTextView;
    private TextView mReplyFiveTextView;
    private TextView mReplySixTextView;
    private TextView mReplySevenTextView;
    private TextView mReplyEightTextView;
    private TextView mReplyNineTextView;
    private TextView mReplyTenTextView;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(mReplyOneTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Cheese",mReplyOneTextView.getText().toString());
        }

        if(mReplyTwoTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Rice",mReplyTwoTextView.getText().toString());
        }

        if(mReplyThreeTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Apple",mReplyThreeTextView.getText().toString());
        }

        if(mReplyFourTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Milk",mReplyFourTextView.getText().toString());
        }

        if(mReplyFiveTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Noodle",mReplyFiveTextView.getText().toString());
        }

        if(mReplySixTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Beer",mReplySixTextView.getText().toString());
        }

        if(mReplySevenTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Egg",mReplySevenTextView.getText().toString());
        }

        if(mReplyEightTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Poke",mReplyEightTextView.getText().toString());
        }

        if(mReplyNineTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Water",mReplyNineTextView.getText().toString());
        }

        if(mReplyTenTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible",true);
            outState.putString("Banana",mReplyTenTextView.getText().toString());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReplyOneTextView = findViewById(R.id.text_item_one);
        mReplyTwoTextView = findViewById(R.id.text_item_two);
        mReplyThreeTextView = findViewById(R.id.text_item_three);
        mReplyFourTextView = findViewById(R.id.text_item_four);
        mReplyFiveTextView = findViewById(R.id.text_item_five);
        mReplySixTextView = findViewById(R.id.text_item_six);
        mReplySevenTextView = findViewById(R.id.text_item_seven);
        mReplyEightTextView = findViewById(R.id.text_item_eight);
        mReplyNineTextView = findViewById(R.id.text_item_nine);
        mReplyTenTextView = findViewById(R.id.text_item_ten);

        if(savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if(isVisible) {
                mReplyOneTextView.setText(savedInstanceState.getString("Cheese"));
                mReplyOneTextView.setVisibility(View.VISIBLE);

                mReplyTwoTextView.setText(savedInstanceState.getString("Rice"));
                mReplyTwoTextView.setVisibility(View.VISIBLE);

                mReplyThreeTextView.setText(savedInstanceState.getString("Apple"));
                mReplyThreeTextView.setVisibility(View.VISIBLE);

                mReplyFourTextView.setText(savedInstanceState.getString("Milk"));
                mReplyFourTextView.setVisibility(View.VISIBLE);

                mReplyFiveTextView.setText(savedInstanceState.getString("Noodle"));
                mReplyFiveTextView.setVisibility(View.VISIBLE);

                mReplySixTextView.setText(savedInstanceState.getString("Beer"));
                mReplySixTextView.setVisibility(View.VISIBLE);

                mReplySevenTextView.setText(savedInstanceState.getString("Egg"));
                mReplySevenTextView.setVisibility(View.VISIBLE);

                mReplyEightTextView.setText(savedInstanceState.getString("Poke"));
                mReplyEightTextView.setVisibility(View.VISIBLE);

                mReplyNineTextView.setText(savedInstanceState.getString("Water"));
                mReplyNineTextView.setVisibility(View.VISIBLE);

                mReplyTenTextView.setText(savedInstanceState.getString("Banana"));
                mReplyTenTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent,TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST) {
            if(resultCode == 101) {
                String reply = data.getStringExtra("Cheese");
                mReplyOneTextView.setText(reply);
                mReplyOneTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 102) {
                String reply = data.getStringExtra("Rice");
                mReplyTwoTextView.setText(reply);
                mReplyTwoTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 103) {
                String reply = data.getStringExtra("Apple");
                mReplyThreeTextView.setText(reply);
                mReplyThreeTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 104) {
                String reply = data.getStringExtra("Milk");
                mReplyFourTextView.setText(reply);
                mReplyFourTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 105) {
                String reply = data.getStringExtra("Noodle");
                mReplyFiveTextView.setText(reply);
                mReplyFiveTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 106) {
                String reply = data.getStringExtra("Beer");
                mReplySixTextView.setText(reply);
                mReplySixTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 107) {
                String reply = data.getStringExtra("Egg");
                mReplySevenTextView.setText(reply);
                mReplySevenTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 108) {
                String reply = data.getStringExtra("Poke");
                mReplyEightTextView.setText(reply);
                mReplyEightTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 109) {
                String reply = data.getStringExtra("Water");
                mReplyNineTextView.setText(reply);
                mReplyNineTextView.setVisibility(View.VISIBLE);
            }

            if(resultCode == 110) {
                String reply = data.getStringExtra("Banana");
                mReplyTenTextView.setText(reply);
                mReplyTenTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    /*
    메인은 10개의 Textview가 있음 + Add Item 버튼, 이 버튼 누르면 두 번째 Activity로 감
    두 번째 Activity에서 Shopping List의 버튼이 있는데 선택된 Item을 빈 TextView에다가 추가시킴
    의문 -> 여기서 10개가 다 찼을 경우? 그렇지만 Item은 제한되어 있기 때문에 -> But Item을 텍스트 10개 이상 만들었을때도 고려해봐야함
    */

}