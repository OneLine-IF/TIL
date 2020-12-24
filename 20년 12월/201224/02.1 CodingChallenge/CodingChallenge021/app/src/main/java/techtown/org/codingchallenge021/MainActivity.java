package techtown.org.codingchallenge021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="com.example.android.codingchallenge021.extra.MESSAGE";
    private EditText mMessageEditText1;
    private EditText mMessageEditText2;
    private EditText mMessageEditText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText1 = findViewById(R.id.editText_one);
        mMessageEditText2 = findViewById(R.id.editText_two);
        mMessageEditText3 = findViewById(R.id.editText_three);
    }

    public void TextOne(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        String message = mMessageEditText1.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    public void TextTwo(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        String message = mMessageEditText2.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    public void TextThree(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        String message = mMessageEditText3.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}