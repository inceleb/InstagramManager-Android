package com.dothome.inceleb.instamanager.Tab;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.dothome.inceleb.instamanager.Home.BlockActivity;
import com.dothome.inceleb.instamanager.Lib.instagram.InstagramSession;
import com.dothome.inceleb.instamanager.Lib.instagram.InstagramUser;
import com.dothome.inceleb.instamanager.LoginActivity;
import com.dothome.inceleb.instamanager.Home.NotFollowActivity;
import com.dothome.inceleb.instamanager.R;
import com.dothome.inceleb.instamanager.Home.RemovedActivity;
import com.dothome.inceleb.instamanager.Home.UnfollowActivity;

public class HomeActivity extends AppCompatActivity
        implements HomeFragment.OnFragmentInteractionListener,
        UserFragment.OnFragmentInteractionListener,
        NoticeFragment.OnFragmentInteractionListener,
        RankFragment.OnFragmentInteractionListener,
        BookmarkFragment.OnFragmentInteractionListener{
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private InstagramSession mInstagramSession;
    public static InstagramUser insta_user;
    private ViewPager mViewPager;
    public DrawerLayout drawer;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mInstagramSession=new InstagramSession(this);
        insta_user=mInstagramSession.getUser();
        if(insta_user==null){
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();

        }


        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0) {
                    tabLayout.setTabTextColors(
                            ContextCompat.getColor(getBaseContext(), R.color.colorDarkGrayText),
                            ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryText));
                } else if(position==1){

                    tabLayout.setTabTextColors(
                            ContextCompat.getColor(getBaseContext(), R.color.colorDarkGrayText),
                            ContextCompat.getColor(getBaseContext(), R.color.colorUserBackground));
                }else if(position==2){
                    tabLayout.setTabTextColors(
                            ContextCompat.getColor(getBaseContext(), R.color.colorDarkGrayText),
                            ContextCompat.getColor(getBaseContext(), R.color.colorPostBackground));

                }else if(position==3){
                    tabLayout.setTabTextColors(
                            ContextCompat.getColor(getBaseContext(), R.color.colorDarkGrayText),
                            ContextCompat.getColor(getBaseContext(), R.color.colorRankBackground));

                }else if(position==4){
                    tabLayout.setTabTextColors(
                            ContextCompat.getColor(getBaseContext(), R.color.colorDarkGrayText),
                            ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryText));

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }
    @Override
    public void onFragmentInteraction(Uri uri){

    }

    public void onToggleMenu(View view) {
        if( !drawer.isDrawerOpen(GravityCompat.START) ){
            drawer.openDrawer(GravityCompat.START);
        }
    }

    public void onLogout(View view) {
        mInstagramSession.reset();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }



    public void onClickBottomMenu(View view) {
        switch (view.getId()){
            case R.id.bottomMenu1:
                startActivity(new Intent(this, UnfollowActivity.class));

                break;
            case R.id.bottomMenu2:
                startActivity(new Intent(this, NotFollowActivity.class));
                break;
            case R.id.bottomMenu3:
                startActivity(new Intent(this, RemovedActivity.class));
                break;
            case R.id.bottomMenu4:
                startActivity(new Intent(this, BlockActivity.class));
                break;
        }
    }

    /*

                        tabLayout.setTabTextColors(
                                ContextCompat.getColor(getBaseContext(), R.color.colorDarkGrayText),
                                ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryText) );
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag=null;
            switch (position){
                case 0:
                    frag= HomeFragment.newInstance();

                    break;
                case 1:
                    frag= UserFragment.newInstance();

                    break;
                case 2:
                    frag= NoticeFragment.newInstance();

                    break;
                case 3:
                    frag= RankFragment.newInstance();


                    break;
                case 4:
                    frag= BookmarkFragment.newInstance();
                    break;
            }
            return frag;
        }


        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "홈";
                case 1:
                    return "사용자분석";
                case 2:
                    return "게시물분석";
                case 3:
                    return "랭킹";
                case 4:
                    return "즐겨찾기";
            }
            return null;
        }
    }
}
