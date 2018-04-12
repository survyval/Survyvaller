package survyvaller.admin.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import survyvaller.rank.Rank;
import survyvaller.rank.RankUtils;
import utils.PlayerUtils;

public class RankCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().toUpperCase().equals("RANK")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Rank senderRank = RankUtils.getRank(player.getUniqueId());
				switch (args.length) {
					case 1: {
						Player target = PlayerUtils.getBestMatchingPlayer(args[0]);
						if (target != null) {
							Rank targetRank = RankUtils.getRank(target.getUniqueId());
							sender.sendMessage(target.getDisplayName() + ChatColor.WHITE + " has the rank: " + targetRank.getPrefix() + targetRank.toString());
						} else {
							sender.sendMessage(ChatColor.RED + "Player " + args[0] + " could not be found!");
						}
						return true;
					}
					case 2: {
						if (senderRank.getLevel() > 4) {
							Player target = PlayerUtils.getBestMatchingPlayer(args[0]);
							if (target != null) {
								Rank targetRank = Rank.fromString(args[1].toUpperCase());
								if (targetRank.getLevel() < senderRank.getLevel()) {
									RankUtils.setRank(target.getUniqueId(), targetRank);
									sender.sendMessage(target.getDisplayName() + ChatColor.WHITE + " is now " + targetRank.getPrefix() + targetRank.toString() + ChatColor.WHITE + "!");
								} else {
									sender.sendMessage(ChatColor.RED + "Can't promote above or to your own rank you dufus!");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "Player " + args[0] + " could not be found!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Only moderation ranks can manage ranks!");
						}
						return true;
					}
					default: {
						sender.sendMessage(ChatColor.YELLOW + "Usage: /rank PLAYER - Displays Rank of PLAYER.");
						if (senderRank.getLevel() > 4) {
							sender.sendMessage(ChatColor.YELLOW + "Usage: /rank PLAYER NEWRANK - sets Rank of PLAYER.");
						}
						return true;
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Only players can do rank shizzle because am lazyy!");
				return true;
			}
		}
		return false;
	}

}
