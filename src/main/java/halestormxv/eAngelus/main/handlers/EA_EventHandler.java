package halestormxv.eAngelus.main.handlers;

import halestormxv.eAngelus.capabilities.Interfaces.IMorality;
import halestormxv.eAngelus.capabilities.moralityProvider;
import halestormxv.eAngelus.capabilities.moralityScale;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
            ChatUtil.sendNoSpam(player, "\u00A7eYour current morality is: "+ morality.getMorality());
            return;
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
            event.setExpToDrop(6);
            Random dChance = new Random();
            int chance = dChance.nextInt(100) + 1;
            if (chance < 15) {
                EntityPlayer player = event.getPlayer();
                IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
                player.sendMessage(new TextComponentString("\u00A74" + "Your scales of morality have tipped to sin."));
                morality.addSin(1);
            }
        }

        if (event.getState().getBlock() == eAngelusBlocks.demonicOre)
        {
            event.setExpToDrop(6);
            //BlockPos pos = event.getPos();
            //event.getWorld().spawnEntity(new EntityItem(event.getWorld(), pos.getX() + 2, pos.getY() + 2, pos.getZ(), new ItemStack(Items.DIAMOND)));
            Random dChance = new Random();
            int chance = dChance.nextInt(100) + 1;
            if (chance < 15) {
                EntityPlayer player = event.getPlayer();
                IMorality morality = player.getCapability(moralityProvider.MORALITY_CAP, null);
                player.sendMessage(new TextComponentString("\u00A73" + "Your scales of morality have tipped to virtue."));
                morality.addVirtue(1);
            }
        }
    }
}