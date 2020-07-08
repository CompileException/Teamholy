package de.teamholy.bridge.utils;

import de.teamholy.bridge.main.BungeeBridge;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Userdata {


    public static void getUserData(Player player, String displayname,
                                   String customname, String playerlistname, String address,
                                   int level, String UUID) {

        if(player.getDisplayName() == null) {
            displayname = "§4Not found";
            player.kickPlayer("§4Fehler beim Laden deiner Daten!");
            for(Player all : Bukkit.getOnlinePlayers()) {
                if(all.hasPermission("system.team")) {
                    all.sendMessage(BungeeBridge.getPrefix() + "§4FEHLER: " + player.getName() + " §4DATEN NICHT KOMPLETT!");
                }
            }
        }
        if(player.getCustomName() == null) { customname = "§4Not found"; }
        if(player.getPlayer() == null) {
            displayname = "§4Not found";
            player.kickPlayer("§4Fehler beim Laden deiner Daten!");
            for(Player all : Bukkit.getOnlinePlayers()) {
                if(all.hasPermission("system.team")) {
                    all.sendMessage(BungeeBridge.getPrefix() + "§4FEHLER: " + player.getName() + " §4DATEN NICHT KOMPLETT!");
                }
            }
        }
        if(player.getAddress() == null) {
            address = "§4Not found";
            player.kickPlayer("§4Fehler beim Laden deiner Daten!");
            for(Player all : Bukkit.getOnlinePlayers()) {
                if(all.hasPermission("system.team")) {
                    all.sendMessage(BungeeBridge.getPrefix() + "§4FEHLER: " + player.getName() + " §4DATEN NICHT KOMPLETT!");
                }
            }
        }
        if(player.getUniqueId().toString() == null) {
            UUID = "§4Not found";
            player.kickPlayer("§4Fehler beim Laden deiner Daten!");
            for(Player all : Bukkit.getOnlinePlayers()) {
                if(all.hasPermission("system.team")) {
                    all.sendMessage(BungeeBridge.getPrefix() + "§4FEHLER: " + player.getName() + " §4DATEN NICHT KOMPLETT!");
                }
            }
        }
        Bukkit.getConsoleSender().sendMessage(BungeeBridge.getPrefix() + "§a!!! USER Information !!!");
        Bukkit.getConsoleSender().sendMessage(BungeeBridge.getPrefix() + "§cDisplayname: " + displayname);
        Bukkit.getConsoleSender().sendMessage(BungeeBridge.getPrefix() + "§cCustomname: " + customname);
        Bukkit.getConsoleSender().sendMessage(BungeeBridge.getPrefix() + "§cPlayerListname: " + playerlistname);
        Bukkit.getConsoleSender().sendMessage(BungeeBridge.getPrefix() + "§cAddress: " + address);
        Bukkit.getConsoleSender().sendMessage(BungeeBridge.getPrefix() + "§cLevel: " + String.valueOf(level));
        Bukkit.getConsoleSender().sendMessage(BungeeBridge.getPrefix() + "§cUUID: " + UUID);
        Bukkit.getConsoleSender().sendMessage(BungeeBridge.getPrefix() + "§a!!! USER Information !!!");

    }


}
