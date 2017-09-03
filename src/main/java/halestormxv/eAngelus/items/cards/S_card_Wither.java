package halestormxv.eAngelus.items.cards;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.config.eAngelusConfig;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_SoundHandler;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.item.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class S_card_Wither  extends Item
{
    private int moralityRequirement = eAngelusConfig.moralityCost_WitherCard;

    public S_card_Wither(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reference.eaCreativeTab);
        this.maxStackSize = 1;
        this.setMaxDamage(8);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 52;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.BOW;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            int xpTotal = ((EntityPlayer) entityLiving).experienceLevel;
            int radius = (int)(Math.ceil((xpTotal) / 10));
            if (radius > 10) { radius = 10; }
            List<EntityLivingBase> targetList = entityLiving.world.getEntitiesWithinAABB(EntityLivingBase.class, entityLiving.getEntityBoundingBox().expand(5.0F + radius, 5.0F + radius, 5.0F + radius));
            for (EntityLivingBase targets : targetList)
            {
                if (targets != null) {
                    if (targets != entityLiving)
                    {
                        targets.clearActivePotions();
                        //targets.setHealth(targets.getHealth() - j);
                        world.playSound(null, entityLiving.posX, entityLiving.posY, entityLiving.posZ, EA_SoundHandler.WITHER_CARD_EXECUTE, SoundCategory.MASTER, 0.8F, 1.0F);
                        targets.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("wither"), 1800, 4));
                    }
                }
            }
            itemStack.damageItem(1, entityLiving);
        }
        return itemStack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);
        NBTTagCompound nbt = itemStack.getTagCompound();
        if (!world.isRemote)
        {
            if (nbt == null)
            {
                nbt = new NBTTagCompound();
                nbt.setLong("totalWorldTime", 0);
                itemStack.setTagCompound(nbt);
            }
            long totWorldTime = this.getStoredWorldTime(itemStack);
            long currentWorldTime = player.world.getTotalWorldTime();
            if ((itemStack.getTagCompound() != null) && (currentWorldTime > totWorldTime + eAngelusConfig.witherCardCooldown)) {
                this.setNewWorldTime(itemStack, player);
                IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
                if (morality.getMorality() <= moralityRequirement)
                {
                    if (!player.isHandActive())
                    {
                        world.playSound(null, player.posX, player.posY, player.posZ, EA_SoundHandler.WITHER_CARD_CAST, SoundCategory.MASTER, 1.0F, 1.0F);
                    }
                    player.setActiveHand(hand);
                } else {
                    ChatUtil.sendNoSpam(player, "\u00A74Your Sin level is not high enough to use this Order.");
                    return new ActionResult<>(EnumActionResult.FAIL, itemStack);
                }
            } else {
                ChatUtil.sendNoSpam(player, "\u00A74This Order is on cooldown.");
                return new ActionResult<>(EnumActionResult.FAIL, itemStack);
            }
            return new ActionResult<>(EnumActionResult.FAIL, itemStack);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("");
        tooltip.add("\u00A76" + "Life, like a flower,");
        tooltip.add("\u00A76" + "will eventually wither and die.");
        tooltip.add("\u00A76" + "I shed no tears at the loss.");
        tooltip.add("");
        tooltip.add("\u00A74" + "Item requires sin of: "+Math.abs(moralityRequirement));
        if (stack.getTagCompound() != null)
        {
            NBTTagCompound nbt = stack.getTagCompound();
            long storedWorldTime = nbt.getLong("totalWorldTime");
            long currentWorldTime = Minecraft.getMinecraft().world.getTotalWorldTime();
            if (getCooldownReal(storedWorldTime, currentWorldTime) > 0)
            {
                tooltip.add("\u00A74" + "Cooldown: " + getCooldownReal(storedWorldTime, currentWorldTime) + " Min");
            }
            else
            {
                tooltip.add("\u00A72" + "ORDER: Defluet is ready for use.");
            }
        }
    }

    //COOLDOWN HANDLER\\
    private long getStoredWorldTime(ItemStack stack)
    {
        if ( (stack.getTagCompound() != null) && (stack.getTagCompound().hasKey("totalWorldTime")) )
        {
            return stack.getTagCompound().getLong("totalWorldTime");
        }
        return 0;
    }

    private void setNewWorldTime(ItemStack stack, EntityPlayer player)
    {
        if (stack.getTagCompound() != null)
        {
            stack.getTagCompound().removeTag("totalWorldTime");
            stack.getTagCompound().setLong("totalWorldTime", player.world.getTotalWorldTime());
        }
    }

    private static int getCooldownReal(long storedWorldTime, long currentWorldTime)
    {
        long TimeLeftInTicks = eAngelusConfig.witherCardCooldown - (currentWorldTime - storedWorldTime);
        long TimeLeftInMinutes = TimeLeftInTicks / (20 * 60);
        if (TimeLeftInTicks > 0)
        {
            return (int) TimeLeftInMinutes + 1;
        }
        else
        {
            TimeLeftInMinutes = 0;
            return (int) TimeLeftInMinutes;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return EnumRarity.UNCOMMON;
    }
}
