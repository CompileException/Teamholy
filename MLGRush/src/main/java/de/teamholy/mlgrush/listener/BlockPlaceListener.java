package de.teamholy.mlgrush.listener;

import de.teamholy.mlgrush.MLGRush;
import de.teamholy.mlgrush.enums.GameState;
import de.teamholy.mlgrush.enums.LocationType;
import de.teamholy.mlgrush.gamestates.GameStateHandler;
import de.teamholy.mlgrush.utils.PlayerManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final PlayerManager playerManager = new PlayerManager(player);

        if (GameStateHandler.getGameState() == GameState.INGAME) {

            Location spawn1;
            Location spawn2;
            if(MLGRush.getInstance().getConfigManager().isCacheLoader()) {
                spawn1 = MLGRush.getInstance().getLocationHandler().getLocationFromCache(LocationType.BLAU);
                spawn2 = MLGRush.getInstance().getLocationHandler().getLocationFromCache(LocationType.ROT);
            } else {
                spawn1 = MLGRush.getInstance().getLocationHandler().getLocationByFile(LocationType.BLAU);
                spawn2 = MLGRush.getInstance().getLocationHandler().getLocationByFile(LocationType.ROT);
            }

            if((event.getBlock().getLocation().getBlockX() == spawn1.getBlockX() && event.getBlock().getLocation().getBlockY() == spawn1.getBlockY()
                    && event.getBlock().getLocation().getBlockZ() == spawn1.getBlockZ()) || (event.getBlock().getLocation().getBlockX() == spawn2.getBlockX()
                    && event.getBlock().getLocation().getBlockY() == spawn2.getBlockY() && event.getBlock().getLocation().getBlockZ() == spawn2.getBlockZ())) {
                event.setCancelled(true);
                player.sendMessage(MLGRush.getInstance().getPrefix() + "§cDu darfst nicht auf Spawns bauen.");
                return;
            }

            if (MLGRush.getInstance().getRegionManager().isInRegion(event.getBlock().getLocation()))
                MLGRush.getInstance().getMapResetHandler().addPlacedBlock(event.getBlock().getLocation());
            else {
                event.setCancelled(true);
                player.sendMessage(MLGRush.getInstance().getPrefix() + "§cDu darfst außerhalb des Spielbereiches nicht bauen!");
            }
        } else if (GameStateHandler.getGameState() == GameState.LOBBY || GameStateHandler.getGameState() == GameState.ENDING) {
            if(playerManager.getCurrentSetupBed() == null) {
                event.setCancelled(!playerManager.isInBuildMode());
            }
        }
    }
}
