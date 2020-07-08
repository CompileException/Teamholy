package de.teamholy.mlgrush.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangedEvent implements Listener {

    @EventHandler
    public void onWeatherChange(final WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
