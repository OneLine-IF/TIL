package techtown.org.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        // URL 주소
        String url = mWebsiteEditText.getText().toString();
        // URI를 복사하고 Intent 생성
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
        // intent를 다룰 Activity를 찾고 Activity를 시작함
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents","Can't handle this!");
        }
    }

    public void openLocation(View view) {
        // 위치를 가르키는 String을 받음, Input은 유효하지 않음, location handler에 직접 전달하는 용임
        String loc = mLocationEditText.getText().toString();

        // location을 복사하고 Intent 생성
        Uri addressUri = Uri.parse("geo:0,0?q=" +loc);
        Intent intent = new Intent(Intent.ACTION_VIEW,addressUri);

        // intent를 다룰 Activity를 찾고, Activity를 시작함
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents","Can't handle this intent! ");
        }
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
}