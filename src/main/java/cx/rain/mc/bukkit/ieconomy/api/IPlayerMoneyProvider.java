package cx.rain.mc.bukkit.ieconomy.api;

import org.bukkit.OfflinePlayer;

public interface IPlayerMoneyProvider {
    double getBalance(OfflinePlayer player, String currency);

    void setBalance(OfflinePlayer player, String currency, double amount);

    void deposit(OfflinePlayer player, String currency, double amount);

    boolean tryWithdraw(OfflinePlayer player, String currency, double amount);

    void withdraw(OfflinePlayer player, String currency, double amount);
}
