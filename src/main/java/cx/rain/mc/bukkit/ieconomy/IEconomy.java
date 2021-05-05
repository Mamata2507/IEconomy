package cx.rain.mc.bukkit.ieconomy;

import cx.rain.mc.bukkit.ieconomy.command.CommandBalance;
import cx.rain.mc.bukkit.ieconomy.utility.I18n;
import cx.rain.mc.qore.api.QoreApi;
import cx.rain.mc.qore.api.config.YamlConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class IEconomy extends JavaPlugin {
    private static IEconomy Instance;

    private final QoreApi qore;

    private YamlConfig config;

    public IEconomy() {
        super();

        if (Instance != null) {
            throw new RuntimeException("Init twice!");
        }

        Instance = this;

        qore = new QoreApi(this);
        config = qore.getConfig().createConfig("config", "");

        I18n.loadI18n(config.getString("language"));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loading...");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("Goodbye!");
    }

    public static IEconomy getInstance() {
        return Instance;
    }

    public QoreApi getQore() {
        return qore;
    }
}
