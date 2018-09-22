package survyvaller.util.item.compass;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import survyvaller.utils.PlayerUtils;

public class CompassListener implements Listener {
	
	private static final Map<UUID, CompassMode> compass = new HashMap<>();
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = false)
	public void onInteract(PlayerInteractEvent event) {
		if (
			(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) &&
			event.getPlayer().isSneaking() && 
			(
				(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS) ||
				(event.getPlayer().getInventory().getItemInOffHand().getType() == Material.COMPASS)
			)
		) {
			CompassMode mode = cycleMode(compass.get(event.getPlayer().getUniqueId()));
			event.getPlayer().sendActionBar(ChatColor.YELLOW + "Compass" + ChatColor.WHITE + " set to " + ChatColor.GREEN + mode.toString().toLowerCase() + ChatColor.WHITE + ".");
			handleMode(event.getPlayer(), mode);
			compass.put(event.getPlayer().getUniqueId(), mode);
		}
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onLogin(PlayerLoginEvent event) {
		CompassMode mode = compass.get(event.getPlayer().getUniqueId());
		if (mode == null) { mode = CompassMode.SPAWN; }
		handleMode(event.getPlayer(), mode);
	}
	
	public static CompassMode cycleMode(CompassMode previous) {
		switch(previous != null ? previous : CompassMode.HOME) {
			case HOME:  return CompassMode.NORTH;
			case NORTH: return CompassMode.SPAWN;
			case SPAWN: return CompassMode.HOME;
			default:    return CompassMode.HOME;
		}
	}
	
	public static void handleMode(Player player, CompassMode mode) {
		switch(mode) {
		case HOME: {
			player.setCompassTarget(PlayerUtils.getHome(player));
			break;
		}
		case NORTH: {
			player.setCompassTarget(PlayerUtils.getNorth(player));
			break;
		}
		case SPAWN: {
			player.setCompassTarget(player.getWorld().getSpawnLocation());
			break;
		}
		default:
			break;
		}
	}
	
	public enum CompassMode {
		NORTH, SPAWN, HOME
	}
	
}
