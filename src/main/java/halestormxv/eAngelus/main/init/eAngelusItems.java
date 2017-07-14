package halestormxv.eAngelus.main.init;

import halestormxv.eAngelus.items.*;
import halestormxv.eAngelus.items.cards.O_card_Strength;
import halestormxv.eAngelus.items.records.eAngelus_Records;
import halestormxv.eAngelus.items.records.rec_Mavis;
import halestormxv.eAngelus.items.records.rec_Motomiya;
import halestormxv.eAngelus.main.EACreativeTab;
import halestormxv.eAngelus.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class eAngelusItems 
{
	public static Item angelic_ingot;
	public static Item demonic_ingot;
	public static Item topazStone;
	public static Item angelicDust;
	public static Item mystalDust;
	public static Item azuriteStone;
	public static Item serpentineStone;
	public static Item scryingOrb;
	public static Item cFortitudo;

	//Tools
	public static Item serpentinePick;
	public static Item serpentineSpade;
	public static Item serpentineAxe;
	public static Item serpentineHoe;
	public static Item serpentineMulti;

	//Weapons
	public static Item serpentineSword;
	//public static Item serpentineShield;

	//Cards
	public static Item eaCardO;

	//Rare Drops
	public static Item tarotPaper;
	public static Item fireSword;
	public static Item recordMavis;
	public static Item recordMotomiya;

	//Consumables
	public static Item jolluna;
	public static Item esicuri;
	public static Item virtuefruit;
	public static Item sinfulfruit;

	//Materials
	public static ToolMaterial Serpentine = EnumHelper.addToolMaterial("Serpentine", 4, 1800, 10.0F, 9.0F, 25);
	public static ToolMaterial SpecialItems = EnumHelper.addToolMaterial("SpecialItems", 4, 2000, 11.0F, 10.6F, 25);

	public static void initItems() //illustrates both ways to register an item.
	{	
		//Cards
		cFortitudo = registerItem(new O_card_Strength("cFortitudo"), "cFortitudo");

		//Misc Items
		scryingOrb = registerItem(new ModItemScryingOrb("scryingOrb"), "scryingOrb");

		//Rare Drops
		tarotPaper = registerItem(new EAItem("tarotPaper"), "tarotPaper");
		fireSword = registerItem(new ModItemFlameSword("fireSword", SpecialItems), "fireSword");
		recordMavis = new rec_Mavis();
		recordMotomiya = new rec_Motomiya();

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

		//Consumables
		jolluna = new ModItemFood("jolluna", 6, 0.9f, false, new PotionEffect(Potion.getPotionById(10), 200, 3));
		esicuri = new ModItemFood("esicuri", 4, 0.6f, false);
		virtuefruit = new ModItemMoralityFoods("virtuefruit", 4, 0.6f, false);;
		sinfulfruit = new ModItemMoralityFoods("sinfulfruit", 4, 0.6f, false);;

		registerItem(jolluna, "jolluna");
		registerItem(esicuri, "esicuri");
		registerItem(virtuefruit, "virtuefruit");
		registerItem(sinfulfruit, "sinfulfruit");
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
		registerRender(fireSword);
		registerRender(recordMavis);
		registerRender(recordMotomiya);

		//Items
		registerRender(angelic_ingot);
		registerRender(demonic_ingot);
		registerRender(topazStone);
		registerRender(angelicDust);
		registerRender(mystalDust);
		registerRender(azuriteStone);
		registerRender(serpentineStone);
		registerRender(scryingOrb);
		registerRender(cFortitudo);

		//Tools
		registerRender(serpentinePick);
		registerRender(serpentineSpade);
		registerRender(serpentineAxe);
		registerRender(serpentineHoe);
		registerRender(serpentineMulti);	

		//Weapons
		registerRender(serpentineSword);
		//registerRender(serpentineShield);

		//Consumables
		registerRender(jolluna);
		registerRender(esicuri);
		registerRender(virtuefruit);
		registerRender(sinfulfruit);
	}

	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,  0, 
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerRender(Item item, int meta, String fileName)
	{
		GameRegistry.register(item);

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
