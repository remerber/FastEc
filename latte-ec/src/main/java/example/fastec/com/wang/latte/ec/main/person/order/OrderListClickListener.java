package example.fastec.com.wang.latte.ec.main.person.order;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by HP on 2017/9/8.
 */

public class OrderListClickListener extends SimpleClickListener {

    private final OrderListDelegate DELEGATE;

    public OrderListClickListener(OrderListDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DELEGATE.getSupportDelegate().start(new OrderCommentDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}

