package techtown.org.mymovie1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

import techtown.org.mymovie1.data.MovieInfo;
import techtown.org.mymovie1.data.MovieList;
import techtown.org.mymovie1.data.ResponseCheck;

public class MainActivity extends AppCompatActivity {
    FragmentMoviedetail fragmentMoviedetail;
    FragmentMovielist1 fragmentMovielist1;
    FragmentMovielist2 fragmentMovielist2;
    FragmentMovielist3 fragmentMovielist3;
    FragmentMovielist4 fragmentMovielist4;
    FragmentMovielist5 fragmentMovielist5;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ViewPager movielistPager;
    Context context = this;
    int itemid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMoviedetail = new FragmentMoviedetail();




        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("영화 목록");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();

                itemid = item.getItemId();


                if (itemid == R.id.nav_list){
                    getSupportFragmentManager().popBackStack(); // 영화목록1의 상세페이지 제거
                    getSupportActionBar().setTitle("영화 목록");
                } else if(itemid == R.id.nav_review) {
                    Toast.makeText(context, " 영화 API 버튼 ",Toast.LENGTH_LONG).show();
                } else if (itemid == R.id.nav_book) {
                    Toast.makeText(context, " 예매하기 버튼 ",Toast.LENGTH_LONG).show();
                } else if (itemid == R.id.settings) {
                    Toast.makeText(context, " 설정하기 버튼 ", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });


        movielistPager = (ViewPager) findViewById(R.id.movielist_pager);
        movielistPager.setOffscreenPageLimit(3);

        movielistPager.setClipToPadding(false);
        movielistPager.setPadding(100,0,100,0);
        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        MovieListAdapter adapter = new MovieListAdapter(getSupportFragmentManager());
        fragmentMovielist1 = new FragmentMovielist1();
        adapter.addItem(fragmentMovielist1);

        fragmentMovielist2 = new FragmentMovielist2();
        adapter.addItem(fragmentMovielist2);

        fragmentMovielist3 = new FragmentMovielist3();
        adapter.addItem(fragmentMovielist3);

        fragmentMovielist4 = new FragmentMovielist4();
        adapter.addItem(fragmentMovielist4);

        fragmentMovielist5 = new FragmentMovielist5();
        adapter.addItem(fragmentMovielist5);


        movielistPager.setAdapter(adapter);



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }


        return super.onOptionsItemSelected(item);
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