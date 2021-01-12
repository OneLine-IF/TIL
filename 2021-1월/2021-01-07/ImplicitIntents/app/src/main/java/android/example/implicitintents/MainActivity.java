package android.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

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
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);//action 과 data 원래는 context 와 보낼 곳
        if (intent.resolveActivity(getPackageManager()) != null){//implicit intent 를 다룰 수 있는 activity 가 하나라도 있는 것임.
            startActivity(intent);
        }else{//Intent 가 해결이 안된 상태
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q="+loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null){//implicit intent 를 다룰 수 있는 activity 가 하나라도 있는 것임.
            startActivity(intent);
        }else{//Intent 가 해결이 안된 상태
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void ShareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle(R.string.share_text_with).setText(txt).startChooser();
    }

    private boolean isExistsCameraApplication(){// 카메라 처리 가능한 앱이 있는지 검색
        PackageManager packageManager = getPackageManager();//Android 의 모든 Application 을 얻어온다.
        Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // 카메라 앱
        List<ResolveInfo> cameraApps = packageManager.queryIntentActivities(cameraApp, PackageManager.MATCH_DEFAULT_ONLY);
        //Media.ACTION_IMAGE_CAPTURE 의 Intent 를 처리할 수 있는 앱 정보가져오기
        return cameraApps.size()>0;
    }

    public void openCamera(View view) {
        if(isExistsCameraApplication()){
            //카메라 앱 실행
            Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(cameraApp);
        }
    }
}