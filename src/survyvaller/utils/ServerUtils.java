package survyvaller.utils;

import org.bukkit.Bukkit;

public class ServerUtils {
	
	/**
	 * @return if the server is full :F
	 */
	public static boolean isServerFull() {
		return Bukkit.getMaxPlayers() == Bukkit.getOnlinePlayers().size();
	}
	
}
