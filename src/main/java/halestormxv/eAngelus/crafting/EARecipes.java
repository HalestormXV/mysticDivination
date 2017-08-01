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

		GameRegistry.addRecipe(new ItemStack(eAngelusBlocks.demonic_block),
				new Object []
						{
								"###",
								"###",
								"###",
								'#', eAngelusItems.demonic_ingot
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

		GameRegistry.addRecipe(new ItemStack(eAngelusBlocks.alter_chariot),
				"*t*",
				"DFA",
				"***",

				't', eAngelusItems.topazStone, 'D', eAngelusBlocks.demonic_block, 'F', Items.FEATHER,
				'A', eAngelusBlocks.angelic_block);

		GameRegistry.addRecipe(new ItemStack(eAngelusBlocks.alter_knight),
				"*a*",
				"DFA",
				"***",

				'a', eAngelusItems.azuriteStone, 'D', eAngelusBlocks.demonic_block, 'F', Items.DIAMOND_CHESTPLATE,
				'A', eAngelusBlocks.angelic_block);

		GameRegistry.addRecipe(new ItemStack(eAngelusBlocks.alter_strength),
				"*s*",
				"DFA",
				"***",

				's', eAngelusItems.serpentineStone, 'D', eAngelusBlocks.demonic_block, 'F', Items.DIAMOND_SWORD,
				'A', eAngelusBlocks.angelic_block);
		
		
		//Shapeless Crafting
		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.angelic_ingot, 9), 
				new Object [] {
				eAngelusBlocks.angelic_block
				});

		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.demonic_ingot, 9),
				new Object [] {
						eAngelusBlocks.demonic_block
				});

		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.scryingOrb, 1),
			new Object [] {
					eAngelusItems.demonic_ingot, eAngelusItems.angelic_ingot, eAngelusItems.tarotPaper, eAngelusItems.azuriteStone
			});

		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.cFortitudo, 1),
				new Object [] {
						eAngelusItems.tarotPaper, new ItemStack(eAngelusItems.essence, 1, 3),  eAngelusItems.tarotPaper,
						eAngelusItems.azuriteStone, eAngelusItems.mystalDust, eAngelusItems.topazStone
				});

		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.cResistentia, 1),
				new Object [] {
						eAngelusItems.tarotPaper, new ItemStack(eAngelusItems.essence, 1, 2),  eAngelusItems.tarotPaper,
						eAngelusItems.azuriteStone, eAngelusItems.mystalDust, eAngelusItems.topazStone
				});

		GameRegistry.addShapelessRecipe(new ItemStack(eAngelusItems.cVentus, 1),
				new Object [] {
						eAngelusItems.tarotPaper, new ItemStack(eAngelusItems.essence, 1, 1),  eAngelusItems.tarotPaper,
						eAngelusItems.azuriteStone, eAngelusItems.mystalDust, eAngelusItems.topazStone
				});
		
		
		//Smelting Recipes
		GameRegistry.addSmelting(eAngelusBlocks.angelicOre, new ItemStack(eAngelusItems.angelic_ingot), 13.0F);
		GameRegistry.addSmelting(eAngelusBlocks.demonicOre, new ItemStack(eAngelusItems.demonic_ingot), 13.0F);
	}

}
