package com.joany.news.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joany.news.R;
import com.joany.news.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsFragment extends Fragment {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news,null);
        ButterKnife.bind(this,view);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(),getFragmentList(),
                getFragmentTitle()));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private List<Fragment> getFragmentList(){
        List<Fragment> list = new ArrayList<>();
        list.add(NewsListFragment.getInstance(0));
        list.add(NewsListFragment.getInstance(1));
        list.add(NewsListFragment.getInstance(2));
        list.add(NewsListFragment.getInstance(3));
        return list;
    }

    private List<String> getFragmentTitle(){
        List<String> list = new ArrayList<>();
        list.add("科技");
        list.add("社会");
        list.add("体育");
        list.add("国际");
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
