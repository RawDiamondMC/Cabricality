package dm.earth.cabricality.listener;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.resource.data.core.FreePRP;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import ho.artisan.lib.recipe.api.RecipeLoadingEvents;
import ho.artisan.lib.recipe.api.RecipeManagerHelper;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

import java.util.ArrayList;
import java.util.Arrays;

public class DeployerCuttingRecipeHandler {
	public static ArrayList<CuttingBoardRecipe> cuttingBoardRecipes = new ArrayList<>();

	private static void addRecipes(RecipeLoadingEvents.AddRecipesCallback.RecipeHandler handler) {
		for (CuttingBoardRecipe boardRecipe : cuttingBoardRecipes) {
			Identifier id = Cabricality.id("cutting/auto/" + String.valueOf(boardRecipe.hashCode()).replaceAll("-", "x"));
			ArrayList<ProcessingOutput> outputs = new ArrayList<>();

			for (ChanceResult chanceResult : boardRecipe.getRollableResults())
				outputs.add(new ProcessingOutput(chanceResult.stack(), chanceResult.chance()));

			FreePRP params = new FreePRP(id)
					.setIngredient(boardRecipe.getTool())
					.setIngredient(boardRecipe.getIngredients())
					.setResult(outputs);
			if (boardRecipe.getIngredients().stream()
					.anyMatch(ingredient -> Arrays.stream(ingredient.getMatchingStacks())
							.anyMatch(stack -> Registries.ITEM.getId(stack.getItem()).getPath().contains("slime_fern"))
					)) params.keepHeldItem();

			DeployerApplicationRecipe recipe = new DeployerApplicationRecipe(params);
			handler.register(id, identifier -> recipe);
		}
	}

	public static void load() {
		RecipeManagerHelper.addRecipes(DeployerCuttingRecipeHandler::addRecipes);
	}
}
