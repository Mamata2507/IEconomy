package cx.rain.mc.bukkit.ieconomy.core.bank;

import cx.rain.mc.bukkit.ieconomy.api.IBank;
import cx.rain.mc.bukkit.ieconomy.api.ICurrency;
import cx.rain.mc.bukkit.ieconomy.api.account.IAccount;
import cx.rain.mc.bukkit.ieconomy.api.account.IPlayerAccount;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class Bank implements IBank {
    protected String name;
    protected List<ICurrency> currencies;
    protected List<IAccount> accounts;

    public Bank(String nameIn, List<ICurrency> currencyList, List<IAccount> accountList) {
        name = nameIn;
        currencies = currencyList;
        accounts = accountList;
    }

    @Override
    public ICurrency[] getSupportedCurrencies() {
        return currencies.toArray(new ICurrency[0]);
    }

    @Override
    public IPlayerAccount[] getPlayerAccounts(OfflinePlayer player) {
        return accounts.stream().filter(account -> account instanceof IPlayerAccount
                && ((IPlayerAccount) account).getPlayer().getUniqueId().equals(player.getUniqueId()))
                .toArray(IPlayerAccount[]::new);
    }

    @Override
    public IPlayerAccount getPlayerAccount(OfflinePlayer player, String name) {
        return (IPlayerAccount) accounts.stream().filter(account -> account instanceof IPlayerAccount
                && ((IPlayerAccount) account).getPlayer().getUniqueId().equals(player.getUniqueId())
                && account.getName().equals(name))
                .findFirst().get();
    }

    @Override
    public boolean supportsCurrency(ICurrency currency) {
        return currencies.contains(currency);
    }

    @Override
    public void addAccount(IAccount account) {
        accounts.add(account);
    }

    @Override
    public String getName() {
        return name;
    }
}
