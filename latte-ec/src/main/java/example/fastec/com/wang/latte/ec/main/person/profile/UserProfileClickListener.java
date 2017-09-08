package example.fastec.com.wang.latte.ec.main.person.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import example.fastec.com.wang.latte.delegates.LatteDelegate;
import example.fastec.com.wang.latte.ec.R;
import example.fastec.com.wang.latte.ec.main.person.list.ListBean;
import example.fastec.com.wang.latte.ui.date.DateDialogUtil;
import example.fastec.com.wang.latte.util.callback.CallbackManager;
import example.fastec.com.wang.latte.util.callback.CallbackType;
import example.fastec.com.wang.latte.util.callback.IGlobalCallback;

/**
 * Created by HP on 2017/9/1.
 */

public class UserProfileClickListener extends SimpleClickListener {


    private final UserProfileDelegate DELEGATE;
    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate delegate) {
        DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        //1.取得图片的路径
                        //2.上传服务器
                        //3.更新数据
                        //4.如果有数据库，更新数据
                        //5.如果没有，每次进来都请求接口，获取信息
                        final ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
                        Glide.with(DELEGATE)
                                .load(args)
                                .into(avatar);
                    }


                });
                DELEGATE.startCameraWithCheck();

                break;

            case 2:
                final LatteDelegate delegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(delegate);
                break;
            case 3:

                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textview = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textview.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;

            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());

                break;
            default:

                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
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
