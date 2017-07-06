package halestormxv.eAngelus.items;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.item.ItemHoe;

public class ModItemHoe extends ItemHoe
{

	public ModItemHoe(String unlocalizedName, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Reference.eaCreativeTab);
		this.setMaxStackSize(1);
	}

}
