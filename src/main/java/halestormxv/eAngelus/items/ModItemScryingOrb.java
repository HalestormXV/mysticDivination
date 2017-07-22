package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.packets.ChatUtil;
import halestormxv.eAngelus.config.eAngelusConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Blaze on 7/6/2017.
 */

public class ModItemScryingOrb extends Item
{
    private int mystalRequirment = eAngelusConfig.reagentCost_ScryingOrb;

    public ModItemScryingOrb(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reference.eaCreativeTab);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        if(!world.isRemote)
        {
            ItemStack stack = player.getHeldItem(hand);
            NBTTagCompound nbt = stack.getTagCompound();


            if (player.isSneaking())
            {
                if (nbt == null)
                {
                    nbt = new NBTTagCompound();
                    player.sendMessage(new TextComponentString("The Scrying Orb has stored your location data."));
                    nbt.setInteger("Dim", player.dimension);
                    nbt.setDouble("PosX", player.getPosition().getX());
                    nbt.setDouble("PosY", player.getPosition().getY());
                    nbt.setDouble("PosZ", player.getPosition().getZ());
                    stack.setTagCompound(nbt);
                }
                else
                {
                    ItemStack mystalDust = new ItemStack(eAngelusItems.mystalDust);
                    int reagentAmount = checkForReagentQuantity(mystalDust, player);
                    long totWorldTime = this.getStoredWorldTime(stack);
                    long currentWorldTime = player.world.getTotalWorldTime();
                    //Check for Reagent
                    if (reagentAmount >= mystalRequirment)
                    {
                        //System.out.println("Current Stored Time: " + totWorldTime);
                        //System.out.println("Current World Time: " + currentWorldTime);
                        if ((stack.getTagCompound() != null) && (currentWorldTime > totWorldTime + eAngelusConfig.scryingOrbCooldown ))
                        {
                            if (player.isRiding())
                            {
                                player.dismountRidingEntity();
                            }
                            int fetchDim = nbt.getInteger("Dim");
                            double posX = nbt.getDouble("PosX");
                            double posY = nbt.getDouble("PosY");
                            double posZ = nbt.getDouble("PosZ");
                            if (player.dimension != fetchDim) { player.changeDimension(fetchDim); }
                            player.setPositionAndUpdate(posX + 0.6, posY, posZ + 0.6);
                            this.setNewWorldTime(stack, player);
                            this.consumeReagent(stack, world, player);
                        }
                        else
                        {
                            ChatUtil.sendNoSpam(player, "\u00A74The Scrying Orb is on cooldown.");
                        }
                    }
                    else
                    {
                        ChatUtil.sendNoSpam(player,"\u00A74The Scrying Orb requires "+mystalRequirment+" Mystal Dust for use.");
                    }
                }
            }
            else
                ChatUtil.sendNoSpam(player,"\u00A74You must sneak and right click to store Scrying data.");
        }
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("");
        tooltip.add("\u00A76" + "An orb used to store location data.");
        tooltip.add("\u00A76" + "After storing, it grants the ability");
        tooltip.add("\u00A76" + "to teleport to stored location.");
        tooltip.add("");
        if (stack.getTagCompound() != null)
        {
            NBTTagCompound nbt = stack.getTagCompound();
            int dimID = nbt.getInteger("Dim");
            double posX = nbt.getDouble("PosX");
            double posY = nbt.getDouble("PosY");
            double posZ = nbt.getDouble("PosZ");
            long storedWorldTime = nbt.getLong("totalWorldTime");
            long currentWorldTime = Minecraft.getMinecraft().world.getTotalWorldTime();
            tooltip.add("\u00A73" + "DIM: "+ dimID);
            tooltip.add("\u00A7d" + "X: "  + posX);
            tooltip.add("\u00A72" + "Y: "  + posY);
            tooltip.add("\u00A7c" + "Z: "  + posZ);
            tooltip.add("");
            if (getCooldownReal(storedWorldTime, currentWorldTime) > 0)
            {
                tooltip.add("\u00A74" + "Cooldown: " + getCooldownReal(storedWorldTime, currentWorldTime) + " Min");
            }
            else
            {
                tooltip.add("\u00A72" + "Scrying Orb is ready for use.");
            }

        }
    }

    //Cooldown Handlers\\
    private static int getCooldownReal(long storedWorldTime, long currentWorldTime) {
        long TimeLeftInTicks = eAngelusConfig.scryingOrbCooldown - (currentWorldTime - storedWorldTime);
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

    private void setNewWorldTime(ItemStack stack, EntityPlayer player)
    {
        if (stack.getTagCompound() != null)
        {
            stack.getTagCompound().removeTag("totalWorldTime");
            stack.getTagCompound().setLong("totalWorldTime", player.world.getTotalWorldTime());
        }
    }

    private long getStoredWorldTime(ItemStack stack) {
        if ( (stack.getTagCompound() != null) && (stack.getTagCompound().hasKey("totalWorldTime")) )
        {
            return stack.getTagCompound().getLong("totalWorldTime");
        }
        return 0;
    }

    private void consumeReagent(ItemStack stack, World worldIn, EntityPlayer entityLiving) {
        entityLiving.inventory.clearMatchingItems(eAngelusItems.mystalDust, -1, mystalRequirment, null);
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
                    return total;
                }
            }
        } else {
            return 0;
        }
        //How'd you even get here?
        return 0;
    }
}