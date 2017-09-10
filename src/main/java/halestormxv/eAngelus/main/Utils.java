package halestormxv.eAngelus.main;

import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Blaze on 7/10/2017.
 */
public class Utils
{
    private static Logger logger;

    public static Logger getLogger()
    {
        if ( logger == null )
        {
            logger = LogManager.getFormatterLogger((Reference.MODID));
        }
        return logger;
    }

    public static void consumeReagent(ItemStack stack, int dustRequirement, World worldIn, EntityPlayer entityLiving)
    {
        entityLiving.inventory.clearMatchingItems(eAngelusItems.mystalDust, -1, dustRequirement, null);
    }

    public static int checkForReagentQuantity(ItemStack itemStack, EntityPlayer player)
    {
        int count = 0;
        Item mystalDustItem = itemStack.getItem();
        boolean hasReagent = player.inventory.hasItemStack(itemStack);
        if (hasReagent)
        {
            for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++)
            {
                ItemStack stack = player.inventory.getStackInSlot(slot);
                if (stack != null && stack.getItem().equals(mystalDustItem)) {
                    int total = count += stack.getCount();
                    //System.out.println("Player has: " + total);
                    return total;
                }
            }
        } else {
            return 0;
        }
        return 0;
    }

    public static int checkForTalisman(EntityPlayer player)
    {
        Item whichTalisman = eAngelusItems.talismans;
        for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++)
        {
            ItemStack stack = player.inventory.getStackInSlot(slot);
            if (!stack.isEmpty() && stack.getItem().equals(whichTalisman) && stack.getMetadata() == 0)
            {
                //System.out.println(player + " has the Sin Talisman in inventory.");
                return 1;
            }
            else if (!stack.isEmpty() && stack.getItem().equals(whichTalisman) && stack.getMetadata() == 1)
            {
                //System.out.println(player + " has the Virtue Talisman in inventory.");
                return 2;
            }
        }
        //System.out.println(player + " has somehow triggered an error or has no talisman.");
        return 0;
    }
}
