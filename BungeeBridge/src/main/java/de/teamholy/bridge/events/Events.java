package de.teamholy.bridge.events;

import de.teamholy.bridge.main.BungeeBridge;
import de.teamholy.bridge.utils.Userdata;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(BungeeBridge.getInstance().getPrefix() + "§aWillkommen §6" + player.getName() +
                " §7auf dem §a" + player.getServer().getServerName() + " §7Server!");

        Userdata.getUserData(player, player.getDisplayName(), player.getCustomName(),
                player.getPlayerListName(), player.getAddress().toString(),
                player.getLevel(), player.getUniqueId().toString());

        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
    }


}
