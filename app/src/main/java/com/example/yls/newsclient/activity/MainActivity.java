package com.example.yls.newsclient.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.yls.newsclient.R;
import com.example.yls.newsclient.base.BaseActivity;
import com.example.yls.newsclient.fragment.MainFragment01;
import com.example.yls.newsclient.fragment.MainFragment02;
import com.example.yls.newsclient.fragment.MainFragment03;
import com.example.yls.newsclient.fragment.MainFragment04;
import com.example.yls.newsclient.fragment.MainFragment05;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initViewPager();
        mRadioGroup = (RadioGroup) findViewById(R.id.id_main_radio_group);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_main_drawerLayout);
        initNavigationView();
        initToolBar();
        initDrawerToggle();
    }

    //ActionbarDrawerToggle 用来绑定ActionBar（ToolBar）和DrawerLayout的一个控件
    private void initDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,0,0);
//       mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();  //同步

    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        setSupportActionBar(mToolbar);  //用Toolbar代替 ActionBar
//        mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbar.setTitle("广交院");
//        mToolbar.setSubtitle("子标题");
//        mToolbar.setTitleTextColor(Color.RED);
        //显示左上角返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //ToolBar标题栏右侧点击菜单  创建
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //定义可点击的菜单项
        getMenuInflater().inflate(R.menu.main_nav_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //ToolBar标题栏右侧点击菜单  点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_main_item01:
                showToast("item01");
                break;
            case R.id.id_main_item02:
                showToast("item02");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.id_main_navigation_view);
        //点击事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_menu_01:
                        showToast("menu_01");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.id_menu_02:
                        showToast("menu_02");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.id_menu_03:
                        showToast("menu_03");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.id_menu_04:
                        showToast("menu_04");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.id_menu_05:
                        showToast("menu_05");
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });
        View view = mNavigationView.getHeaderView(0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("head");
            }
        });
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.id_main_view_pager);
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MainFragment01());
        fragmentList.add(new MainFragment02());
        fragmentList.add(new MainFragment03());
        fragmentList.add(new MainFragment04());
        fragmentList.add(new MainFragment05());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
    }

    @Override
    protected void initListener() {
        //底部点击事件监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.id_main_radio_button01:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.id_main_radio_button02:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.id_main_radio_button03:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.id_main_radio_button04:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.id_main_radio_button05:
                        mViewPager.setCurrentItem(4);
                        break;
                }
            }
        });
        //viewPager选中时底部变化
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.id_main_radio_button01);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.id_main_radio_button02);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.id_main_radio_button03);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.id_main_radio_button04);
                        break;
                    case 4:
                        mRadioGroup.check(R.id.id_main_radio_button05);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initDate() {

    }
}
