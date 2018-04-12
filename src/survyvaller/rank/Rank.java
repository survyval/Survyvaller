package survyvaller.rank;

import java.util.EnumSet;
import org.bukkit.ChatColor;

public enum Rank {
	ADMIN(ChatColor.GREEN, 7), 
	MODERATOR(ChatColor.BLUE, 6), 
	VIP(ChatColor.RED, 2), 
	HELPER(ChatColor.YELLOW, 1), 
	DEFAULT(ChatColor.WHITE, 0), 
	OUTCAST(ChatColor.BLACK, -1);
	
	private final String prefix;
	private final int level;
	
	private Rank(String prefix, int level) {
		this.prefix = prefix;
		this.level = level;
	}
	
	private Rank(ChatColor prefix, int level) {
		this.prefix = prefix.toString();
		this.level = level;
	}
	
	/**
	 * @return the permission level of this rank.
	 */
	public int getLevel() {
		return level;
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
	 * Gets a rank from string or defaults to DEFAULT.
	 * @param rank
	 * @return
	 */
	public static Rank fromString(String rank) {
		try {
			return valueOf(rank);
		} catch(Exception e) {
			return Rank.DEFAULT;
		}
	}
	
}
