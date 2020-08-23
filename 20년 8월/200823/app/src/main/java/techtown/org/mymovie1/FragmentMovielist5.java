package techtown.org.mymovie1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMovielist5 extends Fragment {
    FragmentMoviedetail fragmentMoviedetail;
    MainActivity activity;
    Button showdetail_button5;

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

        fragmentMoviedetail = new FragmentMoviedetail();
        showdetail_button5 = rootView.findViewById(R.id.showdetail_button5);
        showdetail_button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //activity.onFragmentChange(1);


            }
        });

        return rootView;
    }
}
