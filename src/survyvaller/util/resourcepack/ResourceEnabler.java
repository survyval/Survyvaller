package survyvaller.util.resourcepack;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.md_5.bungee.api.ChatColor;
import survyvaller.Survyvaller;

public class ResourceEnabler implements Listener {
	
	public static final String packId = "https://github.com/survyval/SurvyvalPack/releases/download/0.0.4/SurvyvalPack.zip";
	public static final byte[] packHash = new byte[] {
			(byte) 0x92, (byte) 0x7C, (byte) 0xD7, (byte) 0x5B, (byte) 0xD1, 
			(byte) 0xFA, (byte) 0x9B, (byte) 0x92, (byte) 0xE2, (byte) 0x96, 
			(byte) 0xD0, (byte) 0x5C, (byte) 0xB5, (byte) 0x2E, (byte) 0xCE, 
			(byte) 0xCC, (byte) 0xE0, (byte) 0x17, (byte) 0xD3, (byte) 0x2C
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
