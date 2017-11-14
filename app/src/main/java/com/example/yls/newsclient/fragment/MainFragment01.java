package com.example.yls.newsclient.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.yls.newsclient.R;
import com.example.yls.newsclient.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yls on 2017/6/27.
 */

public class MainFragment01 extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_01;
    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager) mRootView.findViewById(R.id.id_fragment_01_viewPager);
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.id_fragment_01_tabLayout);

        initViewPager();
    }

    private void initViewPager() {
        final String title[] = new String[]{
                "头条", "社会", "科技", "财经", "体育", "汽车"
        };
        final String[] channelId = new String[]{
                "T1348647909107",
                "T1348648037603",
                "T1348649580692",
                "T1348648756099",
                "T1348649079062",
                "T1348654060988",
        };
        final List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setChannelId(channelId[i]);
            fragmentList.add(newsFragment);
        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return title.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initDate() {

    }


}
