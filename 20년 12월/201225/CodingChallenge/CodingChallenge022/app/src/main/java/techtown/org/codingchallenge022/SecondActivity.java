package techtown.org.codingchallenge022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.codingchallenge22.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        intent.putExtra(EXTRA_REPLY,message);
    }

    public void returnCheese(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Cheese","Cheese");
        setResult(101, replyIntent);
        finish();
    }

    public void returnRice(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Rice","Rice");
        setResult(102, replyIntent);
        finish();
    }

    public void returnApple(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Apple","Apple");
        setResult(103, replyIntent);
        finish();
    }

    public void returnMilk(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Milk","Milk");
        setResult(104, replyIntent);
        finish();
    }

    public void returnNoodle(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Noodle","Noodle");
        setResult(105, replyIntent);
        finish();
    }

    public void returnBeer(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Beer","Beer");
        setResult(106, replyIntent);
        finish();
    }

    public void returnEgg(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Egg","Egg");
        setResult(107, replyIntent);
        finish();
    }

    public void returnPoke(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Poke","Poke");
        setResult(108, replyIntent);
        finish();
    }

    public void returnWater(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Water","Water");
        setResult(109, replyIntent);
        finish();
    }

    public void returnBanana(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Banana","Banana");
        setResult(110, replyIntent);
        finish();
    }
}