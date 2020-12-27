package cx.rain.mc.bukkit.ieconomy.api.account;

import cx.rain.mc.bukkit.ieconomy.api.IBank;
import cx.rain.mc.bukkit.ieconomy.api.ICurrency;

public interface IAccount {
    String getName();

    IBank getBank();

    boolean supportsCurrency(ICurrency currency);

    boolean hasCurrency(ICurrency currency);

    double getBalance(ICurrency currency);

    boolean setBalance(ICurrency currency, double balance);

    boolean deposit(ICurrency currency, double balance);

    boolean withdraw(ICurrency currency, double balance);
}
