package com.example.project2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView likeCountView;
    TextView dislikeCountView;
    Button likeButton;
    Button dislikeButton;
    int likeCount = 15;
    int dislikeCount = 1;
    //ReViewAdapter adapter;
    ScrollView scrollView;
    RatingBar ratingBar2;
    TextView outputView;
    ListView listView;
    boolean likeState = false;
    boolean dislikeState = false;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    FragmentTransaction transaction;
    MovieDetailFragment moviedetailfragment;
    private DrawerLayout drawerLayout;
    private View drawerView;
    ActionBar actionBar;
    androidx.appcompat.widget.Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        int margin = 200;
        //뷰페이저
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setClipToPadding(false);
        pager.setPadding(margin, 0, margin, 0); // int margin = [원하는 padding의 정도]
        pager.setPageMargin(margin/2);
        MoviePagerAdapter adapter2 = new MoviePagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter2.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter2.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter2.addItem(fragment3);

        pager.setAdapter(adapter2);

        moviedetailfragment = new MovieDetailFragment();

        //툴바 생성 및 세팅하는 부분
        myToolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("영화 목록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawerView = (View) findViewById(R.id.drawerView);


    }
    public void onFragmentChange(int index){
        if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,moviedetailfragment).commit();
        }
    }


    public class MoviePagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public MoviePagerAdapter(@NonNull FragmentManager fm){
            super(fm);
        }

        public void addItem(Fragment item){
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}