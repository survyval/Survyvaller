package survyvaller.util.running;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import kmod.plugmod.kfood.kFood;

public class RunListener implements Listener {

	@EventHandler
	public void onSprint(PlayerToggleSprintEvent event) {
		if (!event.getPlayer().getLocation().getBlock().isLiquid() && event.isSprinting()) {
			kFood.getPlugin(kFood.class).setFoodLevel(event.getPlayer(), 5);
			kFood.getPlugin(kFood.class).updateFoodLevel(event.getPlayer());
		} else {
			kFood.getPlugin(kFood.class).setFoodLevel(event.getPlayer(), 8);
			kFood.getPlugin(kFood.class).updateFoodLevel(event.getPlayer());
		}
	}

}
