package halestormxv.eAngelus.blocks;

import halestormxv.eAngelus.config.eAngelusConfig;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_SoundHandler;
import halestormxv.eAngelus.main.init.eAngelusItems;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blaze on 7/31/2017.
 */
public class alter_Knight extends Block
{
    private int chargeRequirment = eAngelusConfig.soulChargeReq;

    public alter_Knight()
    {
        super(Material.IRON);
        this.setRegistryName("alter_knight");
        this.setResistance(3F);
        this.setHardness(4F);
        this.setHarvestLevel("pickaxe", 3);
        this.setUnlocalizedName("alter_knight");
        this.setCreativeTab(Reference.eaCreativeTab);
        this.setLightLevel(0.7375F);
        this.setLightOpacity(16);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (stack.getItem() == eAngelusItems.essence && stack.getMetadata() == 0)
        {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt != null)
            {
                nbt.setBoolean("takeMe", true);
                int fetchKills = nbt.getInteger("killCount");
                if (fetchKills >= chargeRequirment)
                {
                    worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, EA_SoundHandler.SIN_INCREASE_LEVEL, SoundCategory.MASTER, 2.0F, 1.0F);
                    playerIn.inventory.clearMatchingItems(eAngelusItems.essence, 0, 1, nbt.getCompoundTag("takeMe"));
                    ItemStack knightEssence = new ItemStack(eAngelusItems.essence, 1, 2);
                    playerIn.inventory.addItemStackToInventory(knightEssence);
                }else{
                    ChatUtil.sendNoSpam(playerIn,"\u00A74You need "+chargeRequirment+" Soul Charges to convert this item.");
                }
                return true;
            }
        } else {
            ChatUtil.sendNoSpam(playerIn,"\u00A73This alter will absorb the "+"\u00A74Soul Charges "+"\u00A73of an empty essence and convert it to an "+"\u00A76Essence of the Knight.");
        }
        return false;
    }
}
