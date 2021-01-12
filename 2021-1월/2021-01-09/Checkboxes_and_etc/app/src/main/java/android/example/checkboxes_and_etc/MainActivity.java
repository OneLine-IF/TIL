package android.example.checkboxes_and_etc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void onCheckButtonClicked(View view) {
        boolean checked = ((CheckBox)view).isChecked();
        switch (view.getId()){
            case R.id.checkBox:
                if(checked){
                    displayToast(getString(R.string.chocolate_syrup));
                    total+= getString(R.string.chocolate_syrup);
                }
                break;
            case R.id.checkBox2:
                if(checked){
                    displayToast(getString(R.string.sprinkles));
                    total+= getString(R.string.sprinkles);
                }
                break;
            case R.id.checkBox3:
                if(checked){
                    displayToast(getString(R.string.crushed_nuts));
                    total+= getString(R.string.crushed_nuts);
                }
                break;
            case R.id.checkBox4:
                if(checked){
                    displayToast(getString(R.string.cherries));
                    total+= getString(R.string.cherries);
                }
                break;
            case R.id.checkBox5:
                if(checked){
                    displayToast(getString(R.string.orio_cookie_crumbles));
                    total+= getString(R.string.orio_cookie_crumbles);
                }
                break;
        }
    }

    public void showTotal(View view) {
        displayToast(total);
    }
}