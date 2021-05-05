package cx.rain.mc.bukkit.ieconomy.utility;

import cx.rain.mc.bukkit.ieconomy.IEconomy;
import cx.rain.mc.qore.api.config.YamlConfig;
import org.bukkit.ChatColor;

public class I18n {
    public static final String DEFAULT_LOCALE = "zh_cn";

    private static YamlConfig messages;

    public static void loadI18n(String locale) {
        try {
            messages = IEconomy.getInstance().getQore().getConfig().createConfig(
                    locale, IEconomy.getInstance().getDataFolder() + "/messages",
                    locale, "/messages");
        } catch (Exception ex) {
            IEconomy.getInstance().getLogger().warning("Cannot load locale: " + locale + " !");
            ex.printStackTrace();
        }
    }

    public static String format(String key, Object... params) {
        if (messages == null) {
            loadI18n(DEFAULT_LOCALE);
        }

        String str = messages.getString(key);
        return ChatColor.translateAlternateColorCodes('&', String.format(str, params));
    }
}
