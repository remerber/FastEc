package example.fastec.com.wang.latte.ec.main.person.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.ec.R;

/**
 * Created by HP on 2017/9/1.
 */

public class NameDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
