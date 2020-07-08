package de.teamholy.mlgrush.listener;

import de.teamholy.mlgrush.MLGRush;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftItemListener implements Listener {

    @EventHandler
    public void onCraftItem(final CraftItemEvent event) {
        event.setCancelled(true);
        event.getWhoClicked().sendMessage(MLGRush.getInstance().getPrefix() + "§cDu darfst keine Items craften.");
    }
}
