package xyz.intensedev.iutil.commands;

import com.mysql.jdbc.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import xyz.intensedev.iutil.Main;
import xyz.intensedev.iutil.Utils;

public class SetSlotsCommand implements CommandExecutor, Listener {

    private int max;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender.hasPermission("iutils.setslots"))) {
            sender.sendMessage(Utils.translate("&cYou do not have permission."));
        }

        if (args.length != 1) {
            for(String message : Main.getInstance().getConfig().getStringList("SetSlots.HelpMessage")) {
                sender.sendMessage(Utils.translate(message));
            }
        } else if (!(Utils.isInt(args[0]))) {
            sender.sendMessage(Utils.translate(Main.getInstance().getConfig().getString("SetSlots.NoNumberMessage")));
        } else {
            try {
                Utils.setMaxPlayers(Integer.parseInt(args[0]));
                max = Integer.parseInt(args[0]);
                for(String message : Main.getInstance().getConfig().getStringList("SetSlots.SuccessfulSet")) {
                    sender.sendMessage(Utils.translate(message).replace("%amount%", args[0]));
                }
            } catch (ReflectiveOperationException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "An Error has occurred while setting max players. Please contact" + ChatColor.AQUA + "IntenseDevelopment");
                e.printStackTrace();
            }
        }
        return false;
    }

    @EventHandler
    public void onMOTD(ServerListPingEvent e) {
        e.setMaxPlayers(max);
    }
}
