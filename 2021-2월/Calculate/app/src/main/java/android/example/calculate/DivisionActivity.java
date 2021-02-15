package android.example.calculate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DivisionActivity extends AppCompatActivity {
    Toolbar myToolbar;
    private EditText editText;
    private Button btn[] = new Button[16];
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        init();
        initListener();


    }

    private void init() {
        btn[0]=findViewById(R.id.btn1);
        btn[1]=findViewById(R.id.btn2);
        btn[2]=findViewById(R.id.btn3);
        btn[3]=findViewById(R.id.btn4);
        btn[4]=findViewById(R.id.btn5);
        btn[5]=findViewById(R.id.btn6);
        btn[6]=findViewById(R.id.btn7);
        btn[7]=findViewById(R.id.btn8);
        btn[8]=findViewById(R.id.btn9);
        btn[10]=findViewById(R.id.reset);
        btn[9]=findViewById(R.id.plus);
        btn[11]=findViewById(R.id.result);
        editText = (EditText) findViewById(R.id.editTextTextPersonName);
    }

    private void initListener() {
        for(int i =0;i<10;i++){
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button btn = (Button) view;
                    editText.append(btn.getText().toString());
                }
            });
        }
        btn[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
        btn[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = editText.getText().toString();
                editText.setText(Eval.cal(result));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting1:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_setting2:
                Intent intent2 = new Intent(this, MinusActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_setting3:
                Intent intent3 = new Intent(this, MultipliActivity.class);
                startActivity(intent3);
                return true;
            case R.id.action_setting4:
                Intent intent4 = new Intent(this, DivisionActivity.class);
                startActivity(intent4);
                return true;
            default:
                Toast.makeText(getApplicationContext(), "나머지", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }
}