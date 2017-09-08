package example.fastec.com.wang.latte.ec.main.person;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.ec.main.person.list.ListBean;

/**
 * Created by HP on 2017/9/1.
 */

public class PersonalClickListener extends SimpleClickListener {
    private final LatteDelegate delegate;

    public PersonalClickListener(LatteDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) adapter.getData().get(position);
        int id = bean.getId();
        switch (id) {

            case 1:
                delegate.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;

            case 2:
                delegate.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            default:

                break;
        }

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
