package com.example.youtubedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.youtubedemo.adapter.AdaperViewPager;
import com.example.youtubedemo.fragment.PlayListFragment;
import com.example.youtubedemo.fragment.VideoFragment;
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

    private List<Fragment> fragmentList =  new ArrayList<>();
    AdaperViewPager adapter;
    VideoFragment videoFragment;
    PlayListFragment playListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        videoFragment = new VideoFragment();
        playListFragment = new PlayListFragment();

        fragmentList.clear();
        fragmentList.add(videoFragment);
        fragmentList.add(playListFragment);

        viewpager.setOffscreenPageLimit(2);
        adapter = new AdaperViewPager(getSupportFragmentManager(),fragmentList);
        viewpager.setAdapter(adapter);

      /*  viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                btnView.getMenu().getItem(position).setChecked(true);
                if (position ==0){
                    getSupportActionBar().setTitle("Videos");
                }else if (position ==1){
                    getSupportActionBar().setTitle("Playlist");
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        btnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.videoId:
                        viewpager.setCurrentItem(0);
                        getSupportActionBar().setTitle("Single Songs");
                        break;

                    case R.id.playlistId:
                        viewpager.setCurrentItem(1);
                        getSupportActionBar().setTitle("Multi-song Collections");
                        break;

                }
                return true;
            }
        });
    }
}