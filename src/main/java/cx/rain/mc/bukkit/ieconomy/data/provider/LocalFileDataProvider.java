package cx.rain.mc.bukkit.ieconomy.data.provider;

import cx.rain.mc.bukkit.ieconomy.api.IBank;
import cx.rain.mc.bukkit.ieconomy.api.ICurrency;
import cx.rain.mc.bukkit.ieconomy.api.IEconomyDataProvider;
import cx.rain.mc.bukkit.ieconomy.api.account.IAccount;
import cx.rain.mc.bukkit.ieconomy.core.account.Account;
import cx.rain.mc.bukkit.ieconomy.core.account.PlayerAccount;
import cx.rain.mc.bukkit.ieconomy.core.bank.Bank;
import cx.rain.mc.bukkit.ieconomy.core.currency.Currency;
import cx.rain.mc.bukkit.ieconomy.data.bean.BeanAccount;
import cx.rain.mc.bukkit.ieconomy.data.bean.BeanBank;
import cx.rain.mc.bukkit.ieconomy.data.bean.BeanCurrency;
import cx.rain.mc.bukkit.ieconomy.data.bean.BeanMain;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class LocalFileDataProvider implements IEconomyDataProvider {
    private File folder;

    private Yaml yaml;
    private BeanMain main;
    private List<BeanBank> beanBanks;
    private List<IBank> banks = new ArrayList<>();
    private List<IAccount> accounts = new ArrayList<>();
    private Map<String, ICurrency> currencies = new HashMap<>();

    public LocalFileDataProvider(File dataFolder) {
        if (!dataFolder.exists() || !dataFolder.isDirectory()) {
            throw new IllegalArgumentException("File should be a directory!");
        }

        folder = dataFolder;

        loadMain();
        loadBanks();
        loadCurrencies();
    }

    private void loadMain() {
        yaml = new Yaml(new Constructor(BeanMain.class));
        String mainPath = folder.getPath() + "/main.yml";
        File mainFile = new File(mainPath);
        if (!mainFile.exists()) {
            try {
                FileWriter writer = new FileWriter(mainPath);
                yaml.dump(new BeanMain(), writer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        main = yaml.load(mainPath);
    }

    private void loadBanks() {
        for (String bankName : main.Banks) {
            Yaml bankYaml = new Yaml(new Constructor(BeanBank.class));
            String path = folder.getPath() + "/" + bankName + ".yml";
            File file = new File(path);
            if (!file.exists()) {
                try {
                    FileWriter writer = new FileWriter(file);
                    bankYaml.dump(new BeanMain(), writer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            BeanBank bank = bankYaml.load(path);
            beanBanks.add(bank);
        }
    }

    private void loadCurrencies() {
        for (BeanCurrency currency : main.Currencies) {
            currencies.put(currency.Name, new Currency(currency.Name, currency.Abbreviation,
                    currency.Singular, currency.Plural, null));
        }

        for (BeanCurrency currency : main.Currencies) {
            Map<ICurrency, Double> exchangingRate = new HashMap<>();
            currency.ExchangingRates.forEach((name, rate) -> {
                exchangingRate.put(currencies.get(name), rate);
            });
        }
    }

    @Override
    public IBank[] getBanks() {
        return banks.toArray(new IBank[0]);
    }

    @Override
    public void save(List<IBank> banks, List<ICurrency> currencies) {
    }

    private List<ICurrency> getCurrency() {
        return Arrays.asList(currencies.values().toArray(new ICurrency[0]));
    }

    private List<IAccount> getAccounts(IBank bank) {
        return null;
    }
}
