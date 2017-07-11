package halestormxv.eAngelus.items.cards;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class O_card_Fire extends Item 
{
	//public  double powerCost;

	public O_card_Fire()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(-1);
	}

	public boolean isDamageable()
	{
		return false;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		int chargeTime = 0;
		ArrowLooseEvent event = new ArrowLooseEvent((EntityPlayer) entityLiving, itemstack, worldIn, chargeTime, false);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		//int j = this.getMaxItemUseDuration(itemstack) - timeLeft;
		int j = event.getCharge();
		List<EntityLivingBase> targetList = entityLiving.world.getEntitiesWithinAABB(EntityLivingBase.class, entityLiving.getEntityBoundingBox().expand(8.0F + j, 8.0F + j, 8.0F + j));	
		for (EntityLivingBase targets : targetList)
		{
			if ( targets != null)
			{
				if ( targets != (EntityPlayer)entityLiving)
				{
					((EntityPlayer)entityLiving).world.spawnEntity(new EntityLightningBolt(worldIn, targets.posX, targets.posY, targets.posZ, false) );
					targets.clearActivePotions();
					worldIn.createExplosion(targets, targets.posX, targets.posY, targets.posZ, 3.2F + (j / 2), true);
					targets.setHealth(targets.getHealth() - j);
					//worldIn.playSoundAtEntity(targets, RefStrings.MODID + ":leo_gift_execute", 1.4F, 1.0F);
				}
			}
		}
	}
}