package example.fastec.com.wang.latte.ui.camera;

import android.net.Uri;

/**
 * Created by HP on 2017/9/1.
 */

public class CameraImageBean {

    private Uri mPath = null;
    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance() {
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
