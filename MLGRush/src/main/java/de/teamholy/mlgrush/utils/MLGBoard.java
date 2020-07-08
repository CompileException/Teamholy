package de.teamholy.mlgrush.utils;

import de.teamholy.mlgrush.api.ScoreBoardAPI;
import de.teamholy.mlgrush.MLGRush;
import de.teamholy.mlgrush.enums.GameState;
import de.teamholy.mlgrush.enums.TeamType;
import de.teamholy.mlgrush.gamestates.GameStateHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class MLGBoard {

    public void setScoreboard(final Player player) {
        final PlayerManager playerManager = new PlayerManager(player);
        final Scoreboard scoreboard;
        final Objective objective;
        final Team playerTeam;
        final Team ingamePlayerTeam;
        final Team roundDeaths;
        final Team points;
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("main", "dummy");
        playerTeam = scoreboard.registerNewTeam("playerTeam");
        ingamePlayerTeam = scoreboard.registerNewTeam("ingamePlayerTeam");
        roundDeaths = scoreboard.registerNewTeam("deaths");
        points = scoreboard.registerNewTeam("points");


        objective.setDisplayName("  §8§l« §3§lMLGRUSH §8§l»  ");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        if (GameStateHandler.getGameState() == GameState.LOBBY) {

            objective.getScore("§d").setScore(6);
            objective.getScore("§7Aktuelle Karte").setScore(5);
            objective.getScore("§8➜ §a" + MLGRush.getInstance().getConfigManager().getMapName()).setScore(4);
            objective.getScore("§d§b§c§n§m§6").setScore(3);
            objective.getScore("§7Dein Team").setScore(2);
            objective.getScore("§1§a").setScore(1);
            objective.getScore("§e").setScore(0);

            playerTeam.addEntry("§1§a");
            if(MLGRush.getInstance().getTeamHandler().getPlayerTeam(player) == TeamType.BLAU)
                playerTeam.setPrefix("§8➜ §bTeam Blau");
            else if(MLGRush.getInstance().getTeamHandler().getPlayerTeam(player) == TeamType.ROT)
                playerTeam.setPrefix("§8➜ §cTeam Rot");
            else
                playerTeam.setPrefix("§8➜ §cKeins");

        } else if (GameStateHandler.getGameState() == GameState.INGAME || GameStateHandler.getGameState() == GameState.ENDING) {

            objective.getScore("").setScore(9);
            objective.getScore("§7Dein Team").setScore(8);
            objective.getScore("§m§r§f§k").setScore(7);
            objective.getScore("§d").setScore(6);
            objective.getScore("§7Tode").setScore(5);
            objective.getScore("§1§a§k").setScore(4);
            objective.getScore("§e").setScore(3);
            objective.getScore("§7Punkte").setScore(2);
            objective.getScore("§e§l§a§r").setScore(1);
            objective.getScore("§e§l§k").setScore(0);

            roundDeaths.addEntry("§1§a§k");
            roundDeaths.setPrefix("§8➜ §a" + playerManager.getRoundDeaths());

            points.addEntry("§e§l§a§r");
            points.setPrefix("§8➜ " + MLGRush.getInstance().getPointsHandler().getGlobalPoints());


            ingamePlayerTeam.addEntry("§m§r§f§k");
            if (MLGRush.getInstance().getTeamHandler().getPlayerTeam(player) == TeamType.BLAU)
                ingamePlayerTeam.setPrefix("§8➜ §bTeam Blau");
            else if (MLGRush.getInstance().getTeamHandler().getPlayerTeam(player) == TeamType.ROT)
                ingamePlayerTeam.setPrefix("§8➜ §cTeam Rot");
            else
                ingamePlayerTeam.setPrefix("§8➜ §cKeins");
        }
        player.setScoreboard(scoreboard);
    }

    private void update(final Player player) {
        final PlayerManager playerManager = new PlayerManager(player);

        if(GameStateHandler.getGameState() == GameState.LOBBY) {

            final Team playerTeam = player.getScoreboard().getTeam("playerTeam");
            if (MLGRush.getInstance().getTeamHandler().getPlayerTeam(player) == TeamType.BLAU)
                playerTeam.setPrefix("§8➜ §bTeam Blau");
            else if (MLGRush.getInstance().getTeamHandler().getPlayerTeam(player) == TeamType.ROT)
                playerTeam.setPrefix("§8➜ §cTeam Rot");
            else
                playerTeam.setPrefix("§8➜ §cKeins");

        } else if(GameStateHandler.getGameState() == GameState.INGAME) {

            final Team roundDeaths = player.getScoreboard().getTeam("deaths");
            roundDeaths.setPrefix("§8➜ §a" + playerManager.getRoundDeaths());

            final Team points = player.getScoreboard().getTeam("points");
            points.setPrefix("§8➜ " + MLGRush.getInstance().getPointsHandler().getGlobalPoints());

            final Team ingamePlayerTeam = player.getScoreboard().getTeam("ingamePlayerTeam");
            if(MLGRush.getInstance().getTeamHandler().getPlayerTeam(player) == TeamType.BLAU)
                ingamePlayerTeam.setPrefix("§8➜ §bTeam Blau");
            else if(MLGRush.getInstance().getTeamHandler().getPlayerTeam(player) == TeamType.ROT)
                ingamePlayerTeam.setPrefix("§8➜ §cTeam Rot");
            else
                ingamePlayerTeam.setPrefix("§8➜ §cKeins");

        }
    }

    public void startUpdater() {
        Bukkit.getScheduler().runTaskTimer(MLGRush.getInstance(), () -> {
            for (Player player : MLGRush.getInstance().getTeamHandler().playing)
                this.update(player);
        }, 0, 2);
    }

}
