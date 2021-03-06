package halestormxv.eAngelus.items.cards;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.config.eAngelusConfig;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class S_card_Strength extends Item
{
    private int moralityRequirement = eAngelusConfig.moralityCost_StrengthCard;

    public S_card_Strength(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reference.eaCreativeTab);
        this.maxStackSize = 1;
        this.setMaxDamage(7);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        IMorality morality = playerIn.getCapability(moralityProvider.MORALITY_CAP, null);
        //Check for Reagent and Morality Level
        if (morality.getMorality() <= moralityRequirement)
        {
            if (playerIn.inventory.hasItemStack(new ItemStack(eAngelusItems.mystalDust)))
            {
                itemStack.damageItem(1, playerIn);
                playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("strength"), 1800, 4));
                this.consumeReagent(itemStack, worldIn, playerIn);
            } else {
                ChatUtil.sendNoSpam(playerIn, "\u00A74Mystal Dust is a required Catalyst.");
            }
        } else {
            ChatUtil.sendNoSpam(playerIn, "\u00A74Your morality level is not high enough to use this.");
        }
        return  new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("");
        tooltip.add("\u00A76" + "The Spear-Danes in days gone by");
        tooltip.add("\u00A76" + "and the kings who ruled them had");
        tooltip.add("\u00A76" + "courage and greatness.");
        tooltip.add("");
        tooltip.add("\u00A74" + "Item requires sin of: "+Math.abs(moralityRequirement));
    }

    private void consumeReagent(ItemStack stack, World worldIn, EntityPlayer entityLiving) {
        entityLiving.inventory.clearMatchingItems(eAngelusItems.mystalDust, -1, 1, null);
    }
}
