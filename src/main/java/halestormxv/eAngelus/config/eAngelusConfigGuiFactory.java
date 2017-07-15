package halestormxv.eAngelus.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

/**
 * The in game view of the config file
 * @author CJMinecraft
 *
 */
public class eAngelusConfigGuiFactory implements IModGuiFactory
{

    /**
     * Used to initialize values from the user's minecraft instance
     * We don't use this
     */
    @Override
    public void initialize(Minecraft minecraftInstance) {
    }

    /**
     * The actual class which is the gui
     */
    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return eaConfigClass.class;
    }

    /**
     * Ggets the runtime gui categories which change in game
     */
    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    /**
     * Used to change the colour of the properties
     */
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    /**
     * The gui for the config
     * @author CJMinecraft
     *
     */
    public static class eaConfigClass extends GuiConfig {

        /**
         * Settting up the screen
         * @param parentScreen The prior screen
         */
        public eaConfigClass(GuiScreen parentScreen) {
            super(parentScreen, getConfigElements(), Reference.MODID, false, false, I18n.format("gui.config.main_title"));
        }

        /**
         * Get all of the different categories
         * @return a list of the different categories
         */
        private static List<IConfigElement> getConfigElements() {
            List<IConfigElement> list = new ArrayList<IConfigElement>();
            list.add(new DummyCategoryElement(I18n.format("gui.config.category.items"), "gui.config.category.items", CategoryEntryItems.class)); //Add another one of these for any other categories
            list.add(new DummyCategoryElement(I18n.format("gui.config.category.moralitylevels"), "gui.config.category.moralitylevels", CategoryEntryMoralityLevels.class)); //Add another one of these for any other categories
            return list;
        }

        /**
         * The category for the items
         * @author CJMinecraft
         *
         */
        public static class CategoryEntryItems extends CategoryEntry {

            /**
             * Default constructor
             */
            public CategoryEntryItems(GuiConfig owningScreen, GuiConfigEntries owningEntryList,
                                       IConfigElement configElement) {
                super(owningScreen, owningEntryList, configElement);
            }


            /**
             * Puts all of the properties on the screen from the category
             */
            @Override
            protected GuiScreen buildChildScreen() {
                Configuration config = eAngelusConfig.getConfig();
                ConfigElement categoryItems = new ConfigElement(config.getCategory(eAngelusConfig.CATEGORY_NAME_ITEMS));
                List<IConfigElement> propertiesOnScreen = categoryItems.getChildElements();
                String windowTitle = I18n.format("gui.config.category.items");
                return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
            }

        }

        public static class CategoryEntryMoralityLevels extends CategoryEntry {

            /**
             * Default constructor
             */
            public CategoryEntryMoralityLevels(GuiConfig owningScreen, GuiConfigEntries owningEntryList,
                                      IConfigElement configElement) {
                super(owningScreen, owningEntryList, configElement);
            }


            /**
             * Puts all of the properties on the screen from the category
             */
            @Override
            protected GuiScreen buildChildScreen() {
                Configuration config = eAngelusConfig.getConfig();
                ConfigElement categoryItems = new ConfigElement(config.getCategory(eAngelusConfig.CATEGORY_NAME_MORALITYCOSTS));
                List<IConfigElement> propertiesOnScreen = categoryItems.getChildElements();
                String windowTitle = I18n.format("gui.config.category.moralitycosts");
                return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
            }

        }

    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new eaConfigClass(parentScreen);
    }

}