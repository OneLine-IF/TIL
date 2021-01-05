package android.example.shopping_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY";
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activiyt);
        Intent intent = getIntent();
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
    }

    public void returnReply1(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("first", checkBox1.getText().toString());
        setResult(1, replyIntent);
        finish();

    }

    public void returnReply2(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("second", checkBox2.getText().toString());
        setResult(2, replyIntent);
        finish();
    }

    public void returnReply3(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("third", checkBox3.getText().toString());
        setResult(3, replyIntent);
        finish();
    }

    public void returnReply4(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("forth", checkBox4.getText().toString());
        setResult(4, replyIntent);
        finish();
    }
}