package halestormxv.eAngelus.blocks;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DemonicBlock extends Block {

	public DemonicBlock() 
	{
		super(Material.IRON);
		this.setRegistryName("demonic_block");
		this.setResistance(3F);
		this.setHardness(3F);
		this.setHarvestLevel("pickaxe", 3);
		this.setUnlocalizedName("demonic_block");
		this.setCreativeTab(Reference.eaCreativeTab);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}

}
