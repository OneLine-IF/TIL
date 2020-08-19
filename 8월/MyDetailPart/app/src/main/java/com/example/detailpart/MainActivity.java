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
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Context context = this;

    PagerFragment pagerFragment;
    DetailFragment detailFragment;

    TextView toolbarText;

    int curIndex;// 네비게이션 호출과 버튼 호출 코드의 중복을 방지하기 위한 전역 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerFragment = new PagerFragment();
        detailFragment = new DetailFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, pagerFragment).commit();
        curIndex = 0;

        toolbarText = (TextView) findViewById(R.id.toolbar_title);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);// 기존 타이틀 지우기
        actionBar.setDisplayHomeAsUpEnabled(true);// 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_hamburger_menu);// 뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.account && curIndex != 0){
                    toolbarText.setText("영화 목록");
                    Toast.makeText(context, "영화 목록", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().add(R.id.container, pagerFragment).commit();
                    curIndex = 0;
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

    }
    // 바로가기 메뉴 툴바 관련 메서드
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
            getSupportFragmentManager().beginTransaction().add(R.id.container, pagerFragment).commit();
            curIndex = 0;
        } else if (index == 1) {
            toolbarText.setText("영화 상세");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.container,detailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            curIndex = 1;
        }
    }
}