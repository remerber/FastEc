package example.fastec.com.wang.latte.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import example.fastec.com.wang.latte.ui.recycler.DataConverter;
import example.fastec.com.wang.latte.ui.recycler.ItemType;
import example.fastec.com.wang.latte.ui.recycler.MultipleFields;
import example.fastec.com.wang.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by HP on 2017/8/14.
 */

public class VerticalListDataConverter extends DataConverter {


    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data").getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false).build();
            dataList.add(entity);
            //设置选中第一个
            dataList.get(0).setField(MultipleFields.TAG, true);

        }

        return dataList;
    }
}
