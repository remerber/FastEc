package example.fastec.com.wang.latte.ec.main.person.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.ec.R;
import example.fastec.com.wang.latte.ec.R2;
import example.fastec.com.wang.latte.net.RestClient;
import example.fastec.com.wang.latte.net.callback.ISuccess;
import example.fastec.com.wang.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by HP on 2017/9/5.
 */

public class AddressDelegate extends LatteDelegate implements ISuccess {


    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView;


    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder().url("http://192.168.210.107:8081/data/address.json")
               .success(this).build()
                .get();

    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJson(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
