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
    RatingBar userRating;
    EditText userComment;
    Button cancelButton, saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        userRating = (RatingBar) findViewById(R.id.user_ratingbar);
        userComment = (EditText) findViewById(R.id.usercomment_edittext);

        saveButton = (Button) findViewById(R.id.save_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
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
        saveButton.setOnClickListener(button);
        cancelButton.setOnClickListener(button);
    }





    public void saveToMain() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

        String comments = userComment.getText().toString();
        float rating = userRating.getRating();
        intent.putExtra("comments",comments);
        intent.putExtra("rating",rating);
        setResult(RESULT_OK,intent);
        finish();
    }
}