package halestormxv.eAngelus.items.records;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * Created by Blaze on 7/9/2017.
 */
public class eAngelus_Records extends ItemRecord
{
    private final String file;

    public eAngelus_Records(String record, SoundEvent sound, String name) {
        super("eAngelus:" + record, sound);
        setCreativeTab(Reference.eaCreativeTab);
        GameRegistry.register(this, new ResourceLocation(Reference.MODID, name));
        setUnlocalizedName(name);
        file = "eAngelus:music." + record;
    }

    @Nonnull
    @Override
    public String getUnlocalizedNameInefficiently(@Nonnull ItemStack par1ItemStack) {
        return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("item\\.", "item." + Reference.MODID + ":");
    }

    @Nonnull
    @Override
    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(file);
    }
}
