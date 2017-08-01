package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_EnumHandler.CardEssences;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
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
public class CardEssence extends Item
{
    public CardEssence(String unlocalizedName)
    {
        this.setUnlocalizedName(unlocalizedName);
        this.setMaxStackSize(1);
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
        return this.getUnlocalizedName() + "." + CardEssences.BLANK.getName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        switch(stack.getMetadata())
        {
            case 0:
                if (stack.getTagCompound() != null)
                {
                    NBTTagCompound nbt = stack.getTagCompound();
                    int killCounter = nbt.getInteger("killCount");
                    tooltip.add("");
                    tooltip.add("\u00A73" + "Soul Charge: "+ killCounter);
                }

            default:
        }
    }
}
