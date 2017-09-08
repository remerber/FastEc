package example.fastec.com.wang.latte.util.callback;

import java.util.WeakHashMap;

/**
 * Created by HP on 2017/9/1.
 */

public class CallbackManager {

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    public CallbackManager addCallback(Object key, IGlobalCallback callback) {
        CALLBACKS.put(key, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object key) {
        return CALLBACKS.get(key);
    }

}
