package example.fastec.com.wang.latte.util.callback;

import android.support.annotation.Nullable;

/**
 * Created by HP on 2017/9/1.
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
