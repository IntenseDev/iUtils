package xyz.intensedev.iutil.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.intensedev.iutil.Main;
import xyz.intensedev.iutil.Utils;

public class TrashCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender.hasPermission("iutils.trash"))) {
            sender.sendMessage(Utils.translate("&cYou do not have permission."));
        }

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You must be a player to use this command.");
        } else {
            Player player = (Player) sender;
            final Inventory trash = Bukkit.getServer().createInventory(null, Main.getInstance().getConfig().getInt("Trash.Slots"), Utils.translate(Main.getInstance().getConfig().getString("Trash.Title")));
            player.openInventory(trash);
        }
        return false;
    }
}
