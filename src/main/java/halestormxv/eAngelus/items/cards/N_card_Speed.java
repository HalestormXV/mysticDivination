package halestormxv.eAngelus.items.cards;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.config.eAngelusConfig;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
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

public class N_card_Speed extends Item {
    private int dustRequirement = eAngelusConfig.reagentCost_SpeedCard;

    public N_card_Speed(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reference.eaCreativeTab);
        this.maxStackSize = 1;
        this.setMaxDamage(4);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        ItemStack mystalDust = new ItemStack(eAngelusItems.mystalDust);
        boolean hasReagent = playerIn.inventory.hasItemStack(mystalDust);
        int reagentAmount = checkForReagentQuantity(mystalDust, playerIn);

        if (hasReagent && reagentAmount >= dustRequirement)
        {
            itemStack.damageItem(1, playerIn);
            playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("speed"), 400, 4));
            this.consumeReagent(itemStack, worldIn, playerIn);
        } else {
            ChatUtil.sendNoSpam(playerIn, "\u00A74Mystal Dust is a required Catalyst.");
        }
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("");
        tooltip.add("\u00A76" + "And forget not that the earth delights");
        tooltip.add("\u00A76" + "to feel your bare feet and the");
        tooltip.add("\u00A76" + "winds long to play with your hair");
        tooltip.add("");
        tooltip.add("\u00A7e" + "Item requires " + dustRequirement + " Mystal Dust");
    }

    private void consumeReagent(ItemStack stack, World worldIn, EntityPlayer entityLiving) {
        entityLiving.inventory.clearMatchingItems(eAngelusItems.mystalDust, -1, dustRequirement, null);
    }

    private int checkForReagentQuantity(ItemStack itemStack, EntityPlayer player)
    {
        int count = 0;
        Item mystalDustItem = itemStack.getItem();
        boolean hasReagent = player.inventory.hasItemStack(itemStack);
        if (hasReagent)
        {
            for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++)
            {
                ItemStack stack = player.inventory.getStackInSlot(slot);
                if (stack != null && stack.getItem().equals(mystalDustItem)) {
                    int total = count += stack.getCount();
                    //System.out.println("Player has: " + total);
                    return total;
                }
            }
        } else {
            //System.out.println("Player has failed");
            return 0;
        }
        //How'd you even get here?
        return 0;
    }
}
