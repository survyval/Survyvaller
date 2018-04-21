package survyvaller.rank;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.md_5.bungee.api.ChatColor;
import utils.RandUtils;
import utils.ServerUtils;

import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class WarmWelcome implements Listener {
	
	@EventHandler
	public void onAsyncLogin(AsyncPlayerPreLoginEvent event) {
		if (ServerUtils.isServerFull()) {
			RankUtils.getRank(event.getUniqueId()).getSubordinates().stream().forEach(subRank -> {
				List<Player> victims = RankUtils.getRankedPlayers(subRank);
				Player unhappy = RandUtils.fromList(victims);
				if (unhappy != null) {
					unhappy.kickPlayer("You have been kicked to make space for a 'superior', Sorry for the limited server size :O");
					return;
				}
			});
		}
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		event.getPlayer().setDisplayName(RankUtils.getRank(event.getPlayer().getUniqueId()).getPrefix() + event.getPlayer().getName() + ChatColor.RESET);
	}
	
}
