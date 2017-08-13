package example.fastec.com.wang.latte.delegates;

/**
 * Created by HP on 2017/7/31.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {


    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T   getParentDelegate() {
        return (T) getParentFragment();
    }
}
