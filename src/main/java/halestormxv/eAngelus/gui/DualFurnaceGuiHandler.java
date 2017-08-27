package halestormxv.eAngelus.gui;

import halestormxv.eAngelus.tileentity.TileEntityDualFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by Blaze on 8/27/2017.
 */
public class DualFurnaceGuiHandler implements IGuiHandler
{
    public static final int DUAL_FURNACE_GUI = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == DUAL_FURNACE_GUI)
            return new ContainerDualFurnace(player.inventory, ((TileEntityDualFurnace)world.getTileEntity(new BlockPos(x, y, z))));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == DUAL_FURNACE_GUI)
            return new GuiDualFurnace(player.inventory, ((TileEntityDualFurnace)world.getTileEntity(new BlockPos(x, y, z))));
        return null;
    }
}
