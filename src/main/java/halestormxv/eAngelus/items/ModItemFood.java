package halestormxv.eAngelus.items;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.main.EACreativeTab;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Blaze on 7/6/2017.
 */

public class ModItemFood extends ItemFood
{
    private PotionEffect[] effects;

    //Steak = 0.8f Saturation
    public ModItemFood(String unlocalizedName, int amount, float saturation, boolean isWolfFood, PotionEffect... potionEffects) {
        super(amount, saturation, isWolfFood);
        this.effects = potionEffects;
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(Reference.eaCreativeTab);
    }

    public ModItemFood(String unlocalizedName, int amount, boolean isWolfFood, PotionEffect... potionEffects) {
        super(amount, isWolfFood);
        this.effects = potionEffects;
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(Reference.eaCreativeTab);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        for (PotionEffect effect : effects) {
            player.addPotionEffect(effect);
        }
    }
}
