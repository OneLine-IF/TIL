package techtown.org.drawabletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView battery;
    private int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        battery = (ImageView) findViewById(R.id.battery_image);
        battery.setImageLevel(level);
    }

    public void changeBatterylevel(View view) {
        switch(view.getId()){
            case R.id.increase_button:
                if(level<6){
                    level++;
                    battery.setImageLevel(level);
                }
                break;
            case R.id.decrease_button:
                if(level>0){
                    level--;
                    battery.setImageLevel(level);
                }
                break;
            default:
                break;
        }
    }
}