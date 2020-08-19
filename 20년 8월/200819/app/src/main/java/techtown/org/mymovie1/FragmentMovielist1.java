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

public class FragmentMovielist1 extends Fragment {
    FragmentMovielist fragmentMovelist;
    FragmentMoviedetail1 fragmentMoviedetail1;
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movielist1,container,false);

        fragmentMoviedetail1 = new FragmentMoviedetail1();
        showdetail_button1 = rootView.findViewById(R.id.showdetail_button1);
        showdetail_button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(1);

            }
        });

        return rootView;
    }


}
