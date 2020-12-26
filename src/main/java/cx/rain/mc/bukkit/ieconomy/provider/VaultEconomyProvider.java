package cx.rain.mc.bukkit.ieconomy.provider;

import cx.rain.mc.bukkit.ieconomy.api.IPlayerMoneyProvider;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;

public class VaultEconomyProvider implements IPlayerMoneyProvider {
    private OfflinePlayer player;
    private Economy economy;

    public VaultEconomyProvider(OfflinePlayer playerIn, Economy economyIn) {
        player = playerIn;
        economy = economyIn;
    }


    @Override
    public double getBalance(OfflinePlayer player, String currency) {
        return economy.getBalance(player);
    }

    @Override
    public void setBalance(OfflinePlayer player, String currency, double amount) {

    }

    @Override
    public void deposit(OfflinePlayer player, String currency, double amount) {

    }

    @Override
    public boolean tryWithdraw(OfflinePlayer player, String currency, double amount) {
        return false;
    }

    @Override
    public void withdraw(OfflinePlayer player, String currency, double amount) {

    }
}
