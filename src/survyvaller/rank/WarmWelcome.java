package survyvaller.rank;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.md_5.bungee.api.ChatColor;
import survyvaller.utils.RandUtils;
import survyvaller.utils.ServerUtils;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class WarmWelcome implements Listener {
	
	@EventHandler
	public void onAsyncLogin(AsyncPlayerPreLoginEvent event) {
		if (ServerUtils.isServerFull()) {
			RankUtils.getRank(event.getUniqueId()).getSubordinates().stream()
				.map(subRank -> RankUtils.getRankedPlayers(subRank)).filter(plebs -> !plebs.isEmpty())
				.findFirst().ifPresent(plebs -> RandUtils.fromList(plebs)
				.kickPlayer("You have been kicked to make space for a 'superior', Sorry for the limited server size :O"));
		}
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		event.getPlayer().setDisplayName(RankUtils.getRank(event.getPlayer().getUniqueId()).getPrefix() + event.getPlayer().getName() + ChatColor.RESET);
	}
	
}
