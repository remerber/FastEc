package example.fastec.com.wang.latte.ec.main.sort.list;

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
import example.fastec.com.wang.latte.ec.main.sort.SortDelegate;
import example.fastec.com.wang.latte.net.RestClient;
import example.fastec.com.wang.latte.net.callback.ISuccess;
import example.fastec.com.wang.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by HP on 2017/8/14.
 */

public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().url("http://192.168.209.120:8081/data/sort_list_data.json").loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final List<MultipleItemEntity> data =
                        new VerticalListDataConverter().setJson(response).convert();
                final SortDelegate delegate = getParentDelegate();
                final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                mRecyclerView.setAdapter(adapter);
            }
        }).build().get();
    }
}
