package xyz.intensedev.iutil.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.intensedev.iutil.Main;
import xyz.intensedev.iutil.Utils;

import javax.rmi.CORBA.Util;

public class RenameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        StringBuilder builder = new StringBuilder();
        int startArg = 0;
        int endArg = args.length;
        for (int i = startArg; i < endArg; i++) {
            builder.append(args[i]).append(args.length > (i + 1) ? " " : "");
        }
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You must be a player to use this command.");
        } else {
            Player player = (Player) sender;
            if (player.getItemInHand().getType() == Material.AIR) {
                player.sendMessage(Utils.translate("&cThere is no item in your hand."));
            } else {
                player.sendMessage(Utils.translate(Main.getInstance().getConfig().getString("Rename.SuccessfulRename")
                        .replace("%item%", player.getItemInHand().getType().toString())
                        .replace("%new-name%", builder.toString())));
                ItemStack stack = player.getItemInHand();
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(Utils.translate(builder.toString()));
                stack.setItemMeta(meta);
            }
        }

        return false;
    }
}
