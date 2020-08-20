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

public class FragmentMovielist4 extends Fragment {
    FragmentMoviedetail4 fragmentMoviedetail4;
    MainActivity activity;
    Button showdetail_button4;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movielist4,container,false);

        fragmentMoviedetail4 = new FragmentMoviedetail4();
        showdetail_button4 = rootView.findViewById(R.id.showdetail_button4);
        showdetail_button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(4);

            }
        });

        return rootView;
    }
}
