package halestormxv.eAngelus.blocks;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ObsidianIronTable extends Block
{
	//protected static final AxisAlignedBB RENDER = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.6875D, 0.0D);

	public ObsidianIronTable(String name, Material materialIn) {
		super(materialIn);
		this.setCreativeTab(Reference.eaCreativeTab);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), getRegistryName());
	}
	
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    /*@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return RENDER;
    }*/
}
