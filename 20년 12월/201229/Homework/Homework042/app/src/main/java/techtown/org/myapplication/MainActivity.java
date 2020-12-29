package techtown.org.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String ItemText = "Topping: ";
    CheckBox Itemone;
    CheckBox Itemtwo;
    CheckBox Itemthree;
    CheckBox Itemfour;
    CheckBox Itemfive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Itemone = (CheckBox) findViewById(R.id.Item_one);
        Itemtwo = (CheckBox) findViewById(R.id.Item_two);
        Itemthree = (CheckBox) findViewById(R.id.Item_three);
        Itemfour = (CheckBox) findViewById(R.id.Item_four);
        Itemfive = (CheckBox) findViewById(R.id.Item_five);
    }



    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
    }

    public void onSubmit(View view) {
        ItemText = "Topping: ";
        if(Itemone.isChecked()){
            ItemText = ItemText + " "  + getString(R.string.checkbox_chocolate_syrup);
        }
        else {
            ItemText = ItemText.replace(getString(R.string.checkbox_chocolate_syrup),"");
        }
        if(Itemtwo.isChecked()){
            ItemText = ItemText + " " + getString(R.string.checkbox_sprinkles);
        }
        else {
            ItemText = ItemText.replace(getString(R.string.checkbox_sprinkles),"");
        }
        if(Itemthree.isChecked()){
            ItemText = ItemText + " "  + getString(R.string.checkbox_crushed_nuts);
        }
        else {
            ItemText = ItemText.replace(getString(R.string.checkbox_crushed_nuts),"");
        }
        if(Itemfour.isChecked()){
            ItemText = ItemText + " " + getString(R.string.checkbox_cherries);
        }
        else {
            ItemText = ItemText.replace(getString(R.string.checkbox_cherries),"");
        }
        if(Itemfive.isChecked()){
            ItemText = ItemText + " " + getString(R.string.checkbox_orio_cookie_crumbles);
        }
        else {
            ItemText = ItemText.replace(getString(R.string.checkbox_orio_cookie_crumbles),"");
        }


    }


    public void showToast(View view) {

        displayToast(ItemText);

    }


}

