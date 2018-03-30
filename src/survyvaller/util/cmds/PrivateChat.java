package survyvaller.util.cmds;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import utils.PlayerUtils;

public class PrivateChat implements CommandExecutor {
	
	private static Map<UUID, UUID> conversations = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String cmdString, String[] args) {
		if (s instanceof Player) {
			Player sender = (Player) s;
			switch(cmd.getName().toUpperCase()) {
				case "TELL": {
					if (args.length > 1) {
						Player receiver = PlayerUtils.getBestMatchingPlayer(args[0]);
						if (receiver != null) {
							if (!sender.getUniqueId().equals(receiver.getUniqueId())) {
								//Add to conversation
								conversations.put(sender.getUniqueId(), receiver.getUniqueId());
								conversations.put(receiver.getUniqueId(), sender.getUniqueId());
								//Send message
								String[] tell = new String[args.length];
								tell[0] = ChatColor.GRAY + "<" + sender.getDisplayName() + ChatColor.GRAY + ">";
								System.arraycopy(args, 1, tell, 1, args.length - 1);
								receiver.sendMessage(String.join(" ", tell));
								tell[0] += " -> <" + receiver.getDisplayName() + ChatColor.GRAY + ">";
								sender.sendMessage(String.join(" ", tell));
							} else {
								sender.sendMessage(ChatColor.RED + "You talkin to me?!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Could not find player: " + args[0] + "!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "To tell you need to tell who to tell to and what to tell.");
					}
					return true;
				}
				case "REPLY": {
					if (conversations.containsKey(sender.getUniqueId())) {
						Player receiver = Bukkit.getPlayer(conversations.get(sender.getUniqueId()));
						if (receiver != null) {
							if (args.length > 0) {
								receiver.sendMessage(ChatColor.GRAY + "<" + sender.getDisplayName() + ChatColor.GRAY + "> " + String.join(" ", args));
								sender.sendMessage(ChatColor.GRAY + "<" + sender.getDisplayName() + ChatColor.GRAY + "> -> <" + receiver.getDisplayName() + ChatColor.GRAY + "> " + String.join(" ", args));
							} else {
								sender.sendMessage(ChatColor.RED + "To reply you need to tell what to reply!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "You're talking to someone who isn't there anymore.");
							conversations.remove(conversations.get(sender.getUniqueId()));
							conversations.remove(sender.getUniqueId());
						}
					} else {
						sender.sendMessage(ChatColor.RED + "You're not in a conversation right now!");
					}
					return true;
				}
			}
		}
		return false;
	}
	
}
