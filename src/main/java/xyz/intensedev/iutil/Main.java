package xyz.intensedev.iutil;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.intensedev.iutil.commands.*;
import xyz.intensedev.iutil.commands.soical.*;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        //Config
        saveDefaultConfig();

        //Commands
        registerCommands();

        //Listeners
        registerListeners();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public void registerCommands(){
        if (getConfig().getBoolean("Discord.Enabled")) {
            getCommand("discord").setExecutor(new DiscordCommand());
        }
        if (getConfig().getBoolean("Store.Enabled")) {
            getCommand("store").setExecutor(new StoreCommand());
        }
        if (getConfig().getBoolean("TeamSpeak.Enabled")) {
            getCommand("teamspeak").setExecutor(new TeamSpeakCommand());
        }
        if (getConfig().getBoolean("Twitter.Enabled")) {
            getCommand("twitter").setExecutor(new TwitterCommand());
        }
        if (getConfig().getBoolean("Website.Enabled")) {
            getCommand("website").setExecutor(new WebsiteCommand());
        }
        if (getConfig().getBoolean("Trash.Enabled")) {
            getCommand("trash").setExecutor(new TrashCommand());
        }
        getCommand("setslots").setExecutor(new SetSlotsCommand());
        getCommand("setmotd").setExecutor(new SetMOTDCommand());
        getCommand("rename").setExecutor(new RenameCommand());
    }

    public void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new SetMOTDCommand(), this);
        pm.registerEvents(new SetSlotsCommand(), this);
    }

    public static Main getInstance() {
        return instance;
    }

}
