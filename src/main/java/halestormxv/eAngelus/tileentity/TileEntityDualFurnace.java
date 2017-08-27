package halestormxv.eAngelus.tileentity;

import halestormxv.eAngelus.blocks.DualFurance;
import halestormxv.eAngelus.crafting.DualFurnaceRecipes;
import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Blaze on 8/26/2017.
 */
public class TileEntityDualFurnace extends TileEntity implements IInventory, ITickable
{
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    private String customName;

    private int burnTime;
    private int currentBurnTime;
    private int cookTime;
    private int totalCookTime;

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.inventory)
            if(!stack.isEmpty())
                return false;
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return (ItemStack)this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemStack = (ItemStack)this.inventory.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemStack) && ItemStack.areItemsEqual(stack, itemStack);
        this.inventory.set(index, stack);

        if(stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());
        if(index == 0 && index + 1 == 1 && !flag)
        {
            ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
            this.totalCookTime = this.getCookTime(stack, stack1);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning()
    {
        return this.burnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false :
                player.getDistanceSq((double)this.pos.getX() + 0.5D,
                (double)this.pos.getY() + 0.5D,
                (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index == 3)
            return false;
        else if(index != 2)
            return true;
        else{
            return isItemFuel(stack);
        }
    }

    public String getGuiID()
    {
        return Reference.MODID+":dual_furnace";
    }

    @Override
    public int getField(int id) {
        switch(id)
        {
            case 0:
                return this.burnTime;
            case 1:
                return this.currentBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value)
    {
        switch(id)
        {
            case 0:
                this.burnTime = value;
                break;
            case 1:
                this.currentBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    public int getCookTime(ItemStack input1, ItemStack input2) //Change to a Switch to Adjust Burn Times
    {
        return 200;
    }

    @Override
    public void clear()
    {
        this.inventory.clear();
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.dual_furnace";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.inventory);
        this.burnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentBurnTime = getItemBurnTime((ItemStack)this.inventory.get(2));

        if(compound.hasKey("CustomName", 8))
            this.setCustomName(compound.getString("CustomName"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.burnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.loadAllItems(compound, this.inventory);

        if(this.hasCustomName())
            compound.setString("CustomName", this.customName);

        return compound;
    }

    @Override
    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if(this.isBurning())
            --this.burnTime;

        if(!this.world.isRemote)
        {
            ItemStack itemStack = (ItemStack)this.inventory.get(2);

            if(this.isBurning() || !itemStack.isEmpty() && !((((ItemStack)this.inventory.get(0)).isEmpty()) || ((ItemStack)this.inventory.get(1)).isEmpty()))
            {
                if(!this.isBurning() && this.canSmelt())
                {
                    this.burnTime = getItemBurnTime(itemStack);
                    this.currentBurnTime = this.burnTime;

                    if(this.isBurning())
                    {
                        flag1 = true;

                        if(!itemStack.isEmpty())
                        {
                            Item item = itemStack.getItem();
                            itemStack.shrink(1);
                            if(itemStack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemStack);
                                this.inventory.set(2, item1);
                            }
                        }
                    }
                }
                if(this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if(this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime((ItemStack)this.inventory.get(0), (ItemStack)this.inventory.get(1));
                        this.smeltItem();
                        flag1 = true;
                    }
                } else
                    this.cookTime = 0;
            } else if(!this.isBurning() && this.cookTime > 0)
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            if(flag != this.isBurning())
            {
                flag1 = true;
                DualFurance.setState(this.isBurning(), this.world, this.pos);
            }
        }
        if(flag1)
        this.markDirty();
    }

    private boolean canSmelt()
    {
        if( ((ItemStack)this.inventory.get(0)).isEmpty() || ((ItemStack)this.inventory.get(1)).isEmpty() )
            return false;
        else{
            ItemStack result = DualFurnaceRecipes.instance().getDualSmeltingResult((ItemStack)this.inventory.get(0), (ItemStack)this.inventory.get(1));
            if(result.isEmpty())
                return false;
            else{
                ItemStack output = (ItemStack)this.inventory.get(3);

                if(output.isEmpty()) return true;
                if(!output.isItemEqual(result)) return false;
                int res = output.getCount() + result.getCount();
                return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
            }
        }
    }

    public void smeltItem()
    {
        if(this.canSmelt())
        {
            ItemStack input1 = (ItemStack)this.inventory.get(0);
            ItemStack input2 = (ItemStack)this.inventory.get(1);
            ItemStack result = DualFurnaceRecipes.instance().getDualSmeltingResult(input1, input2);
            ItemStack output = (ItemStack)this.inventory.get(3);

            if(output.isEmpty())
                this.inventory.set(3, result.copy());
            else if(output.getItem() == result.getItem())
                output.grow(result.getCount());

            input1.shrink(1);
            input2.shrink(1);
        }
    }

    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();
            if (!item.getRegistryName().getResourceDomain().equals("minecraft"))
            {
                int burnTime = net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
                if (burnTime != 0) return burnTime;
            }
            return item ==
                    Item.getItemFromBlock(Blocks.WOODEN_SLAB) ? 150
                    : (item == Item.getItemFromBlock(Blocks.WOOL) ? 100
                    : (item == Item.getItemFromBlock(Blocks.CARPET) ? 67
                    : (item == Item.getItemFromBlock(Blocks.LADDER) ? 300
                    : (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON) ? 100
                    : (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD ? 300
                    : (item == Item.getItemFromBlock(Blocks.COAL_BLOCK) ? 16000
                    : (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()) ? 200
                    : (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()) ? 200
                    : (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()) ? 200
                    : (item == Items.STICK ? 100
                    : (item != Items.BOW && item != Items.FISHING_ROD ? (item == Items.SIGN ? 200
                    : (item == Items.COAL ? 1600
                    : (item == Items.LAVA_BUCKET ? 20000
                    : (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL ? (item == Items.BLAZE_ROD ? 2400
                    : (item instanceof ItemDoor && item != Items.IRON_DOOR ? 200 : (item instanceof ItemBoat ? 400 : 0)))
                    //Custom Items
                    : (item == eAngelusItems.mystalDust ? 3000


                    : 100)))))
                    : 300)))))))))));
        }
    }

    public static boolean isItemFuel(ItemStack fuel)
    {
        return getItemBurnTime(fuel) > 0;
    }
}
