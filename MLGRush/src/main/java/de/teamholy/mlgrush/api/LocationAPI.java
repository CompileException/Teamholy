package de.teamholy.mlgrush.api;

import de.teamholy.mlgrush.MLGRush;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationAPI {

    public static void setLoc(Player player, Location location) {
        MLGRush.getInstance().getConfig().set("spawn.world", (Object)player.getLocation().getWorld().getName());
        MLGRush.getInstance().getConfig().set("spawn.x", (Object)player.getLocation().getX());
        MLGRush.getInstance().getConfig().set("spawn.y", (Object)player.getLocation().getY());
        MLGRush.getInstance().getConfig().set("spawn.z", (Object)player.getLocation().getZ());
        MLGRush.getInstance().getConfig().set("spawn.yaw", (Object)player.getLocation().getYaw());
        MLGRush.getInstance().getConfig().set("spawn.pitch", (Object)player.getLocation().getPitch());
        MLGRush.getInstance().saveConfig();
        player.sendMessage(MLGRush.getInstance().getPrefix() + "§eDu hast den Spawnpunkt gesetzt.");
    }

    public static void setDeathHeight(Player player, Location location) {
        final int height = player.getLocation().getBlockY();
        MLGRush.getInstance().getConfig().set("DeathHeight", (Object)height);
        MLGRush.getInstance().saveConfig();
        player.sendMessage(MLGRush.getInstance().getPrefix() + "§aDu hast die Todeshöhe auf§e " + height + " §agesetzt.");
    }

    public static Location getSpawn(Player player, Location location) {
        Location loc = null;
        final String world = MLGRush.getInstance().getConfig().getString("spawn.world");
        final double x = MLGRush.getInstance().getConfig().getDouble("spawn.x");
        final double y = MLGRush.getInstance().getConfig().getDouble("spawn.y");
        final double z = MLGRush.getInstance().getConfig().getDouble("spawn.z");
        final float yaw = (float)MLGRush.getInstance().getConfig().getDouble("spawn.yaw");
        final float pitch = (float)MLGRush.getInstance().getConfig().getDouble("spawn.pitch");
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        return location;
    }

}
