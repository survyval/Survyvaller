package survyvaller;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import survyvaller.admin.cmds.AdminCmd;
import survyvaller.entities.EntityDisabler;
import survyvaller.util.cmds.CmdBlocker;
import survyvaller.util.cmds.PrivateChat;

public class Survyvaller extends JavaPlugin {

	private Survyvaller INSTANCE;
	public Survyvaller getInstance() {
		return INSTANCE;
	}
	
	@Override 
	public void onLoad() {
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
		getCommand("tell").setExecutor(new PrivateChat());
		getCommand("reply").setExecutor(new PrivateChat());
		getCommand("admin").setExecutor(new AdminCmd());
	}
	
	@Override
	public void onDisable() {
		//Wrap up.
	}
	
}
