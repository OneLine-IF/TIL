package com.example.detailpart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;
import java.util.HashMap;

// 이미지를 인터넷 혹은 웹 서버에서 받아와서 비트맵으로 바꾼 다음에 이미지 뷰에 설정하는 방법
public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    private String urlStr;
    private ImageView imageView;

    // 해쉬태그로 요청 url과 비트맵 객체를 맵핑해놓는 것
    private static HashMap<String, Bitmap> bitmapHash = new HashMap<String, Bitmap>();

    public ImageLoadTask(String urlStr, ImageView imageView) {
            this.urlStr = urlStr;
            this.imageView = imageView;
            // 생성자로 프로그래스바를 받아서
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;

        try {// 예외가 발생할 수 있으므로 try, catch로 묶어줌
            if (bitmapHash.containsKey(urlStr)) {
                Bitmap oldBitmap = bitmapHash.remove(urlStr);
                if(oldBitmap != null) {
                    oldBitmap.recycle();
                    oldBitmap = null;
                }
            }

            URL url = new URL(urlStr);
            // 주소로 접속해서 스트림을 받는데, 이것이 이미지 스트림이면 그대로 넘어오고 decodeStream을 통해 비트맵으로 바꿔주는 것
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            // 지금 현재 만든 것을 해쉬에 저장. 그래야 다음번에 요청할 때 이전에 만들었던 건 없어져 버림.
            bitmapHash.put(urlStr, bitmap);

        } catch(Exception e) {
            e.printStackTrace();
        }

        // 여기서 리턴해주는 값은 onPostExecute로 넘어감
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

        // 이미지가 보여지는 시점에 프로그래스바 사라지게
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap);
        imageView.invalidate();
    }
}
