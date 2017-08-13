package example.fastec.com.wang.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by HP on 2017/8/5.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener listener) {
        this.mITimerListener = listener;
    }


    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
