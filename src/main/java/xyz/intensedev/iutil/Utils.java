package xyz.intensedev.iutil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Utils {

    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatString(String string, boolean override) {
        if (!override || string.startsWith("&r"))
            return ChatColor.translateAlternateColorCodes('&', string);
        return ChatColor.translateAlternateColorCodes('&', "&r" + string);
    }

    private static List<String> multiLineLore(String lore, boolean override) {
        List<String> loreList = Arrays.asList(lore.split("\\\\n"));
        ListIterator<String> itr = loreList.listIterator();
        while (itr.hasNext())
            itr.set(Utils.formatString(itr.next(), override));
        return loreList;
    }

    public static void setLore(ItemStack itemStack, String name, boolean override) {
        String lore = Utils.formatString(name, override);
        List<String> loreList = multiLineLore(lore, override);
        ItemMeta itemStackMeta = itemStack.getItemMeta();
        itemStackMeta.setLore(loreList);
        itemStack.setItemMeta(itemStackMeta);
    }

    public static void addLore(ItemStack itemStack, String loreString, boolean override) {
        List<String> lore = itemStack.getItemMeta().getLore();
        if (lore == null) {
            setLore(itemStack, loreString, override);
        } else {
            List<String> newLore = multiLineLore(loreString, override);
            lore.addAll(newLore);
            ItemMeta itemStackMeta = itemStack.getItemMeta();
            itemStackMeta.setLore(lore);
            itemStack.setItemMeta(itemStackMeta);
        }
    }

    public static void removeLore(ItemStack item, int n) {
        ItemMeta itemStackMeta = item.getItemMeta();
        if (itemStackMeta.hasLore() && n != -1) {
            List<String> list = itemStackMeta.getLore();
            if (list.size() >= n + 1) {
                list.remove(n);
                itemStackMeta.setLore(list);
            }
        } else {
            itemStackMeta.setLore(null);
        }
        item.setItemMeta(itemStackMeta);
    }

        public static void setMaxPlayers(int maxPlayers)
            throws ReflectiveOperationException {
        String bukkitversion = Bukkit.getServer().getClass().getPackage()
                .getName().substring(23);
        Object playerlist = Class.forName("org.bukkit.craftbukkit." + bukkitversion + ".CraftServer")
                .getDeclaredMethod("getHandle", null).invoke(Bukkit.getServer(), null);
        Field maxplayers = playerlist.getClass().getSuperclass()
                .getDeclaredField("maxPlayers");
        maxplayers.setAccessible(true);
        maxplayers.set(playerlist, maxPlayers);
    }

}
