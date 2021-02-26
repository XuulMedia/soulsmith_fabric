package com.xuul.soulsmith.gui;

import com.xuul.soulsmith.registry.GuiRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AlloySmelterScreenHandler extends ScreenHandler {
    private final Inventory inventory;


    public AlloySmelterScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(4));

    }



    public AlloySmelterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(GuiRegistry.ALLOY_SMELTER_SCREEN_HANDLER, syncId);
//        checkSize(inventory, 4);
        this.inventory = inventory;

//
//        this.addSlot(new Slot(inventory, 0, 40, 16));
//        this.addSlot(new Slot(inventory, 1, 60, 16));
//        this.addSlot(new Slot(inventory, 2, 50, 34));
//        this.addSlot(new Slot(inventory, 3, 32, 32));
//
//        //This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
//        //This will not render the background of the slots however, this is the Screens job
//        int m;
//        int l;
//
//        //The player inventory
//        for (m = 0; m < 3; ++m) {
//            for (l = 0; l < 9; ++l) {
//                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
//            }
//        }
//        //The player Hotbar
//        for (m = 0; m < 9; ++m) {
//            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
//        }

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
