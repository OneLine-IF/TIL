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
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import techtown.org.mymovie1.data.MovieInfo;
import techtown.org.mymovie1.data.MovieList;
import techtown.org.mymovie1.data.ResponseCheck;

public class FragmentMovielist2 extends Fragment {
    FragmentMoviedetail fragmentMoviedetail;
    MainActivity activity;
    Button showdetail_button2;
    TextView moviename_textview2,movierate_textview2;
    ImageView mainimage_imageview2;
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movielist2,container,false);
        moviename_textview2 = (TextView) rootView.findViewById(R.id.moviename_textview2);
        movierate_textview2 = (TextView) rootView.findViewById(R.id.movierate_textview2);
        mainimage_imageview2 = (ImageView) rootView.findViewById(R.id.mainimage_imageview2);
        requestMovieList();

        showdetail_button2 = rootView.findViewById(R.id.showdetail_button2);
        showdetail_button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("list1", 2);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentMoviedetail = new FragmentMoviedetail();
                fragmentMoviedetail.setArguments(bundle);
                transaction.replace(R.id.container, fragmentMoviedetail).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    public void sendImageRequest() {
        String url = movieInfo.image;

        ImageLoadTask task = new ImageLoadTask(url,mainimage_imageview2);
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

            movieInfo = movieList.result.get(1);
            moviename_textview2.setText("2."+movieInfo.title);
            String rate = Float.toString(movieInfo.reservation_rate);
            String grade = Integer.toString(movieInfo.grade);
            movierate_textview2.setText("예매율 "+rate+"%"+" | "+grade+"세 관람가");


        }
    }
}
