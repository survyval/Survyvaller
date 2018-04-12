package survyvaller;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import survyvaller.admin.cmds.AdminCmd;
import survyvaller.admin.cmds.RankCmd;
import survyvaller.entities.EntityDisabler;
import survyvaller.rank.WarmWelcome;
import survyvaller.util.cmds.CmdBlocker;
import survyvaller.util.cmds.PrivateChat;
import survyvaller.util.item.compass.CompassListener;
import survyvaller.util.resourcepack.ResourceEnabler;
import survyvaller.util.sign.SignListener;

public class Survyvaller extends JavaPlugin {

	private static Survyvaller INSTANCE;
	public static Survyvaller getInstance() {
		return INSTANCE;
	}
	
	@Override 
	public void onLoad() {
		saveDefaultConfig();
		reloadConfig();
		INSTANCE = this;
		//Do setup of classes.
		try {
			Class.forName(EntityDisabler.class.getName());
			Class.forName(CmdBlocker.class.getName());
		} catch (ClassNotFoundException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Error, server setup wrong!", e.getException());
		}
	}
	
	@Override
	public void onEnable() {
		//Register listeners.
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EntityDisabler(), this);
		pm.registerEvents(new CmdBlocker(), this);
		pm.registerEvents(new SignListener(), this);
		pm.registerEvents(new CompassListener(), this);
		pm.registerEvents(new ResourceEnabler(), this);
		pm.registerEvents(new WarmWelcome(), this);
		getCommand("tell").setExecutor(new PrivateChat());
		getCommand("reply").setExecutor(new PrivateChat());
		getCommand("admin").setExecutor(new AdminCmd());
		getCommand("rank").setExecutor(new RankCmd());
	}
	
	@Override
	public void onDisable() {
		//Wrap up.
	}
	
}
