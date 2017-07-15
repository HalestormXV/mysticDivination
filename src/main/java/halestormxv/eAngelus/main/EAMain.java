package halestormxv.eAngelus.main;

import halestormxv.eAngelus.config.eAngelusConfig;
import halestormxv.eAngelus.main.handlers.*;
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
		ModSounds_Records.init();
		EA_SoundHandler.init();
		logger = event.getModLog();
		Utils.getLogger().info("Pre Initialize");
		eAngelusConfig.preInit();
		this.proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Utils.getLogger().info("Initialize");
		this.proxy.init(event);
		GameRegistry.registerFuelHandler(new EA_FuelHandler());
		eAngelusPacketHandler.init();
		MinecraftForge.EVENT_BUS.register(new EA_KeyInputHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		Utils.getLogger().info("Post Initialize");
		MinecraftForge.EVENT_BUS.register(new EA_EventHandler());
		MinecraftForge.EVENT_BUS.register(new EA_CapabilityHandler());
		this.proxy.postInit(event);
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		
	}
}
