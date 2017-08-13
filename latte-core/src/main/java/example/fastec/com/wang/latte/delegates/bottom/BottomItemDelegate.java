package example.fastec.com.wang.latte.delegates.bottom;

import android.widget.Toast;

import example.fastec.com.wang.latte.R;
import example.fastec.com.wang.latte.app.Latte;
import example.fastec.com.wang.latte.delegates.LatteDelegate;

/**
 * Createdby HP on 2017/8/8.
 */

public abstract class BottomItemDelegate extends LatteDelegate {

    //再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000l;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {

        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
