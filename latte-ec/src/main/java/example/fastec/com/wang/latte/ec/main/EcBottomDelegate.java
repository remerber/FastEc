package example.fastec.com.wang.latte.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import example.fastec.com.wang.latte.delegates.bottom.BaseBottomDelegate;
import example.fastec.com.wang.latte.delegates.bottom.BottomItemDelegate;
import example.fastec.com.wang.latte.delegates.bottom.BottomTabBean;
import example.fastec.com.wang.latte.delegates.bottom.ItemBuilder;
import example.fastec.com.wang.latte.ec.main.cart.ShopCartDelegate;
import example.fastec.com.wang.latte.ec.main.discover.DiscoverDelegate;
import example.fastec.com.wang.latte.ec.main.index.IndexDelegate;
import example.fastec.com.wang.latte.ec.main.person.PersonalDelegate;
import example.fastec.com.wang.latte.ec.main.sort.SortDelegate;

/**
 * Created by HP on 2017/8/8.
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();


    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
