package example.fastec.com.wang.latte.ui.camera;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;

import example.fastec.com.wang.latte.R;
import example.fastec.com.wang.latte.delegates.PermissionCheckerDelegate;
import example.fastec.com.wang.latte.util.file.FileUtil;

/**
 * Created by HP on 2017/9/1.
 * 照片处理类
 */

public class CameraHandler implements View.OnClickListener {

    private final AlertDialog DIALOG;
    private final PermissionCheckerDelegate DELEGATE;

    public CameraHandler(  PermissionCheckerDelegate delegate) {
        DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
        this.DELEGATE = delegate;
    }

    final void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);

        }
    }

    private void takePhoto() {
        final String currentPhotoName = getPhotoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File temFile = new File(FileUtil.CAMERA_PHOTO_DIR, currentPhotoName);

        //兼容7.0及以上的写法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, temFile.getPath());
            final Uri uri = DELEGATE.getContext().getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            //需要将uri路径转换成实际路径
            final File realFile =
                    FileUtils.getFileByPath(FileUtil.getRealFilePath(DELEGATE.getContext(), uri));
            final Uri realUri = Uri.fromFile(realFile);
            CameraImageBean.getInstance().setPath(realUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            final Uri fileUri = Uri.fromFile(temFile);
            CameraImageBean.getInstance().setPath(fileUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        }
        DELEGATE.startActivityForResult(intent, RequestCodes.TAKE_PHOTO);
    }

    private void pickPhoto() {

        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        DELEGATE.startActivityForResult
                (Intent.createChooser(intent, "选择获取图片的方式"), RequestCodes.PICK_PHOTO);

    }

    private String getPhotoName() {
        return FileUtil.getFileNameByTime("IMG", "jpg");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photodialog_btn_cancel) {
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_take) {
            takePhoto();
            DIALOG.cancel();

        } else if (id == R.id.photodialog_btn_native) {
            pickPhoto();
            DIALOG.cancel();
        }
    }
}
