package techtown.org.mymovie1;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import techtown.org.mymovie1.data.MovieInfo;
import techtown.org.mymovie1.data.MovieList;
import techtown.org.mymovie1.data.ResponseCheck;

public class FragmentMovielist1 extends Fragment {
    FragmentMoviedetail fragmentMoviedetail;
    MainActivity activity;
    Button showdetail_button1;
    TextView moviename_textview,movierate_textview;
    ImageView mainimage_imageview;
    MovieList movieList;
    MovieInfo movieInfo;
    ShowAllActivity showAllActivity;
    WriteActivity writeActivity;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movielist1,container,false);
        moviename_textview = (TextView) rootView.findViewById(R.id.moviename_textview);
        movierate_textview = (TextView) rootView.findViewById(R.id.movierate_textview);
        mainimage_imageview = (ImageView) rootView.findViewById(R.id.mainimage_imageview);
        requestMovieList();


        showdetail_button1 = rootView.findViewById(R.id.showdetail_button1);

        showdetail_button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("list1", 1);
                fragmentMoviedetail = new FragmentMoviedetail();
                fragmentMoviedetail.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentMoviedetail).addToBackStack(null).commit();
                activity.getSupportActionBar().setTitle("영화 상세");
            }
        });

        return rootView;
    }

    public void sendImageRequest() {
        String url = movieInfo.image;

        ImageLoadTask task = new ImageLoadTask(url,mainimage_imageview);
        task.execute();
    }

    public void requestMovieList() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                        sendImageRequest();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }


    public void processResponse(String response) {
        Gson gson = new Gson();

        ResponseCheck check = gson.fromJson(response, ResponseCheck.class);
        if(check.code == 200) {
            movieList = gson.fromJson(response,MovieList.class);

            movieInfo = movieList.result.get(0);
            moviename_textview.setText("1."+movieInfo.title);
            String rate = Float.toString(movieInfo.reservation_rate);
            String grade = Integer.toString(movieInfo.grade);
            movierate_textview.setText("예매율 "+rate+"%"+" | "+grade+"세 관람가");


        }
    }

}
