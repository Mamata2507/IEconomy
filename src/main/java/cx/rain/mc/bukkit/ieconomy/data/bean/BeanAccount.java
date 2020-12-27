package cx.rain.mc.bukkit.ieconomy.data.bean;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BeanAccount {
    public String Name;

    public UUID Owner;

    public List<UUID> Member;

    public Map<String, Double> Deposit;
}
