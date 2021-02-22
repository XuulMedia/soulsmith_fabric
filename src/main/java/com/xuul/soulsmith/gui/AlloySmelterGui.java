package com.xuul.soulsmith.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;
import static com.xuul.soulsmith.gui.Components.*;


public class AlloySmelterGui extends SyncedGuiDescription {

    WBar fire;
    WBar progress;

//    public AlloyFurnaceContainer(int syncID, PlayerInventory playerInventory, ScreenHandlerContext context) {
//        super(MRegister.ALLOY_FURNACE_CONTAINER, syncID, playerInventory, getBlockInventory(context),
//                getBlockPropertyDelegate(context));

//
//    protected GenericDirectProcessorScreenHandler(ScreenHandlerType<?> type, RecipeType<? extends ProcessingRecipe> recipeType, int syncId, PlayerInventory playerInventory) {
//        this(type, recipeType, syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(4));
    }

    public AlloySmelterGui(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, new SimpleInventory(4),  new ArrayPropertyDelegate(4));


        WGridPanel root = new WGridPanel();
        setRootPanel(root);

        WItemSlot inputA = WItemSlot.of(blockInventory, 0);
        WItemSlot inputB = WItemSlot.of(blockInventory, 1);
        WItemSlot fuel_slot = WItemSlot.of(blockInventory, 2);
        WItemSlot output_tile = WItemSlot.outputOf(blockInventory, 3);

        root.add(inputA, 2, 0);
        inputA.setLocation(inputA.getX() + 9, inputA.getY());
        root.add(inputB, 3, 0);
        inputB.setLocation(inputB.getX() + 9, inputB.getY());
        root.add(fuel_slot, 3, 2);
        root.add(output_tile, 7, 1);

//        fire = new WBar(new Identifier(MOD_ID, "textures/gui/furnace_flames_bg.png"), new Identifier(MOD_ID, "textures/gui/furnace_flames.png"), 1, 2, WBar.Direction.UP);
//        root.add(fire, 3, 1);
//
//        progress = new WBar(PROGRESS_BAR_BG, PROGRESS_BAR, 0, 3, WBar.Direction.RIGHT);
//        root.add(progress,5,1);

        root.add(createPlayerInventoryPanel(), 0, 4);


        root.validate(this);
    }

    public static class AlloySmelterScreen extends CottonInventoryScreen<AlloySmelterGui> {
        public AlloySmelterScreen(AlloySmelterGui gui, PlayerEntity player, Text title) {
            super(gui, player, title);
        }

    }
}


