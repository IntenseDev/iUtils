package xyz.intensedev.iutil.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import xyz.intensedev.iutil.Main;
import xyz.intensedev.iutil.Utils;

import javax.rmi.CORBA.Util;
import java.io.IOException;

public class SetMOTDCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        StringBuilder builder = new StringBuilder();
        int startArg = 1;
        int endArg = args.length;
        for (int i = startArg; i < endArg; i++) {
            builder.append(args[i]).append(args.length > (i + 1) ? " " : "");
        }
        if (args.length <= 1) {
            for(String message : Main.getInstance().getConfig().getStringList("SetMOTD.HelpMessage")) {
                sender.sendMessage(Utils.translate(message));
            }
        } else if (!(Utils.isInt(args[0]))) {
            sender.sendMessage(Utils.translate(Main.getInstance().getConfig().getString("SetMOTD.NoNumberMessage")));
        } else if (!(args[0].contains("1") || args[0].contains("2"))) {
            for (String message : Main.getInstance().getConfig().getStringList("SetMOTD.HelpMessage")) {
                sender.sendMessage(Utils.translate(message));
            }
        } else if (args[0].contains("1")) {
            Main.getInstance().getConfig().set("SetMOTD.MOTD.1", builder.toString());
            Main.getInstance().saveDefaultConfig();
            for(String message : Main.getInstance().getConfig().getStringList("SetMOTD.SuccessfulSet")) {
                sender.sendMessage(Utils.translate(message)
                        .replace("%number%", "1")
                        .replace("%motd%", Utils.translate(builder.toString())));
            }
        } else if (args[0].contains("2")) {
            Main.getInstance().getConfig().set("SetMOTD.MOTD.2", builder.toString());
            Main.getInstance().saveDefaultConfig();
            for(String message : Main.getInstance().getConfig().getStringList("SetMOTD.SuccessfulSet")) {
                sender.sendMessage(Utils.translate(message)
                        .replace("%number%", "2")
                        .replace("%motd%", Utils.translate(builder.toString())));
            }
        }
        return false;
    }

    @EventHandler
    public void onMOTD(ServerListPingEvent e) {
        e.setMotd(Utils.translate(Main.getInstance().getConfig().getString("SetMOTD.MOTD.1") + "\n&r" + Main.getInstance().getConfig().getString("SetMOTD.MOTD.2")));
    }
}
