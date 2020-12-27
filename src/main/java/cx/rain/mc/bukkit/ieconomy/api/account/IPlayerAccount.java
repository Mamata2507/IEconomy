package cx.rain.mc.bukkit.ieconomy.api.account;

import org.bukkit.OfflinePlayer;

public interface IPlayerAccount extends IAccount {
    OfflinePlayer getPlayer();
}
