package com.example.detailpart;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.detailpart.data.MovieInfo;

public class Fragment1 extends Fragment {
    MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    TextView title;
    ImageView imageView;
    TextView reservation_rate;
    TextView grade;
    TextView date;

    int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        title = (TextView)rootView.findViewById(R.id.title);
        imageView = (ImageView)rootView.findViewById(R.id.imageView);
        reservation_rate = (TextView)rootView.findViewById(R.id.reservation_gradeRate);
        grade = (TextView)rootView.findViewById(R.id.grade);
        date = (TextView)rootView.findViewById(R.id.date);

        // 액티비티에서 번들에 담아 보내준 인덱스값 받기
        Bundle bundle = getArguments();
        if(bundle != null) {
            index = bundle.getInt("index");
        }

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(1);// 액티비티의 메서드를 호출하여 상세 화면 프래그먼트 얹기
            }
        });

        // 해당 인덱스값의 데이터를 movieInfo에 삽입하여 활용
        MovieInfo movieInfo = activity.movies.get(index);

        String urlStr = movieInfo.image;
        sendImageRequest(urlStr);

        // movieInfo 속 데이터로 프래그먼트 꾸며주기
        title.setText(movieInfo.id +". "+ movieInfo.title);
        reservation_rate.setText("예매율 " + movieInfo.reservation_rate + " | ");
        grade.setText(movieInfo.grade + "세 관람가");
        date.setText(" | " + movieInfo.date);

        return rootView;
    }

    // 이미지를 다운로드 받아서 이미지뷰에 표시하기 위한 메서드
    public void sendImageRequest(String urlStr) {
        String url = urlStr;

        ImageLoadTask task = new ImageLoadTask(url, imageView);
        task.execute();// 실행됨
    }

}
