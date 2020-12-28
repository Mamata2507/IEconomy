package cx.rain.mc.bukkit.ieconomy;

import cx.rain.mc.bukkit.ieconomy.api.IEconomyDataProvider;
import cx.rain.mc.bukkit.ieconomy.api.IEconomyProvider;
import cx.rain.mc.bukkit.ieconomy.command.CommandBalance;
import cx.rain.mc.bukkit.ieconomy.core.Economy;
import cx.rain.mc.bukkit.ieconomy.data.MySqlProvider;
import cx.rain.mc.bukkit.ieconomy.utility.I18n;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class IEconomy extends JavaPlugin {
    private static IEconomy Instance;

    public IEconomy() {
        if (Instance != null) {
            throw new RuntimeException("Init twice!");
        }

        Instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loading...");
        I18n.loadI18nFile(Locale.SIMPLIFIED_CHINESE);

        getServer().getPluginCommand("balance").setExecutor(new CommandBalance());

        getServer().getServicesManager().register(IEconomyDataProvider.class, new MySqlProvider(),
                this, ServicePriority.High);

        getServer().getServicesManager().register(IEconomyProvider.class, Economy.create(),
                this, ServicePriority.High);
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
