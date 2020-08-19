package techtown.org.mymovie1;

import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class FragmentMovielist extends Fragment {
    MainActivity activity;
    ViewPager movielistPager;


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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movielist,container,false);

        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("영화 목록");
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);

        movielistPager = (ViewPager) rootView.findViewById(R.id.movielist_pager);
        movielistPager.setOffscreenPageLimit(3);

        MovieListAdapter adapter = new MovieListAdapter(getChildFragmentManager());
        FragmentMovielist1 fragmentMovielist1 = new FragmentMovielist1();
        adapter.addItem(fragmentMovielist1);

        FragmentMovielist2 fragmentMovielist2 = new FragmentMovielist2();
        adapter.addItem(fragmentMovielist2);

        FragmentMovielist3 fragmentMovielist3 = new FragmentMovielist3();
        adapter.addItem(fragmentMovielist3);

        FragmentMovielist4 fragmentMovielist4 = new FragmentMovielist4();
        adapter.addItem(fragmentMovielist4);

        FragmentMovielist5 fragmentMovielist5 = new FragmentMovielist5();
        adapter.addItem(fragmentMovielist5);

        FragmentMovielist6 fragmentMovielist6 = new FragmentMovielist6();
        adapter.addItem(fragmentMovielist6);

        movielistPager.setAdapter(adapter);

        return rootView;
    }

    class MovieListAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> lists = new ArrayList<Fragment>();

        public MovieListAdapter(@NonNull FragmentManager fm) {super(fm);}

        public void addItem(Fragment list) {lists.add(list);}
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return lists.get(position);
        }

        @Override
        public int getCount() {
            return lists.size();
        }
    }
}
