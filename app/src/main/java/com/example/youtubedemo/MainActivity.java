package com.example.youtubedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.youtubedemo.adapter.AdaperViewPager;
import com.example.youtubedemo.fragment.VideoFragment;
import com.example.youtubedemo.util.Constant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bottomNav) BottomNavigationView btnView;

    private List<VideoFragment> fragmentList =  new ArrayList<>();
    AdaperViewPager adapter;
    VideoFragment videoFragmentKurio;
    VideoFragment videoFragmentKurioMultiSong;
    VideoFragment videoFragmentSesam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        videoFragmentKurio = new VideoFragment("Kurio Songs",Constant.PLAYLIST_ID_VIDEO_SINGLE_KURIO,30);
        videoFragmentKurioMultiSong = new VideoFragment("Kurio Muti-Song",Constant.PLAYLIST_ID_MULTI_SONG_KURIO,30);
        videoFragmentSesam = new VideoFragment("Sesam Street songs",Constant.PLAYLIST_ID_SONG_SESAM,30);


        fragmentList.clear();
        fragmentList.add(videoFragmentKurio);
        fragmentList.add(videoFragmentKurioMultiSong);
        fragmentList.add(videoFragmentSesam);

        for (int i=0;i<fragmentList.size();i++) {
            btnView.getMenu().add(Menu.NONE,i , Menu.NONE, fragmentList.get(i).getName()).setIcon(R.mipmap.ic_launcher);
        }


        viewpager.setOffscreenPageLimit(2);
        adapter = new AdaperViewPager(getSupportFragmentManager(),fragmentList);
        viewpager.setAdapter(adapter);


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                btnView.getMenu().getItem(position).setChecked(true);
                getSupportActionBar().setTitle(viewpager.getAdapter().getPageTitle(position));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                viewpager.setCurrentItem(item.getItemId());
                return true;
            }
        });
    }
}