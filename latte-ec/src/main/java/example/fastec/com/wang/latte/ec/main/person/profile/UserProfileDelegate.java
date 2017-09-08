package example.fastec.com.wang.latte.ec.main.person.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.ec.R;
import example.fastec.com.wang.latte.ec.R2;
import example.fastec.com.wang.latte.ec.main.person.list.ListAdapter;
import example.fastec.com.wang.latte.ec.main.person.list.ListBean;
import example.fastec.com.wang.latte.ec.main.person.list.ListItemType;
import example.fastec.com.wang.latte.ec.main.person.setting.NameDelegate;

/**
 * Created by HP on 2017/8/31.
 */

public class UserProfileDelegate extends LatteDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1).setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();
        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2).setText("姓名")
                .setDelegate(new NameDelegate())
                .setValue("未设置性别")
                .build();
        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3).setText("性别")
                .setValue("未设置性别")
                .build();
        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4).setText("生日")
                .setValue("未设置生日")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));

    }
}
