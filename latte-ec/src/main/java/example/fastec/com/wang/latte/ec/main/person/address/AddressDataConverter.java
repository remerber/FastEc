package example.fastec.com.wang.latte.ec.main.person.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import example.fastec.com.wang.latte.ui.recycler.DataConverter;
import example.fastec.com.wang.latte.ui.recycler.MultipleFields;
import example.fastec.com.wang.latte.ui.recycler.MultipleItemEntity;

/**
 * Created by HP on 2017/9/5.
 */

public class AddressDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {


        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.TAG, isDefault)
                    .setField(AddressItemFields.PHONE, phone)
                    .setField(AddressItemFields.ADDRESS, address)
                    .build();

            ENTITIES.add(entity);

        }

        return ENTITIES;
    }
}
