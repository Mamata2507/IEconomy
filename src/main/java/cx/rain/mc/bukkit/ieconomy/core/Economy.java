package cx.rain.mc.bukkit.ieconomy.core;

import cx.rain.mc.bukkit.ieconomy.IEconomy;
import cx.rain.mc.bukkit.ieconomy.api.IBank;
import cx.rain.mc.bukkit.ieconomy.api.IEconomyProvider;
import cx.rain.mc.bukkit.ieconomy.api.IEconomyDataProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Economy implements IEconomyProvider {
    private static Economy Instance;

    private Logger log;
    private IEconomyDataProvider data;
    private List<IBank> banks;

    protected Economy() {
        Instance = this;

        log = IEconomy.getInstance().getLogger();
        log.info("Initializing economy API.");

        RegisteredServiceProvider<IEconomyDataProvider> dataService = Bukkit.getServer().getServicesManager().getRegistration(IEconomyDataProvider.class);
        assert dataService != null;
        data = dataService.getProvider();
        banks = Arrays.asList(data.getBanks());
    }

    public static Economy create() {
        if (Instance != null) {
            throw new RuntimeException("Init twice!");
        }

        return new Economy();
    }
}
