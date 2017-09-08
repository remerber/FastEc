package example.fastec.com.wang.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;
import example.fastec.com.wang.latte.activities.ProxyActivity;
import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.ec.laucher.LauncherDelegate;
import example.fastec.com.wang.latte.ec.main.EcBottomDelegate;
import example.fastec.com.wang.latte.ec.sign.ISignListener;
import example.fastec.com.wang.latte.ui.laucher.ILauncherListener;
import example.fastec.com.wang.latte.ui.laucher.OnLauncherFinishTag;
import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements ILauncherListener,
        ISignListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登陆了", Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                //  start(new EcBottomDelegate());
                break;

            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没有登陆", Toast.LENGTH_SHORT).show();
                // startWithPop(new SignInDelegate());
                startWithPop(new EcBottomDelegate());


                break;

            default:

                break;
        }
    }
}
