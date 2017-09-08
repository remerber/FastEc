package example.fastec.com.wang.latte.ec.main.person.setting;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.ec.main.person.list.ListBean;

/**
 * Created by HP on 2017/9/6.
 */

public class SettingsClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public SettingsClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                //这是消息推送的开关
                break;
            case 2:
                DELEGATE.getSupportDelegate().start(bean.getDelegate());
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