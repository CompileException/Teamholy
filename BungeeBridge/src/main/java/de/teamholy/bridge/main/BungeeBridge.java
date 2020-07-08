package de.teamholy.bridge.main;

import de.teamholy.bridge.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeBridge extends JavaPlugin {

    private static String prefix = "§7[§eBridge§7] -> ";

    private static BungeeBridge instance;


    //plugin start
    @Override
    public void onEnable() {

        fetchingData();


    }


    //plugin stop
    @Override
    public void onDisable() {

    }


    //register Listener & Commands
    private void register() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Events(), this);

    }

    //Load all utils, methods usw.
    private void fetchingData() {
        try {
            register();
            instance = this;

            Bukkit.getConsoleSender().sendMessage(prefix + "§aBungeebridge wurde gestartet!");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(prefix + "§4Bungeebridge konnte nicht gestartet werden!");
            e.printStackTrace();
        }
    }


    public static BungeeBridge getInstance() { return instance; }
    public static String getPrefix () { return prefix; }
}
