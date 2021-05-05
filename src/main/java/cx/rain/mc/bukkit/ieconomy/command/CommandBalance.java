package cx.rain.mc.bukkit.ieconomy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBalance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            //sender.sendMessage(I18n.format(I18n.MESSAGES.NotPlayer));
        }

        return true;
    }
}
