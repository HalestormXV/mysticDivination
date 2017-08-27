package halestormxv.eAngelus.slots;

import halestormxv.eAngelus.tileentity.TileEntityDualFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by Blaze on 8/27/2017.
 */
public class SlotDualFurnaceFuel extends Slot
{

    public SlotDualFurnaceFuel(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return TileEntityDualFurnace.isItemFuel(itemStack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        return super.getItemStackLimit(stack);
    }
}
