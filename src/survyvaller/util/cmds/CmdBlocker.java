package survyvaller.util.cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CmdBlocker implements Listener {
	
	private static List<String> disabledCommands = new ArrayList<String>();
	static {
		disabledCommands.add("give");
		disabledCommands.add("fill");
		disabledCommands.add("gamemode");
		disabledCommands.add("replaceitem");
		disabledCommands.add("summon");
		disabledCommands.add("tp");
		disabledCommands.add("xp");
		disabledCommands.add("weather");
		disabledCommands.add("time");
		disabledCommands.add("gamemode");
		disabledCommands.add("enchant");
		disabledCommands.add("kfood");
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String[] args = e.getMessage().split(" ");
		args[0] = args[0].replaceFirst("/", "").toLowerCase();
		if (disabledCommands.contains(args[0])) {
			e.getPlayer().sendMessage(ChatColor.RED + "That command is blocked!");
			e.setCancelled(true);
		}
	}
	
}
