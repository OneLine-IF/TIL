package com.example.detailpart;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.detailpart.data.MovieInfo;
import com.example.detailpart.data.MovieList;
import com.example.detailpart.data.ResponseInfo;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Context context = this;

    DetailFragment detailFragment;

    TextView toolbarText;

    ViewPager pager;

    MoviePagerAdapter adapter;

    ArrayList<DetailFragment> details = new ArrayList<DetailFragment>();// 각 영화별 상세화면 정보를 담아놓음

    int fragIndex = 0;// 뷰페이저의 현재 조각(사용자가 보고 있는)의 인덱스값을 저장

    String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovie" + "?" + "id=";

    ArrayList<MovieInfo> movies = new ArrayList<MovieInfo>();

    int tmp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailFragment = new DetailFragment();

        toolbarText = (TextView) findViewById(R.id.toolbar_title);

        // 영화 목록 데이터를 요청 -> requestMovieList 메서드가 아래의 코드보다 위에 있으면 에러 발생
        AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());// 이 코드가 requestMovieList 메서드보다 먼저 실행되어야 함
        for(int i=1; i<=5; i++) {
            requestMoveList(i);
        }

        // 툴바 관련 메서드
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);// 기존 타이틀 제거
        actionBar.setDisplayHomeAsUpEnabled(true);// 바로가기 메뉴 생성
        actionBar.setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);// 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // 바로가기 메뉴 관련 메서드
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.account ){
                    toolbarText.setText("영화 목록");
                    Toast.makeText(context, "영화 목록", Toast.LENGTH_SHORT).show();
                    // 상세 화면에서 바로가기 메뉴의 "영화 목록"을 눌렀을 때, 영화 상세 화면을 제거
                    getSupportFragmentManager().beginTransaction().remove(details.get(fragIndex)).commit();
                }
                else if(id == R.id.setting){
                    Toast.makeText(context,  "영화 API", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.logout){
                    Toast.makeText(context,  "예매하기", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        // 뷰페이저를 메인 액티비티에서 구현
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(5);// 최대 캐슁 개수 = 5

        pager.setClipToPadding(false);
        pager.setPadding(100,0,100,0);

        adapter = new MoviePagerAdapter(getSupportFragmentManager());

        // 현재 사용자가 어떤 조각을 보고 있는지 그 프래그먼트 조각의 인덱스를 얻기 위함
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragIndex = position;
                Toast.makeText(getApplicationContext(),"현재 조각 : " + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    // 데이터를 요청하고 응답을 받는 메서드(volley, gson 라이브러리를 gradle에 추가)
    public void requestMoveList(int i) {
        String urlstr = url + i;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlstr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Gson을 이용해 응답을 자바 객체로 바꾸는 메서드 호출
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"에러 발생", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // 영화 목록에서 요청 보냄
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    // 받은 응답을 Gson을 이용해 변환해주는 메서드
    public void processResponse(String response) {
        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        // 응답이 정상인지 확인
        if(info.code == 200){
            // 아까 Responseinfo에서 파싱해주지 않았던 것을 파싱
            MovieList movieList = gson.fromJson(response, MovieList.class);

            MovieInfo movieInfo = movieList.result.get(0);

            movies.add(movieInfo);

            // 영화 목록
            Fragment1 fragment1 = new Fragment1();

            // 번들에 인덱스값을 담아서 영화 목록 프래그먼트로 전달
            Bundle bundle = new Bundle(1);
            bundle.putInt("index", tmp);
            fragment1.setArguments(bundle);

            adapter.addItem(fragment1);

            pager.setAdapter(adapter);// 정보가 담긴 어뎁터를 뷰페이저에 장착

            // 영화 상세
            DetailFragment detailFragment = new DetailFragment();

            // 번들에 인덱스값을 담아서 영화 상세 프래그먼트로 전달
            Bundle bundle2 = new Bundle(1);
            bundle2.putInt("index2", tmp);
            detailFragment.setArguments(bundle2);

            details.add(detailFragment);

            tmp = tmp+1;
        }
    }

    // 좌측 상단 햄버거 아이콘이 눌리면 드로어 레이아웃을 보여주도록 함
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       switch (item.getItemId()) {
           case android.R.id.home:{
               mDrawerLayout.openDrawer(GravityCompat.START);
               return true;
           }
       }

        return super.onOptionsItemSelected(item);
    }

    // 프래그먼트 교체를 위한 메서드
    public void onFragmentChange(int option) {
        if (option == 0) {
            toolbarText.setText("영화 목록");
            getSupportFragmentManager().beginTransaction().remove(details.get(fragIndex)).commit();
        } else if (option == 1) {
            toolbarText.setText("영화 상세");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.container, details.get(fragIndex));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    // 뷰페이저에 구현할 정보들을 관리하는 어뎁터
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