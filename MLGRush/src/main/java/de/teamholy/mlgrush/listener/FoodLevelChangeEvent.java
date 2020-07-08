package de.teamholy.mlgrush.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FoodLevelChangeEvent implements Listener {

    @EventHandler
    public void onFoodLevelChange(final org.bukkit.event.entity.FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
