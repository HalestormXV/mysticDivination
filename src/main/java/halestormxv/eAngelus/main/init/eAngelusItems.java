package halestormxv.eAngelus.main.init;

import com.sun.org.apache.regexp.internal.RE;
import halestormxv.eAngelus.items.*;
import halestormxv.eAngelus.items.cards.*;
import halestormxv.eAngelus.items.records.rec_Kishuu;
import halestormxv.eAngelus.items.records.rec_Mavis;
import halestormxv.eAngelus.items.records.rec_Motomiya;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.Utils;
import halestormxv.eAngelus.main.handlers.EA_EnumHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
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
	public static Item celBolt;
	public static Item essence;
	public static Item runestone;
	public static Item talismans;
	public static Item mysticKeystone;
	public static Item divineKeystoneOP;

	//Tools
	public static Item serpentinePick;
	public static Item serpentineSpade;
	public static Item serpentineAxe;
	public static Item serpentineHoe;
	public static Item serpentineMulti;

	//Weapons
	public static Item serpentineSword;
	public static Item serpentineGunblade;

	//Cards
	public static Item cFortitudo;
	public static Item cResistentia;
	public static Item cVentus;
	public static Item cTempus;
	public static Item cDefluet;
	public static Item cClypeus;
	public static Item cFestina;

	//Rare Drops
	public static Item tarotPaper;
	public static Item fireSword;
	public static Item recordMavis;
	public static Item recordMotomiya;
	public static Item recordKishuu;

	//Consumables
	public static Item jolluna;
	public static Item esicuri;
	public static Item virtuefruit;
	public static Item sinfulfruit;

	//Misc
	public static Item sinEssence;
	public static Item virtueEssence;

	//Materials
	public static ToolMaterial Serpentine = EnumHelper.addToolMaterial("Serpentine", 4, 1800, 10.0F, 7.6F, 25);
	public static ToolMaterial SpecialItems = EnumHelper.addToolMaterial("SpecialItems", 4, 2000, 11.0F, 9.2F, 25);

	public static void initItems() //illustrates both ways to register an item.
	{	
		//Cards
		cFortitudo = registerItem(new S_card_Strength("cFortitudo"), "cFortitudo");
		cResistentia = registerItem(new V_card_Resistance("cResistentia"), "cResistentia");
		cVentus = registerItem(new N_card_Speed("cVentus"), "cVentus");
		cTempus = registerItem(new N_card_Time("cTempus"), "cTempus");
		cDefluet = registerItem(new S_card_Wither("cDefluet"), "cDefluet");
		cClypeus = registerItem(new V_card_Absorb("cClypeus"), "cClypeus");
		cFestina = registerItem(new N_card_Haste("cFestina"), "cFestina");

		//Misc Items
		scryingOrb = registerItem(new ModItemScryingOrb("scryingOrb"), "scryingOrb");
		mysticKeystone = registerItem(new MysticKeystone("mysticKeystone"), "mysticKeystone");
		divineKeystoneOP = registerItem(new DivineKeystoneOP("divineKeystone"), "divineKeystone");
		celBolt = registerItem(new EAItem("celBolt"), "celBolt");
		essence = new CardEssence("essence");
		runestone = new runeStones("runestone");
		talismans = new ModItemTalismans("talisman");

		//Rare Drops
		tarotPaper = registerItem(new EAItem("tarotPaper"), "tarotPaper");
		fireSword = registerItem(new ModItemFlameSword("fireSword", SpecialItems), "fireSword");
		recordMavis = new rec_Mavis();
		recordMotomiya = new rec_Motomiya();
		recordKishuu = new rec_Kishuu();

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
		serpentineGunblade = registerItem(new ModItemsGunblade("serpentineGunblade", Serpentine), "serpentineGunblade");

		//Consumables
		jolluna = new ModItemFood("jolluna", 6, 0.9f, false, new PotionEffect(Potion.getPotionById(10), 200, 3));
		esicuri = new ModItemFood("esicuri", 4, 0.6f, false);
		virtuefruit = new ModItemMoralityFoods("virtuefruit", 4, 0.6f, false);
		sinfulfruit = new ModItemMoralityFoods("sinfulfruit", 4, 0.6f, false);


		//Misc
		sinEssence = registerItem(new EAItem("sinEssence"), "sinEssence");
		virtueEssence = registerItem(new EAItem("virtueEssence"), "virtueEssence");


		registerItem(jolluna, "jolluna");
		registerItem(esicuri, "esicuri");
		registerItem(virtuefruit, "virtuefruit");
		registerItem(sinfulfruit, "sinfulfruit");
		goodRegisterItem(essence);
		goodRegisterItem(runestone);
		goodRegisterItem(talismans);
	}


	public static void registerRenders()
	{
		for (int i = 0; i < EA_EnumHandler.CardEssences.values().length; i++)
		{
			goodRegisterRender(essence, i, Reference.MODID+":essence_" + EA_EnumHandler.CardEssences.values()[i].getName());
		}
		for (int i = 0; i < EA_EnumHandler.RuneStones.values().length; i++)
		{
			goodRegisterRender(runestone, i, Reference.MODID+":runestone_" + EA_EnumHandler.RuneStones.values()[i].getName());
		}
		for (int i = 0; i < EA_EnumHandler.ModTalismans.values().length; i++)
		{
			goodRegisterRender(talismans, i, Reference.MODID+":talisman_" + EA_EnumHandler.ModTalismans.values()[i].getName());
		}
		//Cards
		registerRender(cFortitudo); //Strength Card
		registerRender(cResistentia); //Resistance Card
		registerRender(cVentus); //Speed Card
		registerRender(cTempus); //Time Card
		registerRender(cDefluet); //Wither Card
		registerRender(cClypeus); //Absorb Card
		registerRender(cFestina); //Haste Card

		//Rare Drops
		registerRender(tarotPaper);
		registerRender(fireSword);
		registerRender(recordMavis);
		registerRender(recordMotomiya);
		registerRender(recordKishuu);

		//Items
		registerRender(angelic_ingot);
		registerRender(demonic_ingot);
		registerRender(topazStone);
		registerRender(angelicDust);
		registerRender(mystalDust);
		registerRender(azuriteStone);
		registerRender(serpentineStone);
		registerRender(scryingOrb);
		registerRender(mysticKeystone);
		registerRender(divineKeystoneOP);
		registerRender(celBolt);

		//Tools
		registerRender(serpentinePick);
		registerRender(serpentineSpade);
		registerRender(serpentineAxe);
		registerRender(serpentineHoe);
		registerRender(serpentineMulti);	

		//Weapons
		registerRender(serpentineSword);
		registerRender(serpentineGunblade);

		//Consumables
		registerRender(jolluna);
		registerRender(esicuri);
		registerRender(virtuefruit);
		registerRender(sinfulfruit);

		//Misc
		registerRender(sinEssence);
		registerRender(virtueEssence);
	}

	public static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerRender(Item item, int meta, String fileName)
	{
		//GameRegistry.register(item);
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
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

	public static void goodRegisterRender(Item item, int meta, String fileName)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(fileName), "inventory"));
	}

	public static void goodRegisterItem(Item item)
	{
		item.setCreativeTab(Reference.eaCreativeTab);
		GameRegistry.register(item);
		Utils.getLogger().info("Registered item: " + item.getUnlocalizedName().substring(5));
	}
	//registerItem End\\
}
