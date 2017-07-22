package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_EnumHandler.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Blaze on 7/19/2017.
 */
public class runeStones extends Item
{
    public runeStones(String unlocalizedName)
    {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < RuneStones.values().length; i++)
        {
            items.add(new ItemStack(item, 1, i));

        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        for (int i = 0; i < RuneStones.values().length; i++)
        {
            if(stack.getItemDamage() == i)
            {
                return this.getUnlocalizedName() + "." + RuneStones.values()[i].getName();
            }
            else
            {
                    continue;
            }
        }
        return this.getUnlocalizedName() + "." + RuneStones.BASE_RUNE.getName();
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        switch(itemStack.getItemDamage())
        {
            case 0:
                return EnumRarity.COMMON;
            case 4:
                return EnumRarity.EPIC;
            default:
                return EnumRarity.UNCOMMON;
        }
    }
}
