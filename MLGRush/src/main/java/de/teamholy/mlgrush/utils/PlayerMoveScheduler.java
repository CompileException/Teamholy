package de.teamholy.mlgrush.utils;

import de.teamholy.mlgrush.MLGRush;
import de.teamholy.mlgrush.enums.LocationType;
import de.teamholy.mlgrush.gamestates.GameStateHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerMoveScheduler {

    private int taskID;

    public void startListening() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(MLGRush.getInstance(), () -> {
            for (Player current : Bukkit.getOnlinePlayers()) {

                if (!GameStateHandler.isAllowMove()) {
                    current.getLocation().setX(current.getLocation().getX());
                    current.getLocation().setY(current.getLocation().getY());
                    current.getLocation().setZ(current.getLocation().getZ());
                    continue;
                }

                Location pos1;
                if(MLGRush.getInstance().getConfigManager().isCacheLoader())
                    pos1 = MLGRush.getInstance().getLocationHandler().getLocationFromCache(LocationType.POS_1);
                else pos1 = MLGRush.getInstance().getLocationHandler().getLocationByFile(LocationType.POS_1);

                if (current.getLocation().getY() <= pos1.getY()) {
                    final PlayerManager playerManager = new PlayerManager(current);
                    playerManager.addRoundDeath();
                    playerManager.teleportToTeamIsland();
                    playerManager.resetPlayer();
                    MLGRush.getInstance().getInventoryManager().givePlayItems(current);
                    current.playSound(current.getLocation(), Sound.ORB_PICKUP, 3, 1);
                    Bukkit.getScheduler().runTaskLater(MLGRush.getInstance(), () -> current.setHealth(20), 2);
                }

                if (!MLGRush.getInstance().getRegionManager().isInRegion(current.getLocation())) {
                    final PlayerManager playerManager = new PlayerManager(current);
                    playerManager.addRoundDeath();
                    playerManager.teleportToTeamIsland();
                    current.playSound(current.getLocation(), Sound.NOTE_BASS, 3, 1);
                    Bukkit.getScheduler().runTaskLater(MLGRush.getInstance(), () -> current.setHealth(20), 2);
                    current.sendMessage(MLGRush.getInstance().getPrefix() + "§cDu darfst dich nicht außerhalb des Spielbereichs bewegen.");
                }
            }
        }, 0L, 2L);
    }

    public void stopListening() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
