package com.joany.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joany.news.NewsInterface;
import com.joany.news.R;
import com.joany.news.activity.NewsDetailActivity;
import com.joany.news.adapter.RecyclerViewAdapter;
import com.joany.news.base.BaseApplication;
import com.joany.news.bean.NewsEntity;
import com.joany.news.net.Constant;
import com.joany.news.presenter.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,NewsInterface{
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int pageIndex = 1;
    private List<NewsEntity> data;
    private LinearLayoutManager linearLayoutManager;
    private NewsPresenter newsPresenter;
    private RecyclerViewAdapter adapter;
    private int titleId;

    private static NewsListFragment newsListFragment;

    public NewsListFragment(){
    }

    public static NewsListFragment getInstance(int titleId){
        newsListFragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt("args", titleId);
        newsListFragment.setArguments(args);
        return newsListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            titleId = getArguments().getInt("args");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list,container,false);
        ButterKnife.bind(this, v);

        swipeRefreshLayout.setColorSchemeColors(R.color.colorAccent,
                R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(getContext().getApplicationContext());
        adapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(onScrollListener);

        newsPresenter = new NewsPresenter(this,titleId);
        onRefresh();
        return v;
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem+1 == adapter.getItemCount()
                    && adapter.isShowFooter()) {
                newsPresenter.loadNews(titleId,pageIndex++);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
        }
    };

    private RecyclerViewAdapter.OnItemClickListener onItemClickListener =
            new RecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NewsEntity newsEntity = adapter.getItem(position);
            Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
            intent.putExtra(Constant.NEWSDETAIL,newsEntity);
            startActivity(intent);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void add(List<NewsEntity> list) {
        adapter.setShowFooter(true);
        if(data == null) {
            data = new ArrayList<>();
        }
        if(list != null) {
            data.addAll(list);
        }
        if(pageIndex == 1) {
            adapter.setData(data);
        } else {
            if(list == null || list.size() == 0){
                adapter.setShowFooter(false);
            }
            adapter.notifyDataSetChanged();
        }
        pageIndex += Constant.PAZE_SIZE;
    }

    @Override
    public void showProgress() {
        if(swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgress() {
        if(swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showLoadFail() {
        if(pageIndex == 1) {
            adapter.setShowFooter(false);
            adapter.notifyDataSetChanged();
        }

        Toast.makeText(BaseApplication.getContext(),"加载失败",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        if(data != null) {
            data.clear();
        }
        newsPresenter.loadNews(titleId,pageIndex);
    }
}
