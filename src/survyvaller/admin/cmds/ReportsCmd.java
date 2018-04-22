package survyvaller.admin.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import survyvaller.Survyvaller;
import survyvaller.helper.Reports;
import survyvaller.rank.Rank;
import survyvaller.rank.RankUtils;

public class ReportsCmd implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (RankUtils.getRank(player.getUniqueId()).isSuperiorOf(Rank.VIP)) {
				if (args.length == 0 || !args[0].equalsIgnoreCase("view")) {
					int page;
					try {
						page = args.length > 0 ? Integer.parseInt(args[0]) : 0;
					} catch (NumberFormatException e) {
						sender .sendMessage(ChatColor.RED + args[0] + " is not a number or the 'view' option!");
						return true;
					}
					sender.sendMessage("===== " + ChatColor.GREEN + "Reports [" + page + "]" + ChatColor.RESET + " =====");
					sender.sendMessage(Reports.getReports(page));
				} else {
					if (AdminCmd.isInAdminMode(player.getUniqueId())) {
						int report = 0;
						if (args.length > 1) {
							try {
								report = Integer.parseInt(args[1]);
							} catch (NumberFormatException e) {
								sender.sendMessage(ChatColor.RED + args[1] + " is not a number!");
								return true;
							}
						}
						if (report < 0 || report >= Reports.size()) {
							sender.sendMessage(ChatColor.RED + "Report " + report + " does not exist!");
							return true;
						}
						player.teleport(Reports.getLocation(report));
						player.sendMessage("Teleported to: " + Reports.getReport(report));
						Reports.deleteReport(report);
					} else {
						sender.sendMessage(ChatColor.RED + "You can only teleport to a report when you are in adminmode!");
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Don't bother I'm lazy!");
		}
		return true;
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		if (Reports.size() > 0 && RankUtils.getRank(event.getPlayer().getUniqueId()).isSuperiorOf(Rank.VIP)) {
			Bukkit.getScheduler().runTaskLater(Survyvaller.getInstance(), () -> {
				if (Reports.size() == 1) {
					event.getPlayer().sendMessage(ChatColor.RED + "NOTICE: There is 1 unhandled report!");
				} else {
					event.getPlayer().sendMessage(ChatColor.RED + "NOTICE: There are " + Reports.size() + " unhandled reports!");
				}
			}, 200l);
		}
	}
	
}
