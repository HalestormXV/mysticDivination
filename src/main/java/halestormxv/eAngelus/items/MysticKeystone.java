package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.List;

/**
 * Created by Blaze on 8/24/2017.
 */
public class MysticKeystone extends Item
{
    public MysticKeystone(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reference.eaCreativeTab);
        this.setMaxStackSize(1);
        this.setMaxDamage(7);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            ItemStack stack = player.getHeldItem(hand);
            Block b = world.getBlockState(pos).getBlock();
            if ((b instanceof BlockChest))
            {
                TileEntity tileChest = world.getTileEntity(pos);
                NBTTagCompound tileTag = tileChest.getTileData();
                NBTTagCompound stackTag = stack.getTagCompound();

                if ((tileTag.hasKey("divinationLock_k_kname")) && (tileTag.getString("divinationLock_k_kname").equals(stack.getDisplayName())))
                {
                    tileTag.removeTag("divinationLock_k_kname");
                    tileTag.removeTag("divinationLock_k_pname");
                    tileTag.removeTag("divinationLock");
                    ChatUtil.sendNoSpam(player, "\u00A76The Mystic Bindings have been dispelled, the chest is no longer secured.");
                    stack.damageItem(1, player);
                }
                else
                {
                    if (stackTag == null)
                    {
                        stackTag = new NBTTagCompound();
                        stackTag.setString("playerUUID", player.getPersistentID().toString());
                        stack.setTagCompound(stackTag);
                    }
                    tileTag.setString("divinationLock_k_kname", stack.getDisplayName());
                    tileTag.setString("divinationLock_k_pname", player.getPersistentID().toString());
                    tileTag.setString("divinationLock", player.getPersistentID().toString());
                    //System.out.println("The UUID set was: "+player.getPersistentID().toString());
                    ChatUtil.sendNoSpam(player, "\u00A76Mystic Bindings have been placed on the chest. Only you may access it.");
                    stack.damageItem(1, player);
                }
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        return EnumRarity.RARE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        NBTTagCompound stackNBT = stack.getTagCompound();
        tooltip.add("");
        tooltip.add("\u00A76" + "While sneaking and right-clicking a chest");
        tooltip.add("\u00A76" + "it will cast a Mystic Bindings spell.");
        tooltip.add("\u00A76" + "This spell prevents the chest from being opened");
        tooltip.add("\u00A76" + "or broken unless it is by the casting player.");
        tooltip.add("");
        if (stack.getTagCompound() != null)
            tooltip.add("ID: " + stackNBT.getString("playerUUID"));
    }
}