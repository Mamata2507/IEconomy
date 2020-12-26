package cx.rain.mc.bukkit.ieconomy.api;

import org.bukkit.OfflinePlayer;

public abstract class PlayerEconomy {
    private OfflinePlayer player;
    private IMoneyProvider provider;

    public PlayerEconomy(OfflinePlayer playerIn, IMoneyProvider providerIn) {
        player = playerIn;
        provider = providerIn;
    }
}
