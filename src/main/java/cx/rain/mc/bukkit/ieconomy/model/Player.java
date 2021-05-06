package cx.rain.mc.bukkit.ieconomy.model;

import cx.rain.mc.bukkit.ieconomy.data.IEconomyDataBase;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;

@DbName(IEconomyDataBase.CONNECTION_NAME)
public class Player extends Model {
}
