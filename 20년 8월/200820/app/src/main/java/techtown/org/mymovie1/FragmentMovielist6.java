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

public class FragmentMovielist6 extends Fragment {
    FragmentMoviedetail6 fragmentMoviedetail6;
    MainActivity activity;
    Button showdetail_button6;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movielist6,container,false);

        fragmentMoviedetail6 = new FragmentMoviedetail6();
        showdetail_button6 = rootView.findViewById(R.id.showdetail_button6);
        showdetail_button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(6);

            }
        });

        return rootView;
    }
}
