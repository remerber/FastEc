package example.fastec.com.wang.latte.app;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import example.fastec.com.wang.latte.delegates.web.event.Event;
import example.fastec.com.wang.latte.delegates.web.event.EventManager;

/**
 * Created by HP on 2017/7/30.
 */

public class Configurator {

    private final static HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private final static ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private final static Handler HANDLER = new Handler();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        LATTE_CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private final static Configurator INSTANCE = new Configurator();

    }

    public HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
        Utils.init(Latte.getApplicationContext());
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withJavascriptInterface(@NonNull String name) {
        LATTE_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE.name(), name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    public final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());

    }


}
