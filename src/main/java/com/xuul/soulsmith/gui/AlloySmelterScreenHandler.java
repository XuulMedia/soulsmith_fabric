package com.xuul.soulsmith.gui;

import com.xuul.soulsmith.registry.GuiRegistry;
import com.xuul.soulsmith.registry.RecipeRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;

public class AlloySmelterScreenHandler extends GenericDirectProcessorScreenHandler {
    public AlloySmelterScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(GuiRegistry.ALLOY_SMELTER_SCREEN_HANDLER, RecipeRegistry.ALLOYING, syncId, playerInventory);
    }

    public AlloySmelterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(GuiRegistry.ALLOY_SMELTER_SCREEN_HANDLER, RecipeRegistry.ALLOYING, syncId, playerInventory, inventory, propertyDelegate);
    }
}
