package halestormxv.eAngelus.crafting;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;

import java.util.Map;

/**
 * Created by Blaze on 8/26/2017.
 */
public class DualFurnaceRecipes
{
    private static final DualFurnaceRecipes SMELTING = new DualFurnaceRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> dualSmeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static DualFurnaceRecipes instance()
    {
        return SMELTING;
    }

    private DualFurnaceRecipes()
    {
        this.addDualSmeltingRecipe(new ItemStack(Blocks.DIAMOND_ORE), new ItemStack(Blocks.GOLD_ORE), new ItemStack(Items.EMERALD), 12.0F);
    }

    public void addDualSmeltingRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience)
    {
        if(getDualSmeltingResult(input1, input2) != ItemStack.EMPTY)
        {
            FMLLog.info("eAngelus: Ignored dual smelting recipe with conflicting input: " + input1 + " and " + input2 + " = " + result);
            return;
        }
        this.dualSmeltingList.put(input1, input2, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getDualSmeltingResult(ItemStack input1, ItemStack input2)
    {
        for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.dualSmeltingList.columnMap().entrySet())
            if (this.compareItemStacks(input1, (ItemStack)entry.getKey()))
                for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
                        return (ItemStack)ent.getValue();
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
    {
        return this.dualSmeltingList;
    }

    public float getDualSmeltingExperience(ItemStack stack)
    {
        for(Map.Entry<ItemStack, Float> entry : this.experienceList.entrySet())
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
                return((Float)entry.getValue().floatValue());
        return 0.0f;
    }
}
