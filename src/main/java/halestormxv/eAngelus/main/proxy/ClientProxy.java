package halestormxv.eAngelus.main.proxy;

import halestormxv.eAngelus.achievements.EA_Achievements;
import halestormxv.eAngelus.crafting.EARecipes;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_EnumHandler;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.mobs.entitys.EntityCelestialBolt;
import halestormxv.eAngelus.mobs.entitys.EntityCreeperPrime;
import halestormxv.eAngelus.mobs.entitys.EntityPhantom;
import halestormxv.eAngelus.mobs.renders.RenderEnergyBall;
import halestormxv.eAngelus.mobs.renders.RenderCreeperPrime;
import halestormxv.eAngelus.mobs.renders.RenderPhantom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
		EA_Achievements.AchievementRegistry();
		RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, RenderPhantom.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCreeperPrime.class, RenderCreeperPrime.FACTORY);
		registerEntityRenderer(EntityCelestialBolt.class, RenderEnergyBall.class);
		eAngelusItems.registerRenders();
		eAngelusBlocks.registerRenders();
		//registerModelBakeryStuff();
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		eAngelusBlocks.ashRegisterRenders();
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
		ModelBakery.registerItemVariants(eAngelusItems.essence,
				new ResourceLocation(Reference.MODID, "essence_chariot"),
				new ResourceLocation(Reference.MODID,"essence_knight"),
				new ResourceLocation(Reference.MODID,"essence_strength"));
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

	private static <E extends Entity> void registerEntityRenderer(Class<E> entityClass, Class<? extends Render<E>> renderClass)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory(renderClass));
	}

	private static class EntityRenderFactory<E extends Entity>
			implements IRenderFactory<E>
	{
		private Class<? extends Render<E>> renderClass;

		private EntityRenderFactory(Class<? extends Render<E>> renderClass)
		{
			this.renderClass = renderClass;
		}

		public Render<E> createRenderFor(RenderManager manager)
		{
			Render<E> renderer = null;
			try
			{
				renderer = (Render)this.renderClass.getConstructor(new Class[] { RenderManager.class }).newInstance(new Object[] { manager });
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return renderer;
		}
	}
}
