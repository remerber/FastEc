package example.fastec.com.wang.latte.ec.main.person.address;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.util.List;

import example.fastec.com.wang.latte.ec.R;
import example.fastec.com.wang.latte.ui.recycler.MultipleFields;
import example.fastec.com.wang.latte.ui.recycler.MultipleItemEntity;
import example.fastec.com.wang.latte.ui.recycler.MultipleRecyclerAdapter;
import example.fastec.com.wang.latte.ui.recycler.MultipleViewHolder;

/**
 * Created by HP on 2017/9/5.
 */

public class AddressAdapter extends MultipleRecyclerAdapter{



    protected AddressAdapter(@Nullable List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case  AddressItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(AddressItemFields.PHONE);
                final String address = entity.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getField(MultipleFields.TAG);
                final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);


                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //请求接口删除
//                        RestClient.builder()
//                                .url("address.php")
//                                .params("id", id)
//                                .success(new ISuccess() {
//                                    @Override
//                                    public void onSuccess(String response) {
                                        remove(holder.getLayoutPosition());
//                                    }
//                                })
//                                .build()
//                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);



                break;
            default:
        
                break;
        }

        
        
    }
}
