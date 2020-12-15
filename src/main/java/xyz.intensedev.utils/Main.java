package xyz.intensedev.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.intensedev.utils.commands.FeedCommand;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public List<String> developers = new ArrayList<>();

    @Override
    public void onEnable() {
        //Add Developers
        developers.add("Codins");
        developers.add("ItsYahoo");

        //Anti Piracy test
        if(getDescription().getAuthors() != developers){
            Bukkit.getConsoleSender().sendMessage("§b[iUtils] §7Authors are not correct make sure you did not edit the plugin.yml");
            Bukkit.getPluginManager().disablePlugin(this);
        }

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
        getCommand("feed").setExecutor(new FeedCommand());
    }

    public void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();

    }

}
