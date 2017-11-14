package com.example.yls.newsclient.activity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.yls.newsclient.NewsEntity;
import com.example.yls.newsclient.R;
import com.example.yls.newsclient.base.BaseActivity;

public class NewsDetailActivity extends BaseActivity {

    private static final String TAG = "NewsDetailActivity";

    private ProgressBar mProgressBar;
    private WebView mWebView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        initWebView();
    }

    private void initWebView() {
        Log.d(TAG, "initWebView: ======================");
        mWebView = (WebView) findViewById(R.id.web_view);

        mWebView.setWebViewClient(new WebViewClient());

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    mProgressBar.setVisibility(View.GONE);
                }else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                    Log.d(TAG, "onProgressChanged: ------------------");
                }
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initDate() {
        NewsEntity.ResultBean resultBean = (NewsEntity.ResultBean) getIntent().getSerializableExtra("news");
        mWebView.loadUrl(resultBean.getUrl());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(resultBean.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
