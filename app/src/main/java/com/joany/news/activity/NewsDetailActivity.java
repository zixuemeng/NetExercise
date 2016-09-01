package com.joany.news.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.joany.news.NewsDetailInterface;
import com.joany.news.R;
import com.joany.news.bean.NewsEntity;
import com.joany.news.net.Constant;
import com.joany.news.presenter.NewsDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joany on 2016/9/1.
 */
public class NewsDetailActivity extends AppCompatActivity implements NewsDetailInterface{

    @Bind(R.id.collapsing_iv)
    ImageView imageView;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.webview)
    WebView webView;
    private NewsEntity newsEntity;
    private NewsDetailPresenter newsDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(0);

        newsEntity = getIntent().getParcelableExtra(Constant.NEWSDETAIL);

        collapsingToolbarLayout.setTitle(newsEntity.getTitle());

        Glide.with(this).load(newsEntity.getPictureUrl()).into(imageView);

        newsDetailPresenter = new NewsDetailPresenter(this);
        newsDetailPresenter.loadNewsDetail(newsEntity.getUrl());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showNewsDetailContent(String detailUrl) {
        webView.loadUrl(detailUrl);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
