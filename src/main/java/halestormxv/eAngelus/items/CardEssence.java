package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_EnumHandler.CardEssences;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Blaze on 7/19/2017.
 */
public class CardEssence extends Item
{
    public CardEssence(String unlocalizedName)
    {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < CardEssences.values().length; i++)
        {
            items.add(new ItemStack(item, 1, i));

        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        for (int i = 0; i < CardEssences.values().length; i++)
        {
            if(stack.getItemDamage() == i)
            {
                return this.getUnlocalizedName() + "." + CardEssences.values()[i].getName();
            }
            else
            {
                    continue;
            }
        }
        return this.getUnlocalizedName() + "." + CardEssences.CHARIOT.getName();
    }
}
