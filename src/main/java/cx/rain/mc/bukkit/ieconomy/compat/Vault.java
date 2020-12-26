package cx.rain.mc.bukkit.ieconomy.compat;

import cx.rain.mc.bukkit.ieconomy.IEconomy;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Logger;

public class Vault {
    private static Economy economy;
    private static Permission permission;
    private static Chat chat;

    private static Logger log = IEconomy.getInstance().getLogger();

    public Vault() {
        log.info("Initialing Vault.");

        if (setupEconomy() && setupPermission() && setupChat()) {
            log.info("Vault initialized.");
        } else {
            throw new RuntimeException("Setup Vault failed!");
        }
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> serviceProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (serviceProvider != null) {
            economy = serviceProvider.getProvider();
        }
        return economy != null;
    }

    private boolean setupPermission() {
        RegisteredServiceProvider<Permission> serviceProvider = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        if (serviceProvider != null) {
            permission = serviceProvider.getProvider();
        }
        return permission != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> serviceProvider = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        if (serviceProvider != null) {
            chat = serviceProvider.getProvider();
        }
        return chat != null;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static Permission getPermission() {
        return permission;
    }

    public static Chat getChat() {
        return chat;
    }
}
