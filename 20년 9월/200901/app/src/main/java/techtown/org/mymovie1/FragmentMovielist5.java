package techtown.org.mymovie1;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import techtown.org.mymovie1.data.MovieInfo;
import techtown.org.mymovie1.data.MovieList;
import techtown.org.mymovie1.data.ResponseCheck;

public class FragmentMovielist5 extends Fragment {
    FragmentMoviedetail fragmentMoviedetail;
    MainActivity activity;
    Button showdetail_button5;
    TextView moviename_textview5,movierate_textview5;
    ImageView mainimage_imageview5;
    MovieList movieList;
    MovieInfo movieInfo;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movielist5,container,false);
        moviename_textview5 = (TextView) rootView.findViewById(R.id.moviename_textview5);
        movierate_textview5 = (TextView) rootView.findViewById(R.id.movierate_textview5);
        mainimage_imageview5 = (ImageView) rootView.findViewById(R.id.mainimage_imageview5);
        requestMovieList();
        fragmentMoviedetail = new FragmentMoviedetail();
        showdetail_button5 = rootView.findViewById(R.id.showdetail_button5);
        showdetail_button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("list1", 5);
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

        ImageLoadTask task = new ImageLoadTask(url,mainimage_imageview5);
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
            movieList = gson.fromJson(response, MovieList.class);

            movieInfo = movieList.result.get(4);
            moviename_textview5.setText("5."+movieInfo.title);
            String rate = Float.toString(movieInfo.reservation_rate);
            String grade = Integer.toString(movieInfo.grade);
            movierate_textview5.setText("예매율 "+rate+"%"+" | "+grade+"세 관람가");


        }
    }
}
