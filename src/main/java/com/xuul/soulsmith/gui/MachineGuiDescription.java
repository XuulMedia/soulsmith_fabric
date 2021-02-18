package com.xuul.soulsmith.gui;


import io.github.cottonmc.cotton.gui.SyncedGuiDescription;

import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.Soulsmith.MACHINE_SCREEN_HANDLER;

public class MachineGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 9;

    public MachineGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(MACHINE_SCREEN_HANDLER, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 1);
        root.add(itemSlot, 5, 1);
        root.add(itemSlot, 6, 1);
        root.add(itemSlot, 4, 2);
        root.add(itemSlot, 5, 2);
        root.add(itemSlot, 6, 2);
        root.add(itemSlot, 4, 3);
        root.add(itemSlot, 5, 3);
        root.add(itemSlot, 6, 3);


        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }
}
