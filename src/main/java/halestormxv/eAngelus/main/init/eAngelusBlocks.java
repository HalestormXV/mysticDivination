package halestormxv.eAngelus.main.init;

import halestormxv.eAngelus.blocks.*;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.Utils;
import halestormxv.eAngelus.network.packets.PacketBase;
import halestormxv.eAngelus.tileentity.TileEntityDualFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class eAngelusBlocks 
{
	public static AngelicBlock angelic_block;
	public static DemonicBlock demonic_block;
	public static AngelicOre angelicOre;
	public static DemonicOre demonicOre;
	public static MystalCite mystalCite;
	public static AzureiteOre azureite_Ore;
	public static SerpentineOre serpentine_Ore;
	public static ObsidianIronTable oTable;
	public static alter_Chariot alter_chariot;
	public static alter_Knight alter_knight;
	public static alter_Strength alter_strength;
	public static Block dual_furnace;

	//Gems
	public static TopazOre topazOre;
	
	
	
	public static void initBlocks()
	{
		//Blocks
		alter_chariot = new alter_Chariot();
		alter_knight = new alter_Knight();
		alter_strength = new alter_Strength();
		angelic_block = new AngelicBlock();
		angelic_block.setUnlocalizedName("angelic_block");
		angelic_block.setCreativeTab(Reference.eaCreativeTab);
		demonic_block = new DemonicBlock();
		oTable = new ObsidianIronTable("oTable", Material.IRON);

		//Ores
		angelicOre = new AngelicOre();
		demonicOre = new DemonicOre();
		mystalCite = new MystalCite();
		azureite_Ore = new AzureiteOre();
		serpentine_Ore = new SerpentineOre();
		
		//Gems
		topazOre = new TopazOre();



	}
	
	public static void registerRenders()
	{
		//Blocks
		registerRender(angelic_block);
		registerRender(demonic_block);
		registerRender(alter_chariot);
		registerRender(alter_knight);
		registerRender(alter_strength);
		
		//Ores
		registerRender(angelicOre);
		registerRender(demonicOre);
		registerRender(mystalCite);
		registerRender(azureite_Ore);
		registerRender(serpentine_Ore);
		registerRender(oTable);
		registerRender(dual_furnace);
		
		//Gems
		registerRender(topazOre);
	}
	
	public static void registerRender(Block block)
	{
		if(block.getRegistryName() != null) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}else{
			Utils.getLogger().error("You had a null registration of something.");}
	}



	//registerBlock Start\\
	public static Block registerBlock(Block block, String name)
	{
		return registerBlock(block, name, null);
	}
	
	public static Block registerBlock(Block block, String name, CreativeTabs tab)
	{
		GameRegistry.register(block, new ResourceLocation(Reference.MODID, name));
		return block;
	}
	//registerBlock End\\


	//===================================ChampionAsh Methods===============================\\
	public static void ashInit()
	{
		dual_furnace = new DualFurance();
	}

	public static void ashRegister()
	{
		ashRegisterBlock(dual_furnace);
		GameRegistry.registerTileEntity(TileEntityDualFurnace.class, "dual_furnace");
	}

	private static void ashRegisterBlock(Block block)
	{
		GameRegistry.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}

	public static void ashRegisterRenders()
	{
		ashRegisterRender(dual_furnace);
	}

	public static void ashRegisterRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}


}
