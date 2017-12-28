package com.example.mjj.daytopnewschangetabs.fragment;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mjj.daytopnewschangetabs.R;
import com.example.mjj.daytopnewschangetabs.base.BaseFragment;
import com.example.mjj.daytopnewschangetabs.net.NetModel;
import com.example.mjj.daytopnewschangetabs.net.NetPresenter;

/**
 * Created by Administrator on 2017/12/5.
 */
public class ShowFragment extends BaseFragment<NetPresenter, NetModel> {
    private WebView mWebView;
    private int index;

    @Override
    protected void initBan() {

    }

    @Override
    protected void initData() {


        mWebView.loadUrl("http://m.univs.cn/article/" + index );

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }

    @Override
    protected void initView(View view) {
        mWebView = (WebView) view.findViewById(R.id.WebView);

        new NewsFragment().setClick(new NewsFragment.setResult() {
            @Override
            public void setResult(int str) {
                index = str;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_show;
    }



}
