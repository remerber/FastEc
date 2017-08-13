package example.fastec.com.wang.fastec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import example.fastec.com.wang.latte.app.Latte;
import example.fastec.com.wang.latte.ec.database.DatabaseManager;
import example.fastec.com.wang.latte.ec.icon.FontEcModule;

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
                .configure();
        DatabaseManager.getInstance().init(this);
        Stetho.initializeWithDefaults(this);

    }
}
