package example.fastec.com.wang.fastec.event;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import example.fastec.com.wang.latte.delegates.web.event.Event;
import example.fastec.com.wang.latte.util.log.LatteLogger;

/**
 * Created by HP on 2017/8/17.
 */

public class ShareEvent extends Event {
    @Override
    public String execute(String params) {

        LatteLogger.json("ShareEvent", params);
        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

        Toast.makeText(getContext(), title + text, Toast.LENGTH_SHORT).show();


        return null;
    }
}
