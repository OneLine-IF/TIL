package com.example.detailpart;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PagerFragment extends Fragment {
    ViewPager pager;
    MainActivity activity;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pager_fragment, container, false);

        pager = (ViewPager) rootView.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(6);// 캐슁하는 것이 6개까지 늘어남

        MoviePagerAdapter adapter = new MoviePagerAdapter(getChildFragmentManager());
        // pager.setAdapter(adapter); -> 없어도 실행됨 (공부 필요)

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        Fragment4 fragment4 = new Fragment4();
        adapter.addItem(fragment4);

        Fragment5 fragment5 = new Fragment5();
        adapter.addItem(fragment5);

        Fragment6 fragment6 = new Fragment6();
        adapter.addItem(fragment6);

        pager.setAdapter(adapter);

        return rootView;
    }
    // FragmentStatePagerAdapter 대신 FragmentPagerAdapter 사용하니 정상 실행됨 (공부 필요)
    class MoviePagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MoviePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

    }
}
