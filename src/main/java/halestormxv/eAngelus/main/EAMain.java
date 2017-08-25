package halestormxv.eAngelus.main;

import halestormxv.eAngelus.config.eAngelusConfig;
import halestormxv.eAngelus.main.handlers.*;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.main.proxy.ClientProxy;
import halestormxv.eAngelus.mobs.MobRegistry;
import halestormxv.eAngelus.network.eAngelusPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

import halestormxv.eAngelus.main.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY)

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
		eAngelusConfig.preInit();
		ModSounds_Records.init();
		MobRegistry.register();
		EA_SoundHandler.init();
		eAngelusItems.initItems();
		eAngelusBlocks.initBlocks();
		Utils.getLogger().info("Pre Initialize");
		logger = event.getModLog();
		this.proxy.preInit(event);

	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Utils.getLogger().info("Initialize");
		this.proxy.init(event);
		//GameRegistry.registerFuelHandler(new EA_FuelHandler());
		eAngelusPacketHandler.init();
		MinecraftForge.EVENT_BUS.register(new EA_KeyInputHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		Utils.getLogger().info("Post Initialize");
		MinecraftForge.EVENT_BUS.register(new EA_EventHandler());
		MinecraftForge.EVENT_BUS.register(new EA_CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new EA_ChestHandler());
		this.proxy.postInit(event);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		
	}
}
