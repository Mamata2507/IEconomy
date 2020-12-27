package cx.rain.mc.bukkit.ieconomy;

import cx.rain.mc.bukkit.ieconomy.api.IEconomyProvider;
import cx.rain.mc.bukkit.ieconomy.core.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class IEconomy extends JavaPlugin {
    private static IEconomy Instance;

    private IEconomyProvider economy;

    public IEconomy() {
        if (Instance != null) {
            throw new RuntimeException("Init twice!");
        }

        Instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getServicesManager().register(IEconomyProvider.class, Economy.create(),
                this, ServicePriority.High);

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
}
