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
import androidx.fragment.app.FragmentTransaction;

public class FragmentMovielist2 extends Fragment {
    FragmentMoviedetail fragmentMoviedetail;
    MainActivity activity;
    Button showdetail_button2;
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
}
