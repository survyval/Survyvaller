package survyvaller.helper.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import survyvaller.helper.Reports;
import survyvaller.rank.Rank;
import survyvaller.rank.RankUtils;

public class ReportCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (RankUtils.getRank(player.getUniqueId()).isSuperiorOf(Rank.DEFAULT)) {
				if (args.length > 0) {
					String report = String.join(" ", args);
					Reports.report(player, report);
					sender.sendMessage("Reported {" + report + "} to the moderators!");
				} else {
					sender.sendMessage(ChatColor.RED + "Please specify a report!");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have the permission to make admin reports. Talk to a " + Rank.HELPER.getPrefix() + " Helper" + ChatColor.RESET + ".");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Can't report if ur not player dufus!");
		}
		return true;
	}

}
