package survyvaller.recipe;

import org.bukkit.Bukkit;

public class CustomRecipes {

	public static void registerRecipes() {
		//Rottenflesh -> rabbithide.
		Bukkit.addRecipe(RottenFleshRecipes.smeltRecipe());
	}

}
