package example.fastec.com.wang.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.ec.R;
import example.fastec.com.wang.latte.ec.R2;
import example.fastec.com.wang.latte.net.RestClient;
import example.fastec.com.wang.latte.net.callback.ISuccess;

/**
 * Created by HP on 2017/8/14.
 */

public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContent = -1;
    private List<SectionBean> mData = null;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContent = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        RestClient.builder().url("http://192.168.209.120:8081/data/sort_content_data_" + mContent + ".json").success(new ISuccess() {
            @Override
            public void onSuccess(String response) {

                mData = new SectionDataConverter().convert(response);
                final SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,
                        R.layout.item_section_header, mData);
                mRecyclerView.setAdapter(sectionAdapter);

            }
        }).build().get();

    }
}
