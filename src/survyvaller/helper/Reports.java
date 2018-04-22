package survyvaller.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Reports {

	//TODO: Store reports persistantly (use plugmod :F)
	private static final int reportsPerPage = 5;
	private static List<Report> reports = new ArrayList<>();

	public static void report(Player player, String report) {
		reports.add(new Report(player, report));
	}

	public static int size() {
		return reports.size();
	}

	public static String[] getReports(int page) {
		int pages = reports.size() / reportsPerPage;
		int first = page * reportsPerPage, last = (page + 1) * reportsPerPage;
		AtomicInteger count = new AtomicInteger(first);
		if (page > pages) {
			return new String[]{"Invalid Page!"};
		} else if (page == pages) {
			last = reports.size();
		}
		return reports.subList(first, last).stream().map(report -> "[" + count.getAndIncrement() + "] " + report.getReport()).toArray(size -> new String[size]);
	}

	public static Location getLocation(int report) {
		return reports.get(report).getLocation();
	}

	public static String getReport(int report) {
		return reports.get(report).getReport();
	}

	public static void deleteReport(int report) {
		reports.remove(report);
	}

	public static class Report {

		private String report;
		private UUID reporter;
		private Location location;

		public Report(Player reporter, String report) {
			this("<" + reporter.getDisplayName() + ChatColor.RESET + "> " + report, reporter.getUniqueId(), reporter.getLocation());
		}

		public Report(String report, UUID reporter, Location location) {
			this.report = report;
			this.reporter = reporter;
			this.location = location;
		}

		public String getReport() {
			return report;
		}

		public UUID getReporter() {
			return reporter;
		}

		public Location getLocation() {
			return location;
		}

	}

}
