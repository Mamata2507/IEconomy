package cx.rain.mc.bukkit.ieconomy.api;

public interface IMoneyProvider {
    String getName();

    double getBalance();

    void setBalance(double amount);

    void deposit(double amount);

    boolean tryWithdraw(double amount);

    void withdraw(double amount);
}
