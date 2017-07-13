package halestormxv.eAngelus.main.proxy;

import halestormxv.eAngelus.achievements.EA_Achievements;
import halestormxv.eAngelus.crafting.EARecipes;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy
{
	private final Minecraft MINECRAFT = Minecraft.getMinecraft();
	public static KeyBinding[] keyBindings;

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
		keyBindings = new KeyBinding[1];
		keyBindings[0] = new KeyBinding("eangelus.getmorality.desc", Keyboard.KEY_M, "eangelus.keymapping.category");
		for (int i = 0; i < ClientProxy.keyBindings.length; ++i)
		{
			ClientRegistry.registerKeyBinding(ClientProxy.keyBindings[i]);
		}
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

	public IThreadListener getThreadListener(final MessageContext context) {
		if (context.side.isClient()) {
			return MINECRAFT;
		} else {
			return context.getServerHandler().playerEntity.mcServer;
		}
	}

	public EntityPlayer getPlayer(final MessageContext context) {
		if (context.side.isClient()) {
			return MINECRAFT.player;
		} else {
			return context.getServerHandler().playerEntity;
		}
	}
}
