package halestormxv.eAngelus.main.handlers;

import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Blaze on 8/24/2017.
 */
public class EA_ChestHandler
{
    @SubscribeEvent
    public void openChest(PlayerInteractEvent.RightClickBlock event)
    {
        if (!event.getWorld().isRemote)
        {
            event.getPos();
            Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
            if ((block instanceof BlockChest))
            {
                TileEntity tileChest = event.getWorld().getTileEntity(event.getPos());
                NBTTagCompound tileTag = tileChest.getTileData();
                if (tileTag.hasKey("divinationLock"))
                {
                    if (!tileTag.getString("divinationLock").equals(event.getEntityPlayer().getPersistentID().toString()) && (!event.getEntityPlayer().isCreative()))
                    {
                        ChatUtil.sendNoSpam(event.getEntityPlayer(),"\u00A74A magical force prevents you from opening this chest.");
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent event)
    {
        if (!event.getWorld().isRemote)
        {
            Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
            if ((block instanceof BlockChest))
            {
                TileEntity tileChest = event.getWorld().getTileEntity(event.getPos());
                NBTTagCompound tileTag = tileChest.getTileData();
                if ((tileTag.hasKey("divinationLock")) && (!tileTag.getString("divinationLock").equals(event.getPlayer().getPersistentID().toString())) && (!event.getPlayer().isCreative()))
                {
                    ChatUtil.sendNoSpam(event.getPlayer(), "\u00A74A magical force prevents you from breaking this chest.");
                    event.setCanceled(true);
                }
                else if ((tileTag.hasKey("divinationLock_k_pname")) && (!tileTag.getString("divinationLock_k_pname").equals(event.getPlayer().getName())) && (!event.getPlayer().isCreative()))
                {
                    ChatUtil.sendNoSpam(event.getPlayer(), "\u00A74A magical force prevents you from breaking this chest.");
                    event.setCanceled(true);
                }
            }
        }
    }
}