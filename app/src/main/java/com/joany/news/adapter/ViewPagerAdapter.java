package com.joany.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joany on 2016/9/1.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list = new ArrayList<>();
    List<String> title = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm,List<Fragment> list, List<String> title) {
        super(fm);
        this.list = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return list != null ? list.get(position) : null;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title != null ? title.get(position) : null;
    }
}
