package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_EnumHandler.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Blaze on 7/19/2017.
 */
public class ModItemTalismans extends Item
{
    public ModItemTalismans(String unlocalizedName)
    {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < ModTalismans.values().length; i++)
        {
            items.add(new ItemStack(item, 1, i));

        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        for (int i = 0; i < ModTalismans.values().length; i++)
        {
            if(stack.getItemDamage() == i)
            {
                return this.getUnlocalizedName() + "." + ModTalismans.values()[i].getName();
            }
            else
            {
                    continue;
            }
        }
        return this.getUnlocalizedName() + "." + ModTalismans.SIN_TALISMAN.getName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        if (stack.getItemDamage() == 0)
        {
            tooltip.add("");
            tooltip.add("\u00A76" + "This talisman contains the Power");
            tooltip.add("\u00A76" + "of Sin. Enemies slain with this");
            tooltip.add("\u00A76" + "in the inventory have a chance to");
            tooltip.add("\u00A76" + "increase the level of Sin.");
            tooltip.add("");
        }
        else if (stack.getItemDamage() == 1)
        {
            tooltip.add("");
            tooltip.add("\u00A76" + "This talisman contains the Power");
            tooltip.add("\u00A76" + "of Virtue. Enemies slain with this");
            tooltip.add("\u00A76" + "in the inventory have a chance to");
            tooltip.add("\u00A76" + "increase the level of Virtue.");
            tooltip.add("");
        }
    }

    /*@Override
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
    }*/
}
