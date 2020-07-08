package de.teamholy.mlgrush.listener;

import de.teamholy.mlgrush.MLGRush;
import de.teamholy.mlgrush.enums.GameState;
import de.teamholy.mlgrush.enums.LocationType;
import de.teamholy.mlgrush.enums.TeamType;
import de.teamholy.mlgrush.gamestates.GameStateHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {

            final Player damages = (Player) event.getDamager();
            final Player player = (Player) event.getEntity();

            if (GameStateHandler.getGameState() == GameState.LOBBY || GameStateHandler.getGameState() == GameState.ENDING) {
                event.setCancelled(true);
            } else if (GameStateHandler.getGameState() == GameState.INGAME
                    && MLGRush.getInstance().getConfigManager().isDamageCheck()) {

                Location spawn1;
                Location spawn2;
                if(MLGRush.getInstance().getConfigManager().isCacheLoader()) {
                    spawn1 = MLGRush.getInstance().getLocationHandler().getLocationFromCache(LocationType.BLAU);
                    spawn2 = MLGRush.getInstance().getLocationHandler().getLocationFromCache(LocationType.ROT);
                } else {
                    spawn1 = MLGRush.getInstance().getLocationHandler().getLocationByFile(LocationType.BLAU);
                    spawn2 = MLGRush.getInstance().getLocationHandler().getLocationByFile(LocationType.ROT);
                }

                if (MLGRush.getInstance().getTeamHandler().getPlayerTeam(damages) == TeamType.BLAU) {
                    if(player.getLocation().getBlockY() >= spawn2.getBlockY()) {
                        event.setCancelled(true);
                        damages.sendMessage(MLGRush.getInstance().getPrefix() + "§cDu kannst §7" + player.getName() + " §cnicht am Spawn schlagen.");
                    }
                } else if (MLGRush.getInstance().getTeamHandler().getPlayerTeam(damages) == TeamType.ROT) {
                    if(player.getLocation().getBlockY() >= spawn1.getBlockY()) {
                        event.setCancelled(true);
                        damages.sendMessage(MLGRush.getInstance().getPrefix() + "§cDu kannst §7" + player.getName() + " §cnicht am Spawn schlagen.");
                    }
                }
            }
        } else
            event.setCancelled(true);
    }
}
