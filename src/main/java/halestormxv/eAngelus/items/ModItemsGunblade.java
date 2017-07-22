package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.handlers.EA_SoundHandler;
import halestormxv.eAngelus.mobs.entitys.EntityCelestialBolt;
import halestormxv.eAngelus.network.packets.ChatUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

/**
 * Created by Blaze on 7/16/2017.
 */
public class ModItemsGunblade extends ItemSword
{
    public double powerCost;

    public ModItemsGunblade(String unlocalizedName, ToolMaterial material)
    {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(Reference.eaCreativeTab);
        this.setMaxStackSize(1);
        this.setMaxDamage(400);
    }

    @Override
    public int getItemEnchantability()
    {
        return 0;
    }

    public double getGunbladeCost(EntityPlayer shooter)
    {
        powerCost = (float)(shooter.experienceLevel) / 3;
        return powerCost;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack){
        return EnumRarity.UNCOMMON;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack){
        return true;
    }


    public void onCreated(ItemStack itemstack, World world, EntityPlayer player)
    {
        /*NBTTagCompound nbt = itemstack.getTagCompound();
        nbt = new NBTTagCompound();

        itemstack.setTagCompound(nbt);
        nbt.setString("Gunblade Bound To: ", player.getDisplayNameString());
        nbt.setInteger("Cooldown: ", 0);*/
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if(!worldIn.isRemote)
        {
            if ((playerIn.capabilities.isCreativeMode) || (playerIn.experienceLevel > 8))
            {
                powerCost = getGunbladeCost(playerIn);
                worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, EA_SoundHandler.GUNBLADE_FIRE, SoundCategory.MASTER, 2.0F, 1.0F);
                playerIn.addExperienceLevel((int)-powerCost);
                playerIn.swingArm(handIn);
                itemStack.damageItem(10, playerIn);
                if (!worldIn.isRemote)
                {
                    EntityCelestialBolt celestialBolt = new EntityCelestialBolt(worldIn, playerIn);
                    celestialBolt.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                    playerIn.world.spawnEntity(celestialBolt);
                }
            } else {
                ChatUtil.sendNoSpam(playerIn, ("\u00A74You do not have enough power to fire this weapon."));
            }
        }
        return  new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
