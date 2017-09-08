package example.fastec.com.wang.latte.delegates.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import example.fastec.com.wang.latte.delegates.web.chromeclient.WebChromeClientImpl;
import example.fastec.com.wang.latte.delegates.web.client.WebViewClientImpl;
import example.fastec.com.wang.latte.delegates.web.route.RouteKeys;
import example.fastec.com.wang.latte.delegates.web.route.Router;

/**
 * Created by HP on 2017/8/16.
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }


    @Override
    public boolean onBackPressedSupport() {
        if (getWebView().canGoBack()) {   //表示按返回键时的操作
            getWebView().goBack();// 后退

            // webview.goForward();//前进
            return true; // 已处理
        }
        return false;

    }
}



