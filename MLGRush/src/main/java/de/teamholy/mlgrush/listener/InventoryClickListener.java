package de.teamholy.mlgrush.listener;

import de.teamholy.mlgrush.MLGRush;
import de.teamholy.mlgrush.enums.TeamType;
import de.teamholy.mlgrush.utils.PlayerManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {

            try {
                final PlayerManager playerManager = new PlayerManager((Player) event.getWhoClicked());
                final Player player = playerManager.getPlayer();
                final MLGRush instance = MLGRush.getInstance();

                if (event.getClickedInventory().getName().equalsIgnoreCase(instance.getInventoryManager().getTEAM_SEL_TITLE())) {
                    event.setCancelled(true);
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(instance.getInventoryManager().getTEAM_SEL_1())) {
                        player.playSound(player.getLocation(), Sound.CLICK, 3, 1);
                        if(instance.getTeamHandler().getPlayerTeam(player) != TeamType.BLAU) {
                            if(!instance.getTeamHandler().isTeamFull(TeamType.BLAU)) {
                                if(instance.getTeamHandler().getPlayerTeam(player) == TeamType.NONE)
                                    instance.getTeamHandler().leaveTeam(player, TeamType.NONE);
                                if(instance.getTeamHandler().isInTeam(TeamType.ROT, player))
                                    instance.getTeamHandler().leaveTeam(player, TeamType.ROT);
                                instance.getTeamHandler().joinTeam(player, TeamType.BLAU);
                                player.sendMessage(instance.getPrefix() + "§7Du hast das Team §a#1 §7betreten.");
                                player.closeInventory();
                                instance.getTabListHandler().setTabList(player);
                                MLGRush.getInstance().getScoreboardHandler().setScoreboard(player);
                            } else {
                                player.sendMessage(instance.getPrefix() + "§cDieses Team ist bereits voll.");
                                player.closeInventory();
                            }
                        } else {
                            player.sendMessage(instance.getPrefix() + "§cDu bist bereits in diesem Team.");
                            player.closeInventory();
                        }
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(instance.getInventoryManager().getTEAM_SEL_2())) {
                        player.playSound(player.getLocation(), Sound.CLICK, 3, 1);
                        if(instance.getTeamHandler().getPlayerTeam(player) != TeamType.ROT) {
                            if(!instance.getTeamHandler().isTeamFull(TeamType.ROT)) {
                                if(instance.getTeamHandler().getPlayerTeam(player) == TeamType.NONE)
                                    instance.getTeamHandler().leaveTeam(player, TeamType.NONE);
                                if(instance.getTeamHandler().isInTeam(TeamType.BLAU, player))
                                    instance.getTeamHandler().leaveTeam(player, TeamType.BLAU);
                                instance.getTeamHandler().joinTeam(player, TeamType.ROT);
                                player.sendMessage(instance.getPrefix() + "§7Du hast das Team §a#2 §7betreten.");
                                player.closeInventory();
                                instance.getTabListHandler().setTabList(player);
                                MLGRush.getInstance().getScoreboardHandler().setScoreboard(player);
                            } else {
                                player.sendMessage(instance.getPrefix() + "§cDieses Team ist bereits voll.");
                                player.closeInventory();
                            }
                        } else {
                            player.sendMessage(instance.getPrefix() + "§cDu bist bereits in diesem Team.");
                            player.closeInventory();
                        }
                        player.playSound(player.getLocation(), Sound.CLICK, 3, 1);
                    }
                }
            } catch(Exception e) {

            }
        }
    }
}
