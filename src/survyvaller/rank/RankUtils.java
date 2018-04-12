package survyvaller.rank;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import survyvaller.Survyvaller;

public class RankUtils {

	/**
	 * Gets the rank of a player using its uuid.
	 * @param uuid
	 * @return
	 */
	public static Rank getRank(UUID uuid) {
		return Rank.fromString(Survyvaller.getInstance().getConfig().getConfigurationSection("players.ranks").get(uuid.toString()).toString());
	}
	
	/**
	 * Sets the rank of a player using its uuid.
	 * @param uuid
	 * @param rank
	 */
	public static void setRank(UUID uuid, Rank rank) {
		Survyvaller.getInstance().getConfig().getConfigurationSection("players.ranks").set(uuid.toString(), rank.toString());
		Survyvaller.getInstance().saveConfig();
		Player online = Bukkit.getPlayer(uuid);
		if (online != null) {
			online.setDisplayName(rank.getPrefix() + online.getName());
		}
	}
	
	/***
	 * Gets all players of the ranks provided.
	 * @param ranks
	 * @return set of those ranked players.
	 */
	public static Set<? extends Player> getRankedPlayers(Rank... ranks) {
		return Bukkit.getOnlinePlayers().stream().filter(player -> Arrays.binarySearch(ranks, getRank(player.getUniqueId())) >= 0).collect(Collectors.toSet());
	}
	
	/***
	 * Gets all players of the ranks provided.
	 * @param ranks
	 * @return array of those ranked players.
	 */
	public static List<Player> getRankedPlayers(EnumSet<Rank> ranks) {
		return Bukkit.getOnlinePlayers().stream().filter(player -> ranks.contains(getRank(player.getUniqueId()))).collect(Collectors.toList());
	}
	
	/**
	 * Gets all online subs of player with specified uuid.
	 * @param uuid
	 * @return
	 */
	public static List<Player> getSubs(UUID uuid) {
		return RankUtils.getRankedPlayers(RankUtils.getRank(uuid).getSubordinates());
	}
	
	/**
	 * Gets all online sups of player with specified uuid.
	 * @param uuid
	 * @return
	 */
	public static List<Player> getSups(UUID uuid) {
		return RankUtils.getRankedPlayers(RankUtils.getRank(uuid).getSuperiors());
	}
	
}
