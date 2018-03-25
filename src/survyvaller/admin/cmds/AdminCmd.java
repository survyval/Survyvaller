package survyvaller.admin.cmds;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCmd implements CommandExecutor {
	
	private static Map<UUID, Location> adminsInAdminMode = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().toUpperCase().equals("ADMIN")) {
			if (sender instanceof Player && sender.isOp()) {
				Player player = (Player) sender;
				if (!adminsInAdminMode.containsKey(player.getUniqueId())) {
					adminsInAdminMode.put(player.getUniqueId(), player.getLocation().clone());
					player.setGameMode(GameMode.SPECTATOR);
					player.sendMessage(ChatColor.BLUE + "Entered AdminMode!");
				} else {
					player.teleport(adminsInAdminMode.get(player.getUniqueId()));
					player.setGameMode(GameMode.SURVIVAL);
					adminsInAdminMode.remove(player.getUniqueId());
					player.sendMessage(ChatColor.BLUE + "Exited AdminMode!");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Only admin players can enter adminmode!");
			}
			return true;
		}
		return false;
	}

}
