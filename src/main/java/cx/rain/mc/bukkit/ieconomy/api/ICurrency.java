package cx.rain.mc.bukkit.ieconomy.api;

import java.util.Map;

public interface ICurrency {
    String getName();

    String getAbbreviation();

    String getSingular();

    String getPlural();

    Map<ICurrency, Double> getExchangingRates();
}
