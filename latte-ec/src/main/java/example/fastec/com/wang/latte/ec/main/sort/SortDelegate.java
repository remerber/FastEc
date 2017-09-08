package example.fastec.com.wang.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import example.fastec.com.wang.latte.delegates.bottom.BottomItemDelegate;
import example.fastec.com.wang.latte.ec.R;
import example.fastec.com.wang.latte.ec.main.sort.content.ContentDelegate;
import example.fastec.com.wang.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by HP on 2017/8/8.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右边第一个分类显示，默认显示第一个
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
