package halestormxv.eAngelus.main;

import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EACreativeTab extends CreativeTabs 
{

	public EACreativeTab(String string) 
	{
		super(string);
	}
	
	public ItemStack getTabIconItem()
	{
		return new ItemStack(eAngelusItems.angelic_ingot);
	}

}
