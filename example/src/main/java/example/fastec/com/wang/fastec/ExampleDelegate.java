package example.fastec.com.wang.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.net.RestClient;
import example.fastec.com.wang.latte.net.callback.ISuccess;

/**
 * Created by HP on 2017/7/31.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRequest();

//        Log.e("ExampleDelegate",
//            "onBindView(ExampleDelegate.java:27)"+  Latte.getConfigurations().get());
    }

    private void testRequest() {
        RestClient.builder().url("http://imtt.dd.qq.com/16891/1FA1EBDA2BCA25BD8A395DB91DF92B83.apk?fsname=com.snda.wifilocating_4.2.12_3132.apk&csr=1bbd")
                .loader(getActivity())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //   Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .name("wifi")
                .build().download();
    }
}
