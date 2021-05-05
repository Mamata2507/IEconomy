package cx.rain.mc.bukkit.ieconomy;

import cx.rain.mc.bukkit.ieconomy.utility.I18n;
import cx.rain.mc.qore.api.QoreApi;
import cx.rain.mc.qore.api.config.YamlConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class IEconomy extends JavaPlugin {
    private static IEconomy INSTANCE;

    private final QoreApi qore;

    private YamlConfig config;

    public IEconomy() {
        super();

        if (INSTANCE != null) {
            throw new RuntimeException("Init twice!");
        }

        INSTANCE = this;

        qore = new QoreApi(this);
        config = qore.getConfig().createConfig("config", "");

        I18n.loadI18n(config.getString("language"));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loading...");



        try {
            Class.forName("io.izzel.arclight.common.mod.ArclightMod");
            getLogger().info("Hi Arclight, did you hear about limelight?");
        } catch (ClassNotFoundException ignored) {
            // Silence is gold.
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("Goodbye world!");
    }

    public static IEconomy getInstance() {
        return INSTANCE;
    }

    public QoreApi getQore() {
        return qore;
    }

    public YamlConfig getPluginConfig() {
        return config;
    }
}
