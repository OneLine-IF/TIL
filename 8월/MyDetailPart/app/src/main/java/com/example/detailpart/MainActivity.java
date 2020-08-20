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

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Context context = this;

    DetailFragment detailFragment;

    TextView toolbarText;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailFragment = new DetailFragment();

        toolbarText = (TextView) findViewById(R.id.toolbar_title);

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
                    getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();
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
        pager.setOffscreenPageLimit(6);// 최대 캐슁 개수 = 6

        pager.setClipToPadding(false);
        pager.setPadding(100,0,100,0);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager());

        // 어뎁터에 아이템 추가
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

        pager.setAdapter(adapter);// 어뎁터의 내용물이 뷰페이저에 보이도록 설정

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
    public void onFragmentChange(int index) {
        if (index == 0) {
            toolbarText.setText("영화 목록");
            getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();
        } else if (index == 1) {
            toolbarText.setText("영화 상세");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment);
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