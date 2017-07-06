package halestormxv.eAngelus.crafting;

import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EARecipes 
{
	public static void initRecipes()
	{
		//Shaped Crafting
		GameRegistry.addRecipe(new ItemStack(eAngelusBlocks.angelic_block),
				new Object []
						{
								"###",
								"###",
								"###",
								'#', eAngelusItems.angelic_ingot				
						});
		
		
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentineAxe),
				"*SS", 
				"*sS", 
				"*s*", 
				
				'S', eAngelusItems.serpentineStone, 's', Items.STICK);
		
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentineSpade),
				"*S*", 
				"*s*", 
				"*s*", 
				
				'S', eAngelusItems.serpentineStone, 's', Items.STICK);
		
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentinePick),
				"SSS", 
				"*s*", 
				"*s*", 
				
				'S', eAngelusItems.serpentineStone, 's', Items.STICK);
		
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentineHoe),
				"*SS", 
				"*s*", 
				"*s*", 
				
				'S', eAngelusItems.serpentineStone, 's', Items.STICK);
		
		GameRegistry.addRecipe(new ItemStack(eAngelusItems.serpentineMulti),
				"PAS", 
				"*sH", 
				"*s*", 
				
				'S', eAngelusItems.serpentineSpade, 's', Items.STICK, 'P', eAngelusItems.serpentinePick, 
				'A', eAngelusItems.serpentineAxe, 'H', eAngelusItems.serpentineHoe);
		
		
		//Shapeless Crafting
		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.angelic_ingot, 9), 
				new Object [] {
				eAngelusBlocks.angelic_block
				});
		
		
		//Smelting Recipes
		GameRegistry.addSmelting(eAngelusBlocks.angelicOre, new ItemStack(eAngelusItems.angelic_ingot), 13.0F);
		GameRegistry.addSmelting(eAngelusBlocks.demonicOre, new ItemStack(eAngelusItems.demonic_ingot), 13.0F);
	}

}
