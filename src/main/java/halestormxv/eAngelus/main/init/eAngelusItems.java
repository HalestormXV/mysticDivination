package halestormxv.eAngelus.main.init;

import halestormxv.eAngelus.items.AngelicIngot;
import halestormxv.eAngelus.items.EAItem;
import halestormxv.eAngelus.items.ModItemAxe;
import halestormxv.eAngelus.items.ModItemHoe;
import halestormxv.eAngelus.items.ModItemMulti;
import halestormxv.eAngelus.items.ModItemPick;
import halestormxv.eAngelus.items.ModItemSpade;
import halestormxv.eAngelus.items.ModItemSword;
import halestormxv.eAngelus.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import halestormxv.eAngelus.items.cards.O_card_Fire;

public class eAngelusItems 
{
	public static Item angelic_ingot;
	public static Item demonic_ingot;
	public static Item topazStone;
	public static Item angelicDust;
	public static Item mystalDust;
	public static Item azuriteStone;
	public static Item serpentineStone;

	//Tools
	public static Item serpentinePick;
	public static Item serpentineSpade;
	public static Item serpentineAxe;
	public static Item serpentineHoe;
	public static Item serpentineMulti;

	//Weapons
	public static Item serpentineSword;
	public static Item serpentineShield;

	//Cards
	public static Item eaCardO;

	//Rare Drops
	public static Item tarotPaper;

	//Materials
	public static ToolMaterial Serpentine = EnumHelper.addToolMaterial("Serpentine", 4, 1800, 10.0F, 9.0F, 25);

	public static void initItems() //illustrates both ways to register an item.
	{	
		//Cards
		//eaCardO = registerItem(new eAngelusCards("eaCardO"), "eaCardO");

		//Rare Drops
		tarotPaper = registerItem(new EAItem("tarotPaper"), "tarotPaper");

		//Ingots
		angelic_ingot = registerItem(new AngelicIngot(), "angelic_ingot");
		demonic_ingot = registerItem(new Item().setUnlocalizedName("demonic_ingot").setCreativeTab(Reference.eaCreativeTab), "demonic_ingot");
		//Gems
		topazStone = registerItem(new EAItem("topazStone"), "topazStone");
		azuriteStone = registerItem(new EAItem("azuriteStone"), "azuriteStone");
		serpentineStone = registerItem(new EAItem("serpentineStone"), "serpentineStone");

		//Dusts
		angelicDust = registerItem(new EAItem("angelicDust"), "angelicDust");
		mystalDust = registerItem(new EAItem("mystalDust"), "mystalDust");

		//Tools
		serpentineHoe = registerItem(new ModItemHoe("serpentineHoe", Serpentine), "serpentineHoe");
		serpentineSpade = registerItem(new ModItemSpade("serpentineSpade", Serpentine), "serpentineSpade");
		serpentineAxe = registerItem(new ModItemAxe("serpentineAxe", Serpentine), "serpentineAxe");
		serpentinePick = registerItem(new ModItemPick("serpentinePick", Serpentine), "serpentinePick");
		serpentineMulti = registerItem(new ModItemMulti("serpentineMulti", Serpentine), "serpentineMulti");

		//Weapons
		serpentineSword = registerItem(new ModItemSword("serpentineSword", Serpentine), "serpentineSword");
		//serpentineShield = registerItem(new SerpentSword("serpentinePick", Serpentine), "serpentinePick");
	}


	public static void registerRenders()
	{
		//Cards
		//for (int i = 0; i < eAngelusCards.O_cardNames.length; ++i)
		//{
		//	registerRender(eaCardO, i, eAngelusCards.O_cardNames[i]);
		//}
		//

		//Rare Drops
		registerRender(tarotPaper);

		//Items
		registerRender(angelic_ingot);
		registerRender(demonic_ingot);
		registerRender(topazStone);
		registerRender(angelicDust);
		registerRender(mystalDust);
		registerRender(azuriteStone);
		registerRender(serpentineStone);

		//Tools
		registerRender(serpentinePick);
		registerRender(serpentineSpade);
		registerRender(serpentineAxe);
		registerRender(serpentineHoe);
		registerRender(serpentineMulti);	

		//Weapons
		registerRender(serpentineSword);
		//registerRender(serpentineShield);
	}

	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,  0, 
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerRender(Item item, int meta, String fileName)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,  meta, 
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}








	//registerItem Start\\
	public static Item registerItem(Item item, String name)
	{
		return registerItem(item, name, null);
	}

	public static Item registerItem(Item item, String name, CreativeTabs tab)
	{
		GameRegistry.register(item, new ResourceLocation(Reference.MODID, name));
		return item;
	}
	//registerItem End\\
}
