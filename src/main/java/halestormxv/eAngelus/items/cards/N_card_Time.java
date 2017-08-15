package halestormxv.eAngelus.items.cards;

import halestormxv.eAngelus.config.eAngelusConfig;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_SoundHandler;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class N_card_Time extends Item {
    private int dustRequirement = eAngelusConfig.reagentCost_TimeCard;

    public N_card_Time(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reference.eaCreativeTab);
        this.maxStackSize = 1;
        this.setMaxDamage(4);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        ItemStack mystalDust = new ItemStack(eAngelusItems.mystalDust);
        boolean hasReagent = playerIn.inventory.hasItemStack(mystalDust);
        int reagentAmount = checkForReagentQuantity(mystalDust, playerIn);
        int tempusBase = 8;
        int tempusFormula = 6;
        int tUC = (int)(Math.ceil((playerIn.experienceLevel) / tempusFormula));
        if ( (playerIn.experienceTotal > tUC) && (playerIn.experienceLevel >= tempusBase) )
        {
            if (hasReagent && reagentAmount >= dustRequirement)
            {
                itemStack.damageItem(1, playerIn);
                long currentWorldTime = worldIn.getWorldTime();
                worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, EA_SoundHandler.VIRTUE_INCREASE_LEVEL, SoundCategory.MASTER, 2.0F, 0.7F);
                if (currentWorldTime >= 13000)
                {
                    worldIn.setWorldTime(1000);
                }
                else
                {
                    worldIn.setWorldTime(13000);
                }
                this.consumeReagent(itemStack, worldIn, playerIn);
                //Check which value to subtract from expereince
                if (tUC > tempusBase) { playerIn.addExperienceLevel((int)-tUC); }else{ playerIn.addExperienceLevel((int)-tempusBase); }
                //===================\
            } else {
                ChatUtil.sendNoSpam(playerIn, "\u00A74Mystal Dust is a required Catalyst.");
            }
        }else {
            ChatUtil.sendNoSpam(playerIn, "\u00A74You do not have enough experience levels to use this Order.");
        }
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("");
        tooltip.add("\u00A76" + "The two most powerful warrior");
        tooltip.add("\u00A76" + "are patience and time.");
        tooltip.add("\u00A76" + "Control one of the two.");
        tooltip.add("");
        tooltip.add("\u00A7e" + "Item requires " + dustRequirement + " Mystal Dust");
        tooltip.add("\u00A7e" + "and more than 8 XP Levels.");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return EnumRarity.RARE;
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
