package cx.rain.mc.bukkit.ieconomy;

import cx.rain.mc.bukkit.ieconomy.compat.Vault;
import org.bukkit.plugin.java.JavaPlugin;

public final class IEconomy extends JavaPlugin {
    private static IEconomy Instance;

    private Vault vault;

    public IEconomy() {
        if (Instance != null) {
            throw new RuntimeException("Init twice!");
        }

        Instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            vault = new Vault();
        }

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

    public Vault getVault() {
        return vault;
    }
}
