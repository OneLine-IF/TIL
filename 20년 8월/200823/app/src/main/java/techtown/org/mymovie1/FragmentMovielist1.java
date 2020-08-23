package techtown.org.mymovie1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentMovielist1 extends Fragment {
    FragmentMovielist1 fragmentMovielist1;
    FragmentMoviedetail fragmentMoviedetail;
    MainActivity activity;
    Button showdetail_button1;


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


}
