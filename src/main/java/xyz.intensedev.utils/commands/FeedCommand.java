package xyz.intensedev.utils.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String lavel, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§b[iUtils] §7 Only Players can run this commands.");
        }

        Player p = (Player) sender;
        p.setFoodLevel(20);
        p.sendMessage("§b[iUtils] §7 You have been feed");
        return false;
    }
}
