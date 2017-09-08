package example.fastec.com.wang.latte.ec.main.person.order;

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
import example.fastec.com.wang.latte.ec.main.person.PersonalDelegate;
import example.fastec.com.wang.latte.net.RestClient;
import example.fastec.com.wang.latte.net.callback.ISuccess;
import example.fastec.com.wang.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by HP on 2017/8/31.
 */

public class OrderListDelegate extends LatteDelegate {


    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView;


    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().loader(getContext())
                .url("http://192.168.210.108:8081/data/order_list.json").success(new ISuccess() {
            @Override
            public void onSuccess(String response) {

                final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(manager);
                final List<MultipleItemEntity> data = new OrderListDataConverter().setJson(response)
                        .convert();
                final OrderListAdapter adapter = new OrderListAdapter(data);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));

            }
        }).build()
         .get();
    }
}
