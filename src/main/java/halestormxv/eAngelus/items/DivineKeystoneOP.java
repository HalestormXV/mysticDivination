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
import net.minecraft.server.management.PlayerList;
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
public class DivineKeystoneOP extends Item
{
    public DivineKeystoneOP(String name)
    {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reference.eaCreativeTab);
        this.setMaxStackSize(1);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            Block b = world.getBlockState(pos).getBlock();
            if ( (b instanceof BlockChest) && player.canUseCommand(4, null) || player.isCreative() )
            {
                TileEntity tileChest = world.getTileEntity(pos);
                NBTTagCompound tileTag = tileChest.getTileData();
                if ((tileTag.hasKey("divinationLock_k_kname")))
                {
                    tileTag.removeTag("divinationLock_k_kname");
                    tileTag.removeTag("divinationLock_k_pname");
                    tileTag.removeTag("divinationLock");
                    ChatUtil.sendNoSpam(player, "\u00A76The Mystic Bindings have been shattered under divine power.");
                }
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.EPIC;
    }

    @Override
    public boolean hasEffect(ItemStack stack) { return true; }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        NBTTagCompound stackNBT = stack.getTagCompound();
        tooltip.add("");
        tooltip.add("\u00A76" + "This keystone contains the power");
        tooltip.add("\u00A76" + "of the Divines. It can shatter any");
        tooltip.add("\u00A76" + "Mystic Bindings placed on a chest.");
        tooltip.add("\u00A76" + "Only the chosen may utilize its power.");
        tooltip.add("");
        tooltip.add("\u00A7d" + "OP or Creative Mode Only");
    }
}