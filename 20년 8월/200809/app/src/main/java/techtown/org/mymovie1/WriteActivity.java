package techtown.org.mymovie1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class WriteActivity extends AppCompatActivity {
    RatingBar user_rating;
    EditText user_comment;
    Button cancel_button, save_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        user_rating = (RatingBar) findViewById(R.id.user_rating);
        user_comment = (EditText) findViewById(R.id.user_comment);

        save_button = (Button) findViewById(R.id.save_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);
        Button.OnClickListener button = new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                switch (view.getId()){
                    case R.id.save_button:
                        saveToMain();
                        break;
                    case R.id.cancel_button:
                        finish();
                        break;
                }
            }
        };
        save_button.setOnClickListener(button);
        cancel_button.setOnClickListener(button);
    }





    public void saveToMain() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

        String comments = user_comment.getText().toString();
        float rating = user_rating.getRating();
        intent.putExtra("comments",comments);
        intent.putExtra("rating",rating);
        setResult(RESULT_OK,intent);
        finish();
    }
}