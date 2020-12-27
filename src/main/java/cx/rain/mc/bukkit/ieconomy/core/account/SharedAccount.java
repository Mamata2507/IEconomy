package cx.rain.mc.bukkit.ieconomy.core.account;

import cx.rain.mc.bukkit.ieconomy.api.IBank;
import cx.rain.mc.bukkit.ieconomy.api.ICurrency;
import cx.rain.mc.bukkit.ieconomy.api.account.ISharedAccount;
import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.Map;

public class SharedAccount extends Account implements ISharedAccount {
    private OfflinePlayer owner;
    private List<OfflinePlayer> member;

    public SharedAccount(OfflinePlayer ownerIn, List<OfflinePlayer> memberIn,
                         IBank bank, String accountName, Map<ICurrency, Double> deposit) {
        super(bank, accountName, deposit);
        owner = ownerIn;
        member = memberIn;
    }

    @Override
    public OfflinePlayer[] getPlayers() {
        return new OfflinePlayer[0];
    }

    @Override
    public boolean isOwner(OfflinePlayer player) {
        return owner.getUniqueId().equals(player.getUniqueId());
    }

    @Override
    public boolean isMember(OfflinePlayer player) {
        return member.stream().anyMatch(member -> member.getUniqueId() == player.getUniqueId());
    }

    @Override
    public boolean addPlayer(OfflinePlayer player) {
        if (isOwner(player) || isMember(player)) {
            return false;
        }
        member.add(player);
        return true;
    }

    @Override
    public boolean removePlayer(OfflinePlayer player) {
        if (!isMember(player)) {
            return false;
        }
        return member.remove(player);
    }

    @Override
    public boolean transferTo(OfflinePlayer player) {
        if (!isMember(player)) {
            return false;
        }
        OfflinePlayer origin = owner;
        owner = player;
        return member.remove(player) && member.add(origin);
    }
}
