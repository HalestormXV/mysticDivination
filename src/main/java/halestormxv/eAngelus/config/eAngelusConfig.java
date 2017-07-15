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
    public static final String CATEGORY_NAME_MORALITYCOSTS = "Morality Costs";
    public static int scryingOrbCooldown;
    public static int moralityCost_ResistanceCard;
    public static int moralityCost_StrengthCard;

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
        Property propertyScryingOrbCooldown = config.get(CATEGORY_NAME_ITEMS, "property_cooldown", 3600);
        propertyScryingOrbCooldown.setLanguageKey("gui.config.items.scryingorb_cooldown.name");
        propertyScryingOrbCooldown.setComment("gui.config.items.scryingorb_cooldown.comment");
        propertyScryingOrbCooldown.setMinValue(1200);
        propertyScryingOrbCooldown.setMaxValue(12000);

        //Morality Cost Values
        //===========================STRENGTH CARD===========================\\
        Property propCard_Strength = config.get(CATEGORY_NAME_MORALITYCOSTS, "morality_cooldowns_strength_card", -4);
        propCard_Strength.setLanguageKey("gui.config.items.strength_card_cost.name");
        propCard_Strength.setComment("gui.config.items.strength_card_cost.comment");
        //===========================RESITANCE CARD===========================\\
        Property propCard_Resistance = config.get(CATEGORY_NAME_MORALITYCOSTS, "morality_cooldowns_resistance_card", 4);
        propCard_Resistance.setLanguageKey("gui.config.items.resistance_card_cost.name");
        propCard_Resistance.setComment("gui.config.items.resistance_card_cost.comment");



        //DONT FORGET TOT ADD TO THE ARRAY!
        List<String> propertyOrderItems = new ArrayList<String>();
        propertyOrderItems.add(propertyScryingOrbCooldown.getName());
        propertyOrderItems.add(propCard_Strength.getName());
        propertyOrderItems.add(propCard_Resistance.getName());

        config.setCategoryPropertyOrder(CATEGORY_NAME_ITEMS, propertyOrderItems);
        config.setCategoryPropertyOrder(CATEGORY_NAME_MORALITYCOSTS, propertyOrderItems);

        if (readFieldsFromConfig)
        {
            scryingOrbCooldown = propertyScryingOrbCooldown.getInt();
            moralityCost_StrengthCard = propCard_Strength.getInt();
            moralityCost_ResistanceCard = propCard_Resistance.getInt();
        }

        propertyScryingOrbCooldown.set(scryingOrbCooldown);
        propCard_Strength.set(moralityCost_StrengthCard);
        propCard_Resistance.set(moralityCost_ResistanceCard);

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
