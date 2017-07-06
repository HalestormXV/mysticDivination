package halestormxv.eAngelus.blocks;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AngelicOre extends Block 
{
	
	public AngelicOre() 
	{
		super(Material.IRON);
		this.setRegistryName("angelicOre");
		this.setResistance(3F);
		this.setHardness(3F);
		this.setHarvestLevel("pickaxe", 2);
		this.setUnlocalizedName("angelicOre");
		this.setCreativeTab(Reference.eaCreativeTab);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}

}
