package halestormxv.eAngelus.achievements;

import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EA_AchievementsEvents 
{
	@SubscribeEvent
	public void onGetAngelicOre(PlayerEvent.ItemPickupEvent e)
	{
		if (e.pickedUp.getEntityItem().isItemEqual(new ItemStack(Item.getItemFromBlock(eAngelusBlocks.angelicOre))))
		{
			e.player.addStat(EA_Achievements.AchievementGetAngelicOre, 1);
		}
	}
	
	@SubscribeEvent
	public void onGetDemonicOre(PlayerEvent.ItemPickupEvent e)
	{
		if (e.pickedUp.getEntityItem().isItemEqual(new ItemStack(Item.getItemFromBlock(eAngelusBlocks.demonicOre))))
		{
			e.player.addStat(EA_Achievements.AchievementGetDemonicOre, 1);
		}
	}
	
	@SubscribeEvent
	public void onGetAngelicIngot(PlayerEvent.ItemSmeltedEvent e)
	{
		if (e.smelting.getItem().equals(eAngelusItems.angelic_ingot))
		{
			e.player.addStat(EA_Achievements.AchievementMakeAngelicIngot, 1);
		}
	}

	@SubscribeEvent
	public void craftSinfulFruit(PlayerEvent.ItemCraftedEvent e)
	{
		if (e.crafting.isItemEqual(new ItemStack(eAngelusItems.sinfulfruit)))
		{
			e.player.addStat(EA_Achievements.AchievementMakeSinfulFruit, 1);
		}
	}

	@SubscribeEvent
	public void craftVirtueFruit(PlayerEvent.ItemCraftedEvent e)
	{
		if (e.crafting.isItemEqual(new ItemStack(eAngelusItems.virtuefruit)))
		{
			e.player.addStat(EA_Achievements.AchievementMakeVirtueFruit, 1);
		}
	}

}
