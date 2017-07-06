package halestormxv.eAngelus.main;

import org.apache.logging.log4j.Logger;

import halestormxv.eAngelus.main.handlers.EA_FuelHandler;
import halestormxv.eAngelus.main.proxy.CommonProxy;
import halestormxv.eAngelus.main.world.E_AngWorldGen;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class EAMain 
{
	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
	public static CommonProxy proxy;
	
	@Instance(Reference.MODID)
	public static EAMain instance;
	
	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		this.proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.proxy.init(event);
		GameRegistry.registerWorldGenerator(new E_AngWorldGen(), 0);
		GameRegistry.registerFuelHandler(new EA_FuelHandler());
		//EARemoveRecipes.removeCraftingRecipes(Items.diamond_sword);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		this.proxy.postInit(event);	
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		
	}
}
