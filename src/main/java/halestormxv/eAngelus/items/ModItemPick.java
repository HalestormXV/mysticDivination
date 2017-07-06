package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.item.ItemPickaxe;

public class ModItemPick extends ItemPickaxe
{

	public ModItemPick(String unlocalizedName, ToolMaterial material) 
	{
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Reference.eaCreativeTab);
		this.setMaxStackSize(1);
	}

}
