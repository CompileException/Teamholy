package de.teamholy.mlgrush.handler;

import de.teamholy.mlgrush.enums.TeamType;
import de.teamholy.mlgrush.MLGRush;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamHandler {

    private List<Player> teamOne = new ArrayList<>();
    private List<Player> teamTwo = new ArrayList<>();
    private List<Player> spectator = new ArrayList<>();
    private List<Player> noTeam = new ArrayList<>();
    public List<Player> playing = new ArrayList<>();

    private int teamOneSize = 0;
    private int teamTwoSize = 0;
    private int spectatorSize = 0;
    private int noTeamSize = 0;

    public void setSpectator(final Player player, final boolean value) {
        if (value) {
            spectatorSize++;
            spectator.add(player);
        } else {
            spectatorSize--;
            spectator.remove(player);
        }
    }

    public boolean isSpectator(final Player player) {
        return spectator.contains(player);
    }

    public void handleTeams() {
        if(Bukkit.getOnlinePlayers().size() == 2) {
            boolean hasTeam1 = false;
            boolean hasTeam2 = false;
            int index = 1;
            for (Player current : Bukkit.getOnlinePlayers()) {
                if(index == 1) {
                    if(this.hasTeam(current))
                        hasTeam1 = true;
                    index++;
                } else if(index == 2) {
                    if(this.hasTeam(current))
                        hasTeam2 = true;
                }
            }

            if(hasTeam1 && hasTeam2) {
                System.out.println("[MLGRUSH] Teams handled.");
            } else {
                for (Player current : noTeam) {
                    if (!this.isTeamFull(TeamType.BLAU)) {
                        this.joinTeam(current, TeamType.ROT);
                        current.sendMessage(MLGRush.getInstance().getPrefix() + "§7Du wurdest §aTeam #1 §7zugeteilt.");
                    } else {
                        this.joinTeam(current, TeamType.ROT);
                        current.sendMessage(MLGRush.getInstance().getPrefix() + "§7Du wurdest §aTeam #2 §7zugeteilt.");
                    }
                }
                System.out.println("[MLGRUSH] Teams handled.");
            }
        }
    }

    public void fixTeams() {

        TeamType team1 = TeamType.NONE;
        TeamType team2 = TeamType.NONE;
        int index = 1;

        for (Player current : this.playing) {
            if(index == 1) {
                team1 = this.getPlayerTeam(current);
                index++;
            } else if(index == 2) {
                team2 = this.getPlayerTeam(current);
            }
        }

        if(team1 == team2) {
            System.out.println("[MLGRUSH] Teams need to be fixed! Handling new...");
            this.handleTeams();
        }

    }

    public boolean isTeamFull(final TeamType teamType) {
        if (teamType == TeamType.BLAU)
            return teamOneSize == 1;
        if (teamType == TeamType.ROT)
            return teamTwoSize == 1;
        return false;
    }

    public void joinTeam(final Player player, final TeamType teamType) {
        if (teamType == TeamType.BLAU) {
            teamOneSize++;
            teamOne.add(player);
        } else if (teamType == TeamType.ROT) {
            teamTwoSize++;
            teamTwo.add(player);
        } else if (teamType == TeamType.NONE) {
            noTeamSize++;
            noTeam.add(player);
        }
    }

    public void leaveTeam(final Player player, final TeamType teamType) {
        if(teamType == TeamType.BLAU) {
            teamOneSize--;
            teamOne.remove(player);
        } else if (teamType == TeamType.ROT) {
            teamTwoSize--;
            teamTwo.remove(player);
        } else if (teamType == TeamType.NONE) {
            noTeamSize--;
            noTeam.remove(player);
        }
    }

    public boolean hasTeam(final Player player) {
        return (teamOne.contains(player) || teamTwo.contains(player));
    }

    public boolean hasTeamNone(final Player player) {
        return noTeam.contains(player);
    }

    public boolean isInTeam(final TeamType teamType, final Player player) {
        return this.getPlayerTeam(player) == teamType;
    }

    public List<Player> getPlayersInTeam(final TeamType teamType) {
        final List<Player> output = new ArrayList<>();
        if (teamType == TeamType.BLAU)
            output.addAll(teamOne);
        else
            output.addAll(teamTwo);
        return output;
    }

    public TeamType getPlayerTeam(final Player player) {
        if (teamOne.contains(player))
            return TeamType.BLAU;
        if (teamTwo.contains(player))
            return TeamType.ROT;
        return TeamType.NONE;
    }
}
