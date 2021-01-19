//package xyz.intensedev.iutil.commands;
//
//import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import xyz.intensedev.iutil.Main;
//import xyz.intensedev.iutil.Utils;
//
//public class LoreCommand implements CommandExecutor {
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
//
//        StringBuilder builder = new StringBuilder();
//        int startArg = 1;
//        int endArg = args.length;
//        for (int i = startArg; i < endArg; i++) {
//            builder.append(args[i]).append(args.length > (i + 1) ? " " : "");
//        }
//        if (!(sender instanceof Player)) {
//            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You must be a player to use this command.");
//        }
//        Player player = (Player) sender;
//        if (player.getItemInHand().getType() == Material.AIR) {
//            player.sendMessage(Utils.translate("&cThere is no item in your hand."));
//        } else if (args.length < 1) {
//                for(String message : Main.getInstance().getConfig().getStringList("Lore.HelpMessage")) {
//                    sender.sendMessage(Utils.translate(message));
//                }
//        } else if (args[0].equalsIgnoreCase("add")) {
//            Utils.addLore(player.getItemInHand(), Utils.translate(builder.toString()), true);
//            sender.sendMessage(Utils.translate(Main.getInstance().getConfig().getString("Rename.SuccessfullyAdded")));
//        } else if (args[0].equalsIgnoreCase("remove")) {
//            if (!(Utils.isInt(args[1]))) {
//                sender.sendMessage(Utils.translate(Main.getInstance().getConfig().getString("Rename.NoNumberMessage")));
//            }
//            Utils.removeLore(player.getItemInHand(), Integer.parseInt(args[1]));
//            sender.sendMessage(Utils.translate(Main.getInstance().getConfig().getString("Rename.SuccessfullyRemoved")));
//        }
//
//        return false;
//    }
//}
//////////////////////////////////////////////////////
// #
// # Working in progress
// #
//////////////////////////////////////////////////////
