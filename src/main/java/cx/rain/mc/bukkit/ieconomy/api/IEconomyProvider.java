package cx.rain.mc.bukkit.ieconomy.api;

import org.bukkit.OfflinePlayer;

public interface IEconomyProvider {
    PlayerEconomy getPlayer(OfflinePlayer player);

    BankAccount getBankAccounts(OfflinePlayer player);
}
