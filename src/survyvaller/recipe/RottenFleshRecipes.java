package survyvaller.recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import survyvaller.Survyvaller;

public class RottenFleshRecipes {

	public static Recipe smeltRecipe() {
		ItemStack rabbitHide = new ItemStack(Material.RABBIT_HIDE);
		return new FurnaceRecipe(
			new NamespacedKey(Survyvaller.getInstance(), "SmeltRottenFlesh"), 
			rabbitHide, 
			Material.ROTTEN_FLESH, 
			(float) 0.1, 200
		);
	}

}
