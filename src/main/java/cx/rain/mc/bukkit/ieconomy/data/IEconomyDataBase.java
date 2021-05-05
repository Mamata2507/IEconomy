package cx.rain.mc.bukkit.ieconomy.data;

import cx.rain.mc.bukkit.ieconomy.IEconomy;
import cx.rain.mc.qore.api.config.YamlConfig;
import org.javalite.activejdbc.DB;

public class IEconomyDataBase {
    private YamlConfig config;
    private DB db;

    public IEconomyDataBase() {
        config = IEconomy.getInstance().getPluginConfig();

        String dbType = config.getString("database.type", "mysql");
        String connectionString = "jdbc:";
        String driver = "";
        if (dbType.equalsIgnoreCase("mysql")) {
            driver = "com.mysql.cj.jdbc.Driver";
            connectionString += "mysql://" +
                    config.getString("database.host", "localhost") + ":" +
                    config.getInt("database.port", 3306) + "/" +
                    config.getString("database.database");
        } else if (dbType.equalsIgnoreCase("sqlite")) {
            driver = "org.sqlite.JDBC";
            connectionString += "sqlite:ieconomy.db";
        }

        db = IEconomy.getInstance().getQore().getDatabase().open("ieconomy", driver, connectionString,
                config.getString("database.username", "root"),
                config.getString("database.password", "123456"));
    }

    public void close() {
        db.close();
    }
}
