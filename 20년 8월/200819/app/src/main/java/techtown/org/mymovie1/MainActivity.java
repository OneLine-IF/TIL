package techtown.org.mymovie1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    FragmentMovielist fragmentMovielist;
    FragmentMoviedetail1 fragmentMoviedetail1;
    FragmentMoviedetail2 fragmentMoviedetail2;
    FragmentMoviedetail3 fragmentMoviedetail3;
    FragmentMoviedetail4 fragmentMoviedetail4;
    FragmentMoviedetail5 fragmentMoviedetail5;
    FragmentMoviedetail6 fragmentMoviedetail6;
    DrawerLayout drawerLayout;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMovielist = new FragmentMovielist();
        fragmentMoviedetail1 = new FragmentMoviedetail1();
        fragmentMoviedetail2 = new FragmentMoviedetail2();
        fragmentMoviedetail3 = new FragmentMoviedetail3();
        fragmentMoviedetail4 = new FragmentMoviedetail4();
        fragmentMoviedetail5 = new FragmentMoviedetail5();
        fragmentMoviedetail6 = new FragmentMoviedetail6();


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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
                    transaction.replace(R.id.container,fragmentMovielist);
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


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container,fragmentMovielist);
        transaction.commit();

    }

    public void onFragmentChange(int index) {
        if (index == 0) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container,fragmentMovielist);
            transaction.commit();
        } else if(index == 1) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail1);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(index == 2) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail2);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(index == 3) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail3);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(index == 4) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail4);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(index == 5) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail5);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (index == 6) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragmentMoviedetail6);
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



}