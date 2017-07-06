package halestormxv.eAngelus.main;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;

public class EARemoveRecipes 
{
	public static void removeCraftingRecipes(Item item)
	{
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		Iterator<IRecipe> remover = recipes.iterator();

		while(remover.hasNext())
		{
			ItemStack stack = remover.next().getRecipeOutput();

			if (stack != null && stack.getItem() == item)
			{
				remover.remove();
			}
		}
	}

	public static void removeCraftingRecipes(Block block)
	{
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		Iterator<IRecipe> remover = recipes.iterator();

		while(remover.hasNext())
		{
			ItemStack stack = remover.next().getRecipeOutput();

			if (stack != null && stack.getItem() == Item.getItemFromBlock(block))
			{
				remover.remove();
			}
		}
	}

	public static void removeFurnaceRecipe(ItemStack stack)
	{
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();

		for(Iterator<Map.Entry<ItemStack, ItemStack>> entries = recipes.entrySet().iterator(); entries.hasNext(); )
		{
			Map.Entry<ItemStack, ItemStack> entry = entries.next();
			ItemStack result = entry.getValue();
			if(ItemStack.areItemStacksEqual(result, stack))
			{
				entries.remove();
			}
		}
	}

}
