package halestormxv.eAngelus.items;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Blaze on 7/14/2017.
 */
public class ModItemMoralityFoods extends ItemFood
{
    private PotionEffect[] effects;

    //Steak = 0.8f Saturation
    public ModItemMoralityFoods(String unlocalizedName, int amount, float saturation, boolean isWolfFood, PotionEffect...potionEffects) {
        super(amount, saturation, isWolfFood);
        this.effects = potionEffects;
        this.setAlwaysEdible();
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(Reference.eaCreativeTab);
    }

    public ModItemMoralityFoods(String unlocalizedName, int amount, boolean isWolfFood, PotionEffect...potionEffects) {
        super(amount, isWolfFood);
        this.effects = potionEffects;
        this.setAlwaysEdible();
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(Reference.eaCreativeTab);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (stack.getItem() == eAngelusItems.virtuefruit)
        {
            IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
            morality.addVirtue(6);
            ChatUtil.sendNoSpam(player,"\u00A73" + "You have eaten a heavenly fruit. Virtue increased by 6. I'm only human, after all.");
        }
        if (stack.getItem() == eAngelusItems.sinfulfruit)
        {
            IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
            morality.addSin(6);
            ChatUtil.sendNoSpam(player,"\u00A74" + "You have eaten forbidden fruit. Sin increased by 6. Don't put the blame on me.");
        }


        //Potion Effects Anyone?
        for(PotionEffect effect : effects)
        {
            player.addPotionEffect(effect);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("");
        if (stack.getItem() == eAngelusItems.sinfulfruit) tooltip.add("\u00A7e" + "A devilish fruit medley to increase sin by 6.");
        if (stack.getItem() == eAngelusItems.virtuefruit) tooltip.add("\u00A7e" + "A heavenly fruit medley to increase virtue by 6.");
    }
}
