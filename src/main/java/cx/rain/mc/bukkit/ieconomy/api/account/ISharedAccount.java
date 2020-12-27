package cx.rain.mc.bukkit.ieconomy.api.account;

import org.bukkit.OfflinePlayer;

public interface ISharedAccount extends IAccount {
    OfflinePlayer[] getPlayers();

    boolean isOwner(OfflinePlayer player);

    boolean isMember(OfflinePlayer player);

    boolean addPlayer(OfflinePlayer player);

    boolean removePlayer(OfflinePlayer player);

    boolean transferTo(OfflinePlayer player);
}
