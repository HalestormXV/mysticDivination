package halestormxv.eAngelus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AngelicBlock extends Block
{

	public AngelicBlock() 
	{
		super(Material.IRON);
		this.setRegistryName("angelic_block");
		this.setResistance(3F);
		this.setHardness(3F);
		this.setHarvestLevel("pickaxe", 3);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}

}
