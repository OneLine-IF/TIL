package techtown.org.droidcafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);

        // 스피너 생성
        Spinner spinner = findViewById(R.id.label_spinner);
        if(spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // string array를 사용한 ArrayAdapter 생성 default spinner layout도 설정함
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.labels_array,android.R.layout.simple_spinner_item);

        // 선택할 list가 나타날때 사용할 layout을 특징화함
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinner에 adapter를 적용함함
        if(spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtononClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.sameday:
                if(checked){
                    displayToast(getString(R.string.same_day_messenger_service));
                    break;
                }
            case R.id.nextday:
                if(checked){
                    displayToast(getString(R.string.next_day_ground_delivery));
                    break;
                }
            case R.id.pickup:
                if(checked){
                    displayToast(getString(R.string.pick_up));
                    break;
                }
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),getString(R.string.datepicker));
    }

    public void processDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        Toast.makeText(this, getString(R.string.date) + dateMessage,Toast.LENGTH_SHORT).show();
    }
}