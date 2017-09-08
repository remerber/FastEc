package example.fastec.com.wang.latte.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

import example.fastec.com.wang.latte.delegates.web.event.Event;
import example.fastec.com.wang.latte.delegates.web.event.EventManager;
import example.fastec.com.wang.latte.util.log.LatteLogger;

/**
 * Created by HP on 2017/8/15.
 */

public class LatteWebInterface {

    private final WebDelegate DELEGATE;


    public LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        LatteLogger.d("WEB_EVENT", params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
