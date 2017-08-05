package halestormxv.eAngelus.main.handlers;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.MoralityCapability.moralityProvider;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.eAngelusPacketHandler;
import halestormxv.eAngelus.network.packets.ChatUtil;
import halestormxv.eAngelus.network.packets.SyncMorality;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * Created by Blaze on 7/5/2017.
 */
public class EA_EventHandler {

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
            int moralityNoNegative = displayMorality(player);
            if (morality.getMorality() == 0) { ChatUtil.sendNoSpam(player, "\u00A7eYour current morality is: "+ morality.getMorality()); }
            if (morality.getMorality() > 0) { ChatUtil.sendNoSpam(player, "\u00A73Your virtuous morality is: "+ morality.getMorality()); }
            if (morality.getMorality() < 0) { ChatUtil.sendNoSpam(player, "\u00A74Your sinful morality is: "+ moralityNoNegative); }
        }
    }

    @SubscribeEvent
    public void livingDrops(LivingDropsEvent event)
    {
        if (event.getEntity() instanceof EntityMob)
        {
            Random dChance = new Random();
            int rareDrop = dChance.nextInt(100) + 1;
            if (rareDrop < 20 && (event.getSource().getSourceOfDamage() instanceof EntityPlayer))
            {
                Random random = new Random();
                ItemStack itemStackToDrop = new ItemStack(eAngelusItems.tarotPaper, random.nextInt(3));
                event.getDrops().add(new EntityItem(event.getEntity().world, event.getEntity().posX,
                        event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
            }
        }
    }

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent event)
    {
        if (event.getSource().getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
            if (player.getHeldItemMainhand() != ItemStack.EMPTY)
            {
                if (player.getHeldItemMainhand().getItem() == eAngelusItems.fireSword)
                {
                    double d = Math.random();
                    if (d < 0.7)
                    {
                        event.getEntity().setFire(8);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void itemPickup(EntityItemPickupEvent event)
    {


    }

    @SubscribeEvent
    public void livingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        /*
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (player.getHeldItemMainhand() != ItemStack.EMPTY)
            {
                if (player.getHeldItemMainhand().getItem() == eAngelusItems.fireSword)
                {
                    player.setGlowing(true);
                }
            }
        }*/
    }

    @SubscribeEvent
    public void entityDeath(LivingDeathEvent event)
    {
        //Check for EmptyEssence
        if ( (event.getSource().getEntity() instanceof EntityPlayer) && (event.getEntity() instanceof EntityMob) )
        {
            Item blankEssence = eAngelusItems.essence;
            EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
            for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++)
            {
                ItemStack stack = player.inventory.getStackInSlot(slot);
                if (stack != null && stack.getItem().equals(blankEssence) && stack.getMetadata() == 0)
                {
                    NBTTagCompound nbt = stack.getTagCompound();
                    if (nbt == null)
                    {
                        nbt = new NBTTagCompound();
                        nbt.setInteger("killCount", 1);
                        stack.setTagCompound(nbt);
                    }
                    else
                    {
                        int fetchKills = nbt.getInteger("killCount");
                        int newKills = fetchKills + 1;
                        nbt.setInteger("killCount", newKills);
                    }
                }
            }
        }

        //Check for Village Death and Add Sin
        if ( (event.getSource().getEntity() instanceof EntityPlayer) && (event.getEntity() instanceof EntityVillager) )
        {
            EntityPlayer player = (EntityPlayer) event.getSource().getEntity();
            World world = player.getEntityWorld();
            IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
            player.sendMessage(new TextComponentString("\u00A74" + "Your scales of morality have tipped to sin."));
            morality.addSin(1);
            if (!player.world.isRemote) { eAngelusPacketHandler.sendTo(new SyncMorality(morality.getMorality()), (EntityPlayerMP) player);}
            world.playSound(null, player.posX, player.posY, player.posZ, EA_SoundHandler.SIN_INCREASE_LEVEL, SoundCategory.MASTER, 2.0F, 1.0F);
        }


    }

    @SubscribeEvent
    public void itemTooltip(ItemTooltipEvent event)
    {

    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event)
    {
        EntityPlayer player = event.getEntityPlayer();
        IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
        IMorality oldMorality = event.getOriginal().getCapability(moralityProvider.MORALITY_CAP, null);
        morality.set(oldMorality.getMorality());
    }

    @SubscribeEvent
    public void breakEvent(BlockEvent.BreakEvent event)
    {
        if (event.getState().getBlock() == eAngelusBlocks.angelicOre)
        {
            event.setExpToDrop(3);
            Random dChance = new Random();
            int chance = dChance.nextInt(100) + 1;
            if (chance < 15) {
                EntityPlayer player = event.getPlayer();
                World world = event.getWorld();
                IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
                player.sendMessage(new TextComponentString("\u00A74" + "Your scales of morality have tipped to sin."));
                morality.addSin(1);
                if (!player.world.isRemote) { eAngelusPacketHandler.sendTo(new SyncMorality(morality.getMorality()), (EntityPlayerMP) player);}
                world.playSound(null, player.posX, player.posY, player.posZ, EA_SoundHandler.SIN_INCREASE_LEVEL, SoundCategory.MASTER, 2.0F, 1.0F);

            }
        }

        if (event.getState().getBlock() == eAngelusBlocks.demonicOre)
        {
            event.setExpToDrop(3);
            Random dChance = new Random();
            int chance = dChance.nextInt(100) + 1;
            if (chance < 15) {
                EntityPlayer player = event.getPlayer();
                World world = event.getWorld();
                IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
                player.sendMessage(new TextComponentString("\u00A73" + "Your scales of morality have tipped to virtue."));
                morality.addVirtue(1);
                if (!player.world.isRemote) { eAngelusPacketHandler.sendTo(new SyncMorality(morality.getMorality()), (EntityPlayerMP) player);}
                world.playSound(null, player.posX, player.posY, player.posZ, EA_SoundHandler.VIRTUE_INCREASE_LEVEL, SoundCategory.MASTER, 2.0F, 1.0F);
            }
        }
    }

    public static int displayMorality(EntityPlayer player)
    {
        IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
        int currentMorality = morality.getMorality();
        int displayMorality;
        if (currentMorality < 0) {
            displayMorality = Math.abs(currentMorality);
        } else {
            displayMorality = currentMorality;
            {
                return displayMorality;
            }
        }
        return displayMorality;
    }
}