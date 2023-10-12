package io.github.jorge.anglercraft.objects.blocks.containers;

import io.github.jorge.anglercraft.objects.blocks.recipes.HookForgeRecipes;
import io.github.jorge.anglercraft.objects.blocks.tileentities.TileEntityHookForge;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerHookForge extends Container{
    private final TileEntityHookForge tileEntity;
    private int cookTime, totalCookTime, burnTime, currentBurnTime;

    public ContainerHookForge(InventoryPlayer player, TileEntityHookForge tileEntity) {
        this.tileEntity = tileEntity;
		IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        this.addSlotToContainer(new SlotItemHandler(handler, 0, 56, 17));
        this.addSlotToContainer(new SlotItemHandler(handler, 1, 56, 53));
        this.addSlotToContainer(new SlotItemHandler(handler,  2, 112, 31));

        // Player Inventory for GUI
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Player Hotbar for GUI
        for(int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int i = 0; i < this.listeners.size(); i++){
            IContainerListener listener = (IContainerListener)this.listeners.get(i);

            if(this.cookTime != this.tileEntity.getField(2))
                listener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
            if(this.burnTime != this.tileEntity.getField(0))
                listener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
            if(this.currentBurnTime != this.tileEntity.getField(1))
                listener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
            if(this.totalCookTime != this.tileEntity.getField(3))
                listener.sendWindowProperty(this, 3, this.tileEntity.getField(3));
        }

        this.cookTime = this.tileEntity.getField(2);
        this.burnTime = this.tileEntity.getField(0);
        this.currentBurnTime = this.tileEntity.getField(1);
        this.totalCookTime = this.tileEntity.getField(3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileEntity.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileEntity.isUsableByPlayer(playerIn);
    }

    @Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) 
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 2) 
			{
				if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index != 1 && index != 0) 
			{		
				
				if(!HookForgeRecipes.getInstance().getHookForgingResult(stack1).isEmpty())
				{
					if(!this.mergeItemStack(stack1, 0, 1, false)) 
					{
						return ItemStack.EMPTY;
					}
					else if(TileEntityHookForge.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 1, 2, false)) return ItemStack.EMPTY;
					}
					else if(TileEntityHookForge.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 1, 2, false)) return ItemStack.EMPTY;
					}
					else if(TileEntityHookForge.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 1, 2, false)) return ItemStack.EMPTY;
					}
					else if(index >= 3 && index < 31)
					{
						if(!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
					}
					else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false))
					{
						return ItemStack.EMPTY;
					}
				}
			} 
			else if(!this.mergeItemStack(stack1, 4, 40, false)) 
			{
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();

			}
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
    
}
