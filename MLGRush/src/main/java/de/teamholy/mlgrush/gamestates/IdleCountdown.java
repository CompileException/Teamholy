package de.teamholy.mlgrush.gamestates;

import de.teamholy.mlgrush.MLGRush;
import de.teamholy.mlgrush.enums.GameState;
import de.teamholy.mlgrush.utils.PlayerManager;
import org.bukkit.Bukkit;

public class IdleCountdown {

    private static int taskID;

    public void start() {
        taskID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(MLGRush.getInstance(), () -> {
            if(GameStateHandler.getGameState() == GameState.LOBBY && Bukkit.getOnlinePlayers().size() == 1) {
                Bukkit.getOnlinePlayers().forEach(current -> {
                    final PlayerManager playerManager = new PlayerManager(current);
                    playerManager.sendActionBar(MLGRush.getInstance().getPrefix() + "§cWarte auf §7einen §cweiteren Spieler.");
                });
            }
        }, 0L, 500L);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
