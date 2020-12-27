package cx.rain.mc.bukkit.ieconomy.core.currency;

import cx.rain.mc.bukkit.ieconomy.api.ICurrency;

import java.util.Map;

public class Currency implements ICurrency {
    protected String name;
    protected String abbreviation;
    protected String singular;
    protected String plural;
    protected Map<ICurrency, Double> exchangingRates;

    public Currency(String nameIn, String abbreviationIn, String singularIn, String pluralIn,
                    Map<ICurrency, Double> exchangingRatesIn) {
        name = nameIn;
        abbreviation = abbreviationIn;
        singular = singularIn;
        plural = pluralIn;
        exchangingRates = exchangingRatesIn;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String getSingular() {
        return singular;
    }

    @Override
    public String getPlural() {
        return plural;
    }

    @Override
    public Map<ICurrency, Double> getExchangingRates() {
        return exchangingRates;
    }
}
