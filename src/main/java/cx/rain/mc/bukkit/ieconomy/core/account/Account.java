package cx.rain.mc.bukkit.ieconomy.core.account;

import cx.rain.mc.bukkit.ieconomy.api.IBank;
import cx.rain.mc.bukkit.ieconomy.api.ICurrency;
import cx.rain.mc.bukkit.ieconomy.api.account.IAccount;

import java.util.Map;

public abstract class Account implements IAccount {
    protected IBank bank;
    protected String accountName;
    protected Map<ICurrency, Double> deposit;

    public Account(IBank bankIn, String accountNameIn, Map<ICurrency, Double> depositIn) {
        bank = bankIn;
        accountName = accountNameIn;
        deposit = depositIn;
    }

    @Override
    public String getName() {
        return accountName;
    }

    @Override
    public IBank getBank() {
        return bank;
    }

    @Override
    public boolean supportsCurrency(ICurrency currency) {
        return getBank().supportsCurrency(currency);
    }

    @Override
    public boolean hasCurrency(ICurrency currency) {
        if (!supportsCurrency(currency)) {
            return false;
        }
        return deposit.containsKey(currency);
    }

    @Override
    public double getBalance(ICurrency currency) {
        if (hasCurrency(currency)) {
            return deposit.get(currency);
        }

        return 0;
    }

    @Override
    public boolean setBalance(ICurrency currency, double balance) {
        if (!supportsCurrency(currency)) {
            return false;
        }

        deposit.put(currency, balance);
        return true;
    }

    @Override
    public boolean deposit(ICurrency currency, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Deposit negative!");
        }

        double prevDeposit = deposit.get(currency);
        deposit.put(currency, prevDeposit + balance);

        return prevDeposit <= deposit.get(currency);
    }

    @Override
    public boolean withdraw(ICurrency currency, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Withdraw negative!");
        }

        if (!supportsCurrency(currency)) {
            return false;
        }

        if (hasCurrency(currency)) {
            return false;
        }

        double prevDeposit = deposit.get(currency);
        deposit.put(currency, prevDeposit - balance);

        return prevDeposit >= deposit.get(currency);
    }
}
