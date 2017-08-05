package halestormxv.eAngelus.main.proxy;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityScale;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityStorage;
import halestormxv.eAngelus.config.eAngelusConfig;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.main.world.E_AngWorldGen;
import halestormxv.eAngelus.main.world.Structures.generateKnightAlter;
import net.minecraft.item.Item;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event)
	{
		CapabilityManager.INSTANCE.register(IMorality.class, new moralityStorage(), moralityScale.class);
	}
	
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new E_AngWorldGen(), 0);
		//GameRegistry.registerWorldGenerator(new generateKnightAlter(), 1);
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{

	}
	
	public void registerItemSided(Item item)
	{
		
	}
	
	public void registerModelBakeryStuff()
	{
		
	}

}
