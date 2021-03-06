package com.xuul.soulsmith.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.registry.GuiRegistry.MANUAL_GRINDER_SCREEN_HANDLER;

public class ManualGrinderGui extends SyncedGuiDescription {


    public ManualGrinderGui(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(MANUAL_GRINDER_SCREEN_HANDLER, syncId, playerInventory, getBlockInventory(context, 3), getBlockPropertyDelegate(context));


        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        WItemSlot input = WItemSlot.of(blockInventory, 0);
        WItemSlot grindstone = WItemSlot.of(blockInventory, 1);
        WItemSlot output_tile = WItemSlot.outputOf(blockInventory, 2);

        root.add(input, 3, 0);
        root.add(grindstone, 3, 2);
        root.add(output_tile, 7, 1);

        root.add(createPlayerInventoryPanel(), 0, 4);

        root.validate(this);

    }

    @Override
    public @Nullable PropertyDelegate getPropertyDelegate() {
        return super.getPropertyDelegate();
    }

    public boolean canUse(PlayerEntity player) {
        return blockInventory.canPlayerUse(player);
    }


    public static class ManualGrinderScreen extends CottonInventoryScreen<ManualGrinderGui> {
        public ManualGrinderScreen(ManualGrinderGui description, PlayerEntity player, Text title) {
            super(description, player, title);
        }
    }
}


