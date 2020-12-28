package cx.rain.mc.bukkit.ieconomy.utility;

import cx.rain.mc.bukkit.ieconomy.IEconomy;
import cx.rain.mc.bukkit.ieconomy.bean.I18nMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.logging.Logger;

public class I18n {
    public static I18nMessages MESSAGES;

    private static final Yaml yaml = new Yaml(new Constructor(I18nMessages.class));
    private static final Logger log = IEconomy.getInstance().getLogger();

    public static void loadI18nFile(Locale locale) {
        String messagesPath = IEconomy.getInstance().getDataFolder() + "/messages/" + locale.toString() + ".yml";
        File messagesFile = new File(messagesPath);
        if (!messagesFile.exists() && !getInnerMessageFile(locale)) {
            log.warning("No locale file found, releasing.");
            writeDefaultMessages(locale);
            loadI18nFile(locale);
            return;
        }

        try {
            InputStream stream = new FileInputStream(messagesFile);
            MESSAGES = yaml.load(stream);
        } catch (Exception ex) {
            ex.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(IEconomy.getInstance());
        }
    }

    public static String format(String key, Object... params) {
        return ChatColor.translateAlternateColorCodes('&', String.format(key, params));
    }

    private static boolean getInnerMessageFile(Locale locale) {
        String path = "/messages/" + locale.toString() + ".yml";
        File out = new File(IEconomy.getInstance().getDataFolder() + path);
        if (out.exists()) {
            InputStream is = I18n.class.getResourceAsStream(path);
            try {
                File parent = out.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                if (!out.exists()) {
                    out.createNewFile();
                }
                OutputStream os = new FileOutputStream(out);
                int index = 0;
                byte[] bytes = new byte[1024];
                while ((index = is.read(bytes)) != -1) {
                    os.write(bytes, 0, index);
                }
                os.flush();
                os.close();
                is.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return out.exists();
    }

    private static void writeDefaultMessages(Locale locale) {
        String path = IEconomy.getInstance().getDataFolder() + "/messages/" + locale.toString() + ".yml";
        File file = new File(path);
        try {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            byte[] bytes = yaml.dumpAsMap(new I18nMessages()).getBytes(StandardCharsets.UTF_8);
            String str = new String(bytes);
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
