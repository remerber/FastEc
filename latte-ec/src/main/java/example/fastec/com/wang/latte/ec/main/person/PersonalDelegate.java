package example.fastec.com.wang.latte.ec.main.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import example.fastec.com.wang.latte.delegates.bottom.BottomItemDelegate;
import example.fastec.com.wang.latte.ec.R;
import example.fastec.com.wang.latte.ec.R2;
import example.fastec.com.wang.latte.ec.main.person.address.AddressDelegate;
import example.fastec.com.wang.latte.ec.main.person.list.ListAdapter;
import example.fastec.com.wang.latte.ec.main.person.list.ListBean;
import example.fastec.com.wang.latte.ec.main.person.list.ListItemType;
import example.fastec.com.wang.latte.ec.main.person.order.OrderListDelegate;
import example.fastec.com.wang.latte.ec.main.person.profile.UserProfileDelegate;
import example.fastec.com.wang.latte.ec.main.person.setting.SettingsDelegate;

/**
 * Created by HP on 2017/8/31.
 */

public class PersonalDelegate extends BottomItemDelegate {


    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    private Bundle mArgs = null;
    public static final String ORDER_TYPE = "ORDER_TYPE";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL).setId(1)
                .setText("收货地址")
                .setDelegate(new AddressDelegate())
                .build();
        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL).setId(2)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));


    }


    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();

    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);

    }
}
