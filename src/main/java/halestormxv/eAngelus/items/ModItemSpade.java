package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.item.ItemSpade;

public class ModItemSpade extends ItemSpade 
{

	public ModItemSpade(String unlocalizedName, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Reference.eaCreativeTab);
		this.setMaxStackSize(1);
	}

}
