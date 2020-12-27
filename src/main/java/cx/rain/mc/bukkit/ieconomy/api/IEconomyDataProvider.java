package cx.rain.mc.bukkit.ieconomy.api;

import java.util.List;

public interface IEconomyDataProvider {
    IBank[] getBanks();

    void save(List<IBank> banks, List<ICurrency> currencies);
}
