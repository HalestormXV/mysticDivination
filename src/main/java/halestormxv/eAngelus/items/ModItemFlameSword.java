package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Blaze on 7/6/2017.
 */

public class ModItemFlameSword extends ItemSword
{

    public ModItemFlameSword(String unlocalizedName, ToolMaterial material)
    {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(Reference.eaCreativeTab);
        this.setMaxStackSize(1);
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return EnumRarity.EPIC;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
            tooltip.add("");
            tooltip.add("\u00A76" + "You fall into my arms. You are the");
            tooltip.add("\u00A76" + "good gift of destruction's path.");
            tooltip.add("\u00A76" + "When life sickens more than disease.");
            tooltip.add("\u00A76" + "Flames shall guide the way...");
    }
}