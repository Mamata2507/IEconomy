package cx.rain.mc.bukkit.ieconomy;

import org.bukkit.plugin.java.JavaPlugin;

public final class IEconomy extends JavaPlugin {
    private static IEconomy Instance;

    public IEconomy() {
        if (Instance != null) {
            throw new RuntimeException("Init twice!");
        }

        Instance = this;
    }

    public static IEconomy getInstance() {
        return Instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic


        getLogger().info("IEconomy is loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic


        getLogger().info("Goodbye!");
    }
}
