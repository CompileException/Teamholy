package de.teamholy.mlgrush.gamestates;

import de.teamholy.mlgrush.MLGRush;
import de.teamholy.mlgrush.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

public class StartCountDown {

    private static int taskID;
    private int index;

    public void start() {
        index = 5;
        taskID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(MLGRush.getInstance(), new Runnable() {
            @Override
            public void run() {

                switch (index) {
                    case 5 : case 3 : case 2 : case 1 :
                        MLGRush.getInstance().getTeamHandler().playing.forEach(current -> {
                            final PlayerManager playerManager = new PlayerManager(current);
                            playerManager.sendTitle("§a§l" + index, "");
                            current.playSound(current.getLocation(), Sound.ORB_PICKUP, 3, 1);
                        });
                        break;
                    case 0:
                        MLGRush.getInstance().getTeamHandler().playing.forEach(current -> {
                            final PlayerManager playerManager = new PlayerManager(current);
                            playerManager.sendTitle("§a§lLOS!", "§7Das Spiel startet");
                            current.playSound(current.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                        });
                        GameStateHandler.setAllowMove(true);
                        StartCountDown.stop();
                        break;
                }

                index--;

            }
        }, 0, 20);
    }

    public static void stop() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
