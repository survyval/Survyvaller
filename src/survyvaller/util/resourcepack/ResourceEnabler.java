package survyvaller.util.resourcepack;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.md_5.bungee.api.ChatColor;
import survyvaller.Survyvaller;

public class ResourceEnabler implements Listener {
	
	public static final String packId = "https://github.com/survyval/SurvyvalPack/releases/download/0.0.2/SurvyvalPack.zip";
	public static final byte[] packHash = new byte[] {
			(byte) 0x57, (byte) 0x18, (byte) 0x3A, (byte) 0xD6, (byte) 0x79, 
			(byte) 0x40, (byte) 0x42, (byte) 0x6D, (byte) 0x3D, (byte) 0x48, 
			(byte) 0xBC, (byte) 0xA6, (byte) 0x99, (byte) 0x48, (byte) 0x90, 
			(byte) 0xC3, (byte) 0xEF, (byte) 0xDB, (byte) 0x75, (byte) 0x6C
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
