package halestormxv.eAngelus.main.handlers;

import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class EA_FuelHandler implements IFuelHandler 
{

	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		if(eAngelusItems.mystalDust != null)
		{
			return 8400;
		}else{
			return 0;
		}
	}

}
