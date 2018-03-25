package survyvaller.entities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * In charge of disabling all non-wanted entity crap.
 */
public class EntityDisabler implements Listener {
	
	private static List<EntityType> disabledEntities = new ArrayList<EntityType>();
	static {
		disabledEntities.add(EntityType.WITCH);
		disabledEntities.add(EntityType.WITHER);
	}
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if (
			disabledEntities.contains(e.getEntityType()) || 
			(
				e.getEntityType() == EntityType.ZOMBIE &&
				((Zombie) e.getEntity()).isBaby()
			)
		) {
			e.setCancelled(true);
		}
	}
	
}
