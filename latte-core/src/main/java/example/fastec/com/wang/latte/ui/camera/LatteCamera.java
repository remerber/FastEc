package example.fastec.com.wang.latte.ui.camera;

import android.net.Uri;

import example.fastec.com.wang.latte.delegates.PermissionCheckerDelegate;
import example.fastec.com.wang.latte.util.file.FileUtil;

/**
 * Created by HP on 2017/9/1.
 */

public class LatteCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
