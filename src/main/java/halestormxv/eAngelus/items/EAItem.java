package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.item.Item;

public class EAItem extends Item 
{
	public EAItem(String name)
	{
		super();	
		this.setUnlocalizedName(name);
		this.setCreativeTab(Reference.eaCreativeTab);	
	}

}
