package cx.rain.mc.bukkit.ieconomy.api;

import cx.rain.mc.bukkit.ieconomy.api.account.IAccount;
import cx.rain.mc.bukkit.ieconomy.api.account.IPlayerAccount;
import org.bukkit.OfflinePlayer;

public interface IBank {
    ICurrency[] getSupportedCurrencies();

    IPlayerAccount[] getPlayerAccounts(OfflinePlayer player);

    IPlayerAccount getPlayerAccount(OfflinePlayer player, String name);

    boolean supportsCurrency(ICurrency currency);

    void addAccount(IAccount account);

    String getName();
}
