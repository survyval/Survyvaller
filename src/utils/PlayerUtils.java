package utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerUtils {
	
	/***
	 * Gets the closest matching online player given name entered.
	 * @param name
	 * @return
	 */
	public static Player getBestMatchingPlayer(String name) {
		Player bestMatch = Bukkit.getPlayer(name);
		if (bestMatch == null) {
			List<Player> matching = Bukkit.matchPlayer(name);
			if (matching.size() > 0) {
				return matching.get(0);
			}
		}
		return bestMatch;
	}
	
	/**
	 * Gets bed or world spawn location if bed is invalid.
	 * @param player
	 * @return
	 */
	public static Location getHome(Player player) {
		Location bed = player.getBedSpawnLocation();
		if (bed == null) {
			return player.getWorld().getSpawnLocation();
		} else {
			return bed;
		}
	}
	
	/**
	 * Gets the most north thing in the map of the player.
	 * @param player
	 * @return
	 */
	public static Location getNorth(Player player) {
		Location north = player.getWorld().getWorldBorder().getCenter().clone();
		north.subtract(0, 0, player.getWorld().getWorldBorder().getSize() / 2);
		return north;
	}
	
}
