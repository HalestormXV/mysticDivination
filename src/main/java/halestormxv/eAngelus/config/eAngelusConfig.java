package halestormxv.eAngelus.config;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blaze on 7/15/2017.
 */
public class eAngelusConfig
{
    private static Configuration config = null;

    public static final String CATEGORY_NAME_ITEMS = "Items";
    public static final String CATEGORY_NAME_MORALITYCOSTS = "Card Costs";

    //Cooldowns
    public static int scryingOrbCooldown;
    public static int witherCardCooldown;
    public static int soulChargeReq;

    //Morality Costs
    public static int moralityCost_ResistanceCard;
    public static int moralityCost_StrengthCard;
    public static int moralityCost_WitherCard;
    public static int moralityCost_AbsorbCard;

    //Reagent Costs
    public static int reagentCost_SpeedCard;
    public static int reagentCost_TimeCard;
    public static int reagentCost_ScryingOrb;
    public static int reagentCost_HasteCard;

    public static void preInit()
    {
        File configFile = new File(Loader.instance().getConfigDir(), "MysticDivination.cfg");
        config = new Configuration(configFile);
        syncFromFiles();
    }

    public static Configuration getConfig()
    {
        return config;
    }

    public static void clientPreInit()
    {
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
    }

    public static void syncFromFiles()
    {
        syncConfig(true, true);
    }

    public static void syncFromGui()
    {
        syncConfig(false, true);
    }

    public static void syncFromFields()
    {
        syncConfig(false, false);
    }

    private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig)
    {
        if (loadFromConfigFile)
            config.load();

        //Items Category
        Property propertyScryingOrbCooldown = config.get(CATEGORY_NAME_ITEMS, "Scrying Orb Cooldown", 3600);
        propertyScryingOrbCooldown.setLanguageKey("gui.config.items.scryingorb_cooldown.name");
        propertyScryingOrbCooldown.setComment("gui.config.items.scryingorb_cooldown.comment");
        propertyScryingOrbCooldown.setMinValue(1200);
        propertyScryingOrbCooldown.setMaxValue(12000);

        Property propWitherCardCooldown = config.get(CATEGORY_NAME_ITEMS, "Wither Card Cooldown", 2400);
        propWitherCardCooldown.setLanguageKey("gui.config.items.wither_card_cooldown.name");
        propWitherCardCooldown.setComment("gui.config.items.wither_card_cooldown.comment");
        propWitherCardCooldown.setMinValue(1200);
        propWitherCardCooldown.setMaxValue(12000);

        //Mystal Dust Requirements
        //===========================SCRYING ORB===========================\\
        Property propertyScryingOrbCost = config.get(CATEGORY_NAME_ITEMS, "Scrying Orb: Mystal Dust Requirement", 4);
        propertyScryingOrbCost.setLanguageKey("gui.config.items.scryingorb_cost.name");
        propertyScryingOrbCost.setComment("gui.config.items.scryingorb_cost.comment");
        //===========================ORDER: TEMPUS===========================\\
        Property propCard_Time = config.get(CATEGORY_NAME_ITEMS, "ORDER: Tempus - Mystal Dust Requirement", 4);
        propCard_Time.setLanguageKey("gui.config.items.time_card_cost.name");
        propCard_Time.setComment("gui.config.items.time_card_cost.comment");
        //===========================ORDER: FESTINA===========================\\
        Property propCard_Haste = config.get(CATEGORY_NAME_ITEMS, "ORDER: Festina - Mystal Dust Requirement", 3);
        propCard_Haste.setLanguageKey("gui.config.items.haste_card_cost.name");
        propCard_Haste.setComment("gui.config.items.haste_card_cost.comment");



        //Morality Cost Values
        //===========================STRENGTH CARD===========================\\
        Property propCard_Strength = config.get(CATEGORY_NAME_MORALITYCOSTS, "Morality Level Requirement: Strength", -4);
        propCard_Strength.setLanguageKey("gui.config.items.strength_card_cost.name");
        propCard_Strength.setComment("gui.config.items.strength_card_cost.comment");
        //===========================RESISTANCE CARD===========================\\
        Property propCard_Resistance = config.get(CATEGORY_NAME_MORALITYCOSTS, "Morality Level Requirement: Resistance", 4);
        propCard_Resistance.setLanguageKey("gui.config.items.resistance_card_cost.name");
        propCard_Resistance.setComment("gui.config.items.resistance_card_cost.comment");
        //===========================SPEED CARD===========================\\
        Property propCard_Speed = config.get(CATEGORY_NAME_MORALITYCOSTS, "Morality Level Requirement: Speed", 2);
        propCard_Speed.setLanguageKey("gui.config.items.speed_card_cost.name");
        propCard_Speed.setComment("gui.config.items.speed_card_cost.comment");
        //===========================WITHER CARD===========================\\
        Property propWither_Card = config.get(CATEGORY_NAME_MORALITYCOSTS, "Morality Level Requirement: Wither", -6);
        propWither_Card.setLanguageKey("gui.config.items.wither_card_cost.name");
        propWither_Card.setComment("gui.config.items.wither_card_cost.comment");
        //===========================ABSORB CARD===========================\\
        Property propAbsorb_Card = config.get(CATEGORY_NAME_MORALITYCOSTS, "Morality Level Requirement: Absorption", 6);
        propAbsorb_Card.setLanguageKey("gui.config.items.absorb_card_cost.name");
        propAbsorb_Card.setComment("gui.config.items.absorb_card_cost.comment");

        //Misc
        Property propSoulChargeCost = config.get(CATEGORY_NAME_ITEMS, "Soulcharge Conversion Cost", 50);
        propSoulChargeCost.setLanguageKey("gui.config.items.soul_charge_cost.name");
        propSoulChargeCost.setComment("gui.config.items.soul_charge_cost.comment");

        //DONT FORGET TOT ADD TO THE ARRAY!
        List<String> propertyOrderItems = new ArrayList<String>();
        propertyOrderItems.add(propertyScryingOrbCooldown.getName());
        propertyOrderItems.add(propertyScryingOrbCost.getName());
        propertyOrderItems.add(propSoulChargeCost.getName());
        //Morality Cards
        propertyOrderItems.add(propWitherCardCooldown.getName());
        propertyOrderItems.add(propCard_Strength.getName());
        propertyOrderItems.add(propCard_Resistance.getName());
        propertyOrderItems.add(propWither_Card.getName());
        propertyOrderItems.add(propAbsorb_Card.getName());
        //Neutral Cards
        propertyOrderItems.add(propCard_Speed.getName());
        propertyOrderItems.add(propCard_Time.getName());
        propertyOrderItems.add(propCard_Haste.getName());


        config.setCategoryPropertyOrder(CATEGORY_NAME_ITEMS, propertyOrderItems);
        config.setCategoryPropertyOrder(CATEGORY_NAME_MORALITYCOSTS, propertyOrderItems);

        if (readFieldsFromConfig)
        {
            //Cooldown Default Values Setup
            scryingOrbCooldown = propertyScryingOrbCooldown.getInt();
            witherCardCooldown = propWitherCardCooldown.getInt();
            soulChargeReq = propSoulChargeCost.getInt();
            //Morality Default Values Setup
            moralityCost_StrengthCard = propCard_Strength.getInt();
            moralityCost_ResistanceCard = propCard_Resistance.getInt();
            moralityCost_WitherCard = propWither_Card.getInt();
            moralityCost_AbsorbCard = propAbsorb_Card.getInt();
            //Reagent Costs Default Values Setup
            reagentCost_ScryingOrb = propertyScryingOrbCost.getInt();
            reagentCost_SpeedCard = propCard_Speed.getInt();
            reagentCost_TimeCard = propCard_Time.getInt();
            reagentCost_HasteCard = propCard_Haste.getInt();
        }

        //Cooldowns Setup
        propertyScryingOrbCooldown.set(scryingOrbCooldown);
        propertyScryingOrbCost.set(reagentCost_ScryingOrb);
        propWitherCardCooldown.set(witherCardCooldown);
        //Morality Costs Setup
        propCard_Strength.set(moralityCost_StrengthCard);
        propCard_Resistance.set(moralityCost_ResistanceCard);
        propWither_Card.set(moralityCost_WitherCard);
        //Reagent Costs Setup
        propCard_Speed.set(reagentCost_SpeedCard);
        propCard_Time.set(reagentCost_TimeCard);
        propCard_Haste.set(reagentCost_HasteCard);
        //Misc
        propSoulChargeCost.set(soulChargeReq);

        if (config.hasChanged())
            config.save();
    }

    public static class ConfigEventHandler
    {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if( event.getModID().equals(Reference.MODID))
            {
                syncFromGui();
            }
        }
    }
}
