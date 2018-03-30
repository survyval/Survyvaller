package survyvaller.util.sign;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onSignSign(SignChangeEvent event) {
		for (int i = 0; i < 4; i++) {
			event.setLine(i, ChatColor.translateAlternateColorCodes('&', event.getLine(i)));
		}
	}
	
}
