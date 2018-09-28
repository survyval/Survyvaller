package survyvaller.util.resourcepack;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.md_5.bungee.api.ChatColor;
import survyvaller.Survyvaller;

public class ResourceEnabler implements Listener {
	
	public static final String packId = "https://github.com/survyval/SurvyvalPack/releases/download/0.0.3/SurvyvalPack.zip";
	public static final byte[] packHash = new byte[] {
			(byte) 0x06, (byte) 0x35, (byte) 0xEB, (byte) 0xD4, (byte) 0xED, 
			(byte) 0xC3, (byte) 0x99, (byte) 0x82, (byte) 0x86, (byte) 0xC8, 
			(byte) 0xFA, (byte) 0x7B, (byte) 0x71, (byte) 0x66, (byte) 0x8C, 
			(byte) 0x23, (byte) 0xDF, (byte) 0xA9, (byte) 0x5D, (byte) 0xC2
	};
	
	//Apply resourcepack after login to prevent hickups on heavy load on slow internet connections with login handshake.
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Bukkit.getScheduler().runTaskLater(Survyvaller.getInstance(), () -> {
			event.getPlayer().sendActionBar(ChatColor.YELLOW + "Hey, I'm applying the server resources, don't panic if your game freezes for a while :S");
		}, 20l);
		Bukkit.getScheduler().runTaskLater(Survyvaller.getInstance(), () -> {
			event.getPlayer().setResourcePack(packId, packHash);
		}, 40l);
	}
	
}
