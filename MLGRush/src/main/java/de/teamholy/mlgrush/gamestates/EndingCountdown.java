package de.teamholy.mlgrush.gamestates;

import de.teamholy.mlgrush.MLGRush;
import de.teamholy.mlgrush.enums.GameState;
import de.teamholy.mlgrush.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EndingCountdown {

    private int taskID;
    private int countDown;

    public void start() {
        countDown = 20;

        MLGRush.getInstance().getPointsHandler().stopPointsActionBar();
        MLGRush.getInstance().getStartCountDown().stop();
        MLGRush.getInstance().getPlayerMoveScheduler().stopListening();
        GameStateHandler.setGameState(GameState.ENDING);

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(MLGRush.getInstance(), () -> {

            for (Player current : Bukkit.getOnlinePlayers()) {
                final PlayerManager playerManager = new PlayerManager(current);
                MLGRush.getInstance().getInventoryManager().givePlayerLobbyItems(current);
                current.getInventory().setItem(0, new ItemStack(Material.AIR));
                playerManager.sendActionBar(MLGRush.getInstance().getPrefix() + " §7Der Server startet in §a" + countDown + " §7Sekunden neu.");
            }

            switch (countDown) {
                case 20 : case 15 : case 10 : case 5 : case 3 : case 2 :
                    Bukkit.broadcastMessage(MLGRush.getInstance().getPrefix() + "§7Der Server startet in §a" + countDown + " §7Sekunden neu.");
                    break;
                case 1 :
                    Bukkit.broadcastMessage(MLGRush.getInstance().getPrefix() + "§7Der Server startet in §aeiner §7Sekunde neu.");
                    break;
                case 0:
                    Bukkit.getOnlinePlayers().forEach(current -> current.kickPlayer(MLGRush.getInstance().getPrefix() + "§7Der Server startet neu."));
                    this.stop();
                    Bukkit.shutdown();
            }

            countDown--;
        }, 0, 20);
    }

    private void stop() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
