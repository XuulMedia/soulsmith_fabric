package com.xuul.soulsmith.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.ScreenHandler;

import static com.xuul.soulsmith.registry.GuiRegistry.ALLOY_SMELTER_SCREEN_HANDLER;

public class AlloySmelterScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    public AlloySmelterScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(4));
    }

    public AlloySmelterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ALLOY_SMELTER_SCREEN_HANDLER, syncId);
        this.inventory = inventory;

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

}
