package example.fastec.com.wang.latte.ec.main.person.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import example.fastec.com.wang.latte.ui.recycler.DataConverter;
import example.fastec.com.wang.latte.ui.recycler.MultipleFields;
import example.fastec.com.wang.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by HP on 2017/8/31.
 */

public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(OrderItemFields.PRICE, price)
                    .setField(OrderItemFields.TIME, time)
                    .build();
            ENTITIES.add(entity);

        }

        return ENTITIES;
    }
}
