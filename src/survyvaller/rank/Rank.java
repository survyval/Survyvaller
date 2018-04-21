package survyvaller.rank;

import java.util.EnumSet;
import org.bukkit.ChatColor;

public enum Rank {

	ADMIN(ChatColor.GREEN), 
	MODERATOR(ChatColor.BLUE), 
	SUBMOD(ChatColor.AQUA),
	VIP(ChatColor.RED),
	HELPER(ChatColor.YELLOW), 
	DEFAULT(ChatColor.WHITE), 
	OUTCAST(ChatColor.BLACK);

	private final String prefix;

	private Rank(String prefix) {
		this.prefix = prefix;
	}
	
	private Rank(ChatColor prefix) {
		this.prefix = prefix.toString();
	}
	
	/**
	 * @return the prefix of this rank.
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * @return all subordinates to this rank.
	 */
	public EnumSet<Rank> getSubordinates() {
		return EnumSet.of(this, Rank.OUTCAST);
	}
	
	/**
	 * @return all the superiors to this rank.
	 */
	public EnumSet<Rank> getSuperiors() {
		return EnumSet.of(Rank.ADMIN, this);
	}
	
	/**
	 * @param rank
	 * @return true, if specified rank is superior.
	 */
	public boolean isSuperiorOf(Rank rank) {
		return this.ordinal() < rank.ordinal();
	}
	
	/**
	 * @param rank
	 * @return true, if specified rank is subordinate.
	 */
	public boolean isSubordinateOf(Rank rank) {
		return this.ordinal() > rank.ordinal();
	}
	
	/**
	 * Gets a rank from string or defaults to DEFAULT.
	 * @param rank
	 * @return
	 */
	public static Rank fromString(Object rank) {
		try {
			return valueOf(rank.toString());
		} catch(Exception e) {
			return Rank.DEFAULT;
		}
	}
	
}
