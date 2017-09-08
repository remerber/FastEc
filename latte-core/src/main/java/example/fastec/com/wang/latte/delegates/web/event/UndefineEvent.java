package example.fastec.com.wang.latte.delegates.web.event;

import example.fastec.com.wang.latte.util.log.LatteLogger;

/**
 * Created by HP on 2017/8/17.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
