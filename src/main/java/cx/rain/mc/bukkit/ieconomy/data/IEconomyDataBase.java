package cx.rain.mc.bukkit.ieconomy.data;

import cx.rain.mc.bukkit.ieconomy.IEconomy;
import cx.rain.mc.qore.api.config.YamlConfig;
import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.Registry;

public class IEconomyDataBase {
    public static final String CONNECTION_NAME = "ieconomy";

    private YamlConfig config;
    private DB db;

    private String dbType;
    private String dbName;

    public IEconomyDataBase() {
        config = IEconomy.getInstance().getPluginConfig();

        dbType = config.getString("database.type", "mysql");

        String connectionString = "jdbc:";
        String driver = "";
        if (dbType.equalsIgnoreCase("mysql")) {
            dbName = config.getString("database.database", "ieconomy");

            driver = "com.mysql.cj.jdbc.Driver";
            connectionString += "mysql://" +
                    config.getString("database.host", "localhost") + ":" +
                    config.getInt("database.port", 3306) + "/" +
                    dbName;
        } else if (dbType.equalsIgnoreCase("sqlite")) {
            driver = "org.sqlite.JDBC";
            connectionString += "sqlite:" + IEconomy.getInstance().getDataFolder().getPath() + "/ieconomy.db";
        }

        Registry.INSTANCE.setModelFile("activejdbc_models.properties");

        db = new DB(CONNECTION_NAME).open(driver, connectionString,
                config.getString("database.username", "root"),
                config.getString("database.password", "123456"));

        init();
    }

    private void init() {
        
    }

    public DB getDb() {
        return db;
    }

    public void close() {
        db.close();
    }
}
