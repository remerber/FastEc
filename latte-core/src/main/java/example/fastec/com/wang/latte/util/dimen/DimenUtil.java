package example.fastec.com.wang.latte.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import example.fastec.com.wang.latte.app.Latte;

/**
 * Created by HP on 2017/8/2.
 */

public class DimenUtil {

    public static int getScreenWidth() {

        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;

    }

    public static int getScreenHeight() {

        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;

    }
}
