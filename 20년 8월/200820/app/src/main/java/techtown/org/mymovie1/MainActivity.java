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
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentMoviedetail1 fragmentMoviedetail1;
    FragmentMoviedetail2 fragmentMoviedetail2;
    FragmentMoviedetail3 fragmentMoviedetail3;
    FragmentMoviedetail4 fragmentMoviedetail4;
    FragmentMoviedetail5 fragmentMoviedetail5;
    FragmentMoviedetail6 fragmentMoviedetail6;
    DrawerLayout drawerLayout;
    ViewPager movielistPager;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMoviedetail1 = new FragmentMoviedetail1();
        fragmentMoviedetail2 = new FragmentMoviedetail2();
        fragmentMoviedetail3 = new FragmentMoviedetail3();
        fragmentMoviedetail4 = new FragmentMoviedetail4();
        fragmentMoviedetail5 = new FragmentMoviedetail5();
        fragmentMoviedetail6 = new FragmentMoviedetail6();


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

                int id = item.getItemId();
                String title = item.getTitle().toString();

                if (id == R.id.nav_list){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.remove(fragmentMoviedetail1);
                    transaction.remove(fragmentMoviedetail2);
                    transaction.remove(fragmentMoviedetail3);
                    transaction.remove(fragmentMoviedetail4);
                    transaction.remove(fragmentMoviedetail5);
                    transaction.remove(fragmentMoviedetail6);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if(id == R.id.nav_review) {
                    Toast.makeText(context, " 영화 API 버튼 ",Toast.LENGTH_LONG).show();
                } else if (id == R.id.nav_book) {
                    Toast.makeText(context, " 예매하기 버튼 ",Toast.LENGTH_LONG).show();
                } else if (id == R.id.settings) {
                    Toast.makeText(context, " 설정하기 버튼 ", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });



        movielistPager = (ViewPager) findViewById(R.id.movielist_pager);
        movielistPager.setOffscreenPageLimit(3);

        movielistPager.setClipToPadding(false);
        movielistPager.setPadding(100,0,100,0);

        MovieListAdapter adapter = new MovieListAdapter(getSupportFragmentManager());
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

    }

    public void onFragmentChange(int index) {
         if(index == 1) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail1);
            getSupportActionBar().setTitle("영화 상세");
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(index == 2) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail2);
             getSupportActionBar().setTitle("영화 상세");
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(index == 3) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail3);
             getSupportActionBar().setTitle("영화 상세");
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(index == 4) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail4);
             getSupportActionBar().setTitle("영화 상세");
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(index == 5) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail5);
             getSupportActionBar().setTitle("영화 상세");
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (index == 6) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail6);
            getSupportActionBar().setTitle("영화 상세");
            transaction.addToBackStack(null);
            transaction.commit();
        }
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