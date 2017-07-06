package halestormxv.eAngelus.blocks;

import java.util.Random;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MystalCite extends Block 
{
	public MystalCite() 
	{
		super(Material.ROCK);
		this.setUnlocalizedName("mystalCite");
		this.setCreativeTab(Reference.eaCreativeTab);
		this.setRegistryName("mystalCite");
		this.setResistance(3F);
		this.setHardness(4F);
		this.setHarvestLevel("pickaxe", 3);
		this.setLightLevel(0.7375F);
		this.setLightOpacity(16);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this == eAngelusBlocks.mystalCite ? eAngelusItems.mystalDust : Item.getItemFromBlock(this);
	}
	
	public int quantityDroppedWithBonus(int fortune, Random random)
	{
		return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 3);
	}

	public int quantityDropped(Random random) 
	{
		return 1 + random.nextInt(3);
	}
}
