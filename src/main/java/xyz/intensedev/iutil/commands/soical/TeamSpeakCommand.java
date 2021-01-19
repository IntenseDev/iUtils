package xyz.intensedev.iutil.commands.soical;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.intensedev.iutil.Main;
import xyz.intensedev.iutil.Utils;

public class TeamSpeakCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        sender.sendMessage(Utils.translate(Main.getInstance().getConfig().getString("TeamSpeak.Message")));

        return false;
    }
}
