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
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TopazOre extends Block
{
	public TopazOre() 
	{
		super(Material.ROCK);
		this.setRegistryName("topazOre");
		this.setResistance(3F);
		this.setHardness(3F);
		this.setHarvestLevel("pickaxe", 2);
		this.setUnlocalizedName("topazOre");
		this.setCreativeTab(Reference.eaCreativeTab);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this == eAngelusBlocks.topazOre ? eAngelusItems.topazStone : Item.getItemFromBlock(this);
	}
	
	public int quantityDropped(Random random)
	{
		return this == eAngelusBlocks.topazOre ? 1 + random.nextInt(4) : 1;
	}

}
