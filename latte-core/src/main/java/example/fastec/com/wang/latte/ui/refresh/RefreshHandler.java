package example.fastec.com.wang.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import example.fastec.com.wang.latte.app.Latte;
import example.fastec.com.wang.latte.net.RestClient;
import example.fastec.com.wang.latte.net.callback.ISuccess;
import example.fastec.com.wang.latte.ui.recycler.DataConverter;
import example.fastec.com.wang.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by HP on 2017/8/10.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;


    public RefreshHandler(SwipeRefreshLayout refresh_layout,
                          RecyclerView recyclerview,
                          DataConverter converter,
                          PagingBean bean
    ) {
        this.REFRESH_LAYOUT = refresh_layout;
        this.BEAN = bean;
        this.RECYCLERVIEW = recyclerview;
        this.CONVERTER = converter;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refresh_layout,
                                        RecyclerView recyclerview,
                                        DataConverter converter) {
        return new RefreshHandler(refresh_layout, recyclerview, converter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //加载网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);

    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder().url(url).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final JSONObject object = JSON.parseObject(response);
                BEAN.setTotal(object.getInteger("total"))
                        .setPageSize(object.getInteger("page_size"));
                //设置Adapter
                mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJson(response));
                mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                RECYCLERVIEW.setAdapter(mAdapter);
                BEAN.addIndex();
            }
        }).build().get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
