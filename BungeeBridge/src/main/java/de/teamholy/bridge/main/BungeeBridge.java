package de.teamholy.bridge.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeBridge extends JavaPlugin {

    private static final String prefix = "§7[§eBridge§7] -> ";


    //plugin start
    @Override
    public void onEnable() {


    }


    //plugin stop
    @Override
    public void onDisable() {

    }


    //register Listener & Commands
    private void register() {

    }

    //Load all utils, methods usw.
    private void fetchingData() {
        try {
            register();

            Bukkit.getConsoleSender().sendMessage(prefix + "§aBungeebridge wurde gestartet!");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(prefix + "§4Bungeebridge konnte nicht gestartet werden!");
            e.printStackTrace();
        }

    }
}
