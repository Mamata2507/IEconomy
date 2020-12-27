package cx.rain.mc.bukkit.ieconomy.core.account;

import cx.rain.mc.bukkit.ieconomy.api.ICurrency;
import cx.rain.mc.bukkit.ieconomy.api.IBank;
import cx.rain.mc.bukkit.ieconomy.api.account.IPlayerAccount;
import org.bukkit.OfflinePlayer;

import java.util.Map;

public class PlayerAccount extends Account implements IPlayerAccount {
    protected OfflinePlayer owner;

    public PlayerAccount(OfflinePlayer player, IBank bank, String accountName, Map<ICurrency, Double> deposit) {
        super(bank, accountName, deposit);
        owner = player;
    }

    @Override
    public OfflinePlayer getPlayer() {
        return owner;
    }
}
