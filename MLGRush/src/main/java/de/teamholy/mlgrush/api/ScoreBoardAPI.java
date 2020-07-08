package de.teamholy.mlgrush.api;

import com.google.common.collect.Maps;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreBoardAPI {

    public ScoreBoardAPI(Player player) { this.player = player; }

    public void removeScore() { this.scoreMap.clear(); }

    private Map<Integer, String> scoreMap = Maps.newConcurrentMap();

    private String displayName;

    private String[] animationContext;

    private final Player player;

    private Map<String, Integer> schedulerMap = Maps.newConcurrentMap();

    private int animationTick;

    public ScoreBoardAPI(Player player, String displayName) {
        this.player = player;
        this.displayName = displayName;
    }

    public String getLine(int line) { return this.scoreMap.get(Integer.valueOf(line)); }

    public void setLine(int score, String prefix, String suffix) { this.scoreMap.put(Integer.valueOf(score), prefix + ";" + suffix); }

    public void setBoard(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(this.displayName);
        for (int i = 0; i < 20; i++) {
            if (this.scoreMap.get(Integer.valueOf(i)) != null) {
                if (i < 10) {
                    Team team = scoreboard.registerNewTeam("x" + i);
                    String[] raw = ((String)this.scoreMap.get(Integer.valueOf(i))).split(";");
                    team.setPrefix(raw[0]);
                    team.setSuffix(raw[1]);
                    team.addEntry("§" + i);
                    objective.getScore("§" + i).setScore(i);
                } else {
                    Team team = scoreboard.registerNewTeam("x" + i);
                    String[] raw = ((String)this.scoreMap.get(Integer.valueOf(i))).split(";");
                    team.setPrefix(raw[0]);
                    team.setSuffix(raw[1]);
                    team.addEntry("§" + getColorCodeByNumber(i));
                    objective.getScore("§" + getColorCodeByNumber(i)).setScore(i);
                }
                player.setScoreboard(scoreboard);
            }
        }
    }

    public void updateBoard(Player player, int score, String prefix, String suffix) {
        if (player.getScoreboard() != null && player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
            player.getScoreboard().getTeam("x" + score).setSuffix(suffix);
            player.getScoreboard().getTeam("x" + score).setPrefix(prefix);
        }
    }

    public void addAnimation(String[] animationCont, Plugin plugin, Integer animationTick) {
        this.animationContext = animationCont;
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
            int animationTick = 0;

            public void run() {
                if (this.animationTick == ScoreBoardAPI.this.animationContext.length)
                    this.animationTick = 0;
                if (ScoreBoardAPI.this.player.getScoreboard() != null)
                    try {
                        ScoreBoardAPI.this.player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ScoreBoardAPI.this.animationContext[this.animationTick]);
                    } catch (Exception exception) {}
                this.animationTick++;
            }
        },  animationTick, animationTick);
    }

    public void stopAnimation() {
        Bukkit.getScheduler().cancelTask(((Integer)this.schedulerMap.get("scheduler")).intValue());
    }

    private String getColorCodeByNumber(int number) {
        switch (number) {
            case 10:
                return "a";
            case 11:
                return "b";
            case 12:
                return "c";
            case 13:
                return "d";
            case 14:
                return "e";
            case 15:
                return "f";
        }
        return "z";
    }

    public Player getPlayer() {
        return this.player;
    }
}
