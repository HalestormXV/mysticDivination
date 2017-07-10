package halestormxv.eAngelus.main.proxy;

import halestormxv.eAngelus.achievements.EA_Achievements;
import halestormxv.eAngelus.crafting.EARecipes;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		EARecipes.initRecipes();
		EA_Achievements.AchievementRegistry();
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		eAngelusItems.registerRenders();
		eAngelusBlocks.registerRenders();
		registerModelBakeryStuff();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);	
	}
	
	@Override
	public void registerModelBakeryStuff() 
	{
		//for (int i = 0; i < eAngelusCards.O_cardNames.length; ++i)
		//{
		//ModelBakery.registerItemVariants(eAngelusItems.eaCardO, new ResourceLocation("eangel:"+eAngelusCards.O_cardNames[i]));
		//}
	}
		
}
