package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
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
                    int cooldown = this.getCoolDown(stack);
                    //Check for Reagent
                    if (player.inventory.hasItemStack(new ItemStack(eAngelusItems.mystalDust)))
                    {
                        if ((stack.getTagCompound() != null) && (cooldown == 0))
                        {
                            if (player.isRiding())
                            {
                                player.dismountRidingEntity();
                            }
                            double posX = nbt.getDouble("PosX");
                            double posY = nbt.getDouble("PosY");
                            double posZ = nbt.getDouble("PosZ");
                            player.setPositionAndUpdate(posX + 0.6, posY, posZ + 0.6);
                            this.startCoolDown(stack);
                            this.consumeReagent(stack, world, player);
                        }
                        else
                        {
                            player.sendMessage(new TextComponentString("The Scrying Orb is on cooldown."));
                        }
                    }
                    else
                    {
                        player.sendMessage(new TextComponentString("The Scrying Orb requires Mystal Dust for use."));
                    }
                }
            }
            else
                player.sendMessage(new TextComponentString("You must sneak and right click to store Scrying data."));
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
            tooltip.add("\u00A73" + "DIM: "+ dimID);
            tooltip.add("\u00A7d" + "X: "  + posX);
            tooltip.add("\u00A72" + "Y: "  + posY);
            tooltip.add("\u00A7c" + "Z: "  + posZ);
            tooltip.add("");
            tooltip.add("\u00A74" + "Cooldown: " + nbt.getInteger("coolDown"));
        }
    }

    protected void startCoolDown(ItemStack stack)
    {
        if (stack.getTagCompound() != null)
        {
            stack.getTagCompound().setInteger("coolDown", 6000);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        if (!world.isRemote)
        {
            int coolDown = this.getCoolDown(stack);
            if (coolDown > 0) {
                stack.getTagCompound().setInteger("coolDown", coolDown - 1);
            }
            else if (coolDown < 0)
            {
                stack.getTagCompound().setInteger("coolDown", 0);
            }
        }
    }

    protected int getCoolDown(ItemStack stack) {
        if ( (stack.getTagCompound() != null) && (stack.getTagCompound().hasKey("coolDown")) )
        {
            return stack.getTagCompound().getInteger("coolDown");
        }
        return 0;
    }

    protected void consumeReagent(ItemStack stack, World worldIn, EntityPlayer entityLiving) {
        entityLiving.inventory.clearMatchingItems(scryingReagent(stack), -1, 1, null);
    }

    protected Item scryingReagent(ItemStack itemStackIn)
    {
        return eAngelusItems.mystalDust;
    }
}
