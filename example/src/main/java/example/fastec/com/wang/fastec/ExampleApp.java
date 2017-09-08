package example.fastec.com.wang.fastec;

import android.app.Application;
import android.support.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;
import example.fastec.com.wang.fastec.event.ShareEvent;
import example.fastec.com.wang.latte.app.Latte;
import example.fastec.com.wang.latte.ec.database.DatabaseManager;
import example.fastec.com.wang.latte.ec.icon.FontEcModule;
import example.fastec.com.wang.latte.util.callback.CallbackManager;
import example.fastec.com.wang.latte.util.callback.CallbackType;
import example.fastec.com.wang.latte.util.callback.IGlobalCallback;

/**
 * Created by HP on 2017/7/30.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://news.baidu.com/")
                .withJavascriptInterface("latte")
                .withWebEvent("share", new ShareEvent())
                .configure();
        DatabaseManager.getInstance().init(this);
        Stetho.initializeWithDefaults(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
    }
}
