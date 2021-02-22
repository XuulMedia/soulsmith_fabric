package com.xuul.soulsmith.gui;

import com.xuul.soulsmith.recipes.AlloyRecipe;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;
import static com.xuul.soulsmith.gui.Components.*;


public class AlloySmelterGui extends SyncedGuiDescription {



    protected final World world;
    protected final RecipeType<? extends AlloyRecipe> recipeType;

    protected AlloySmelterGui(ScreenHandlerType<?> type, RecipeType<? extends AlloyRecipe> recipeType, int syncId, PlayerInventory playerInventory) {
        this(type, recipeType, syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(4));
    }

    public AlloySmelterGui(ScreenHandlerType<?> type, RecipeType<? extends AlloyRecipe> recipeType, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId, playerInventory, inventory, propertyDelegate);
        checkSize(inventory, 2);
        checkDataCount(propertyDelegate, 2);
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.world;
        this.recipeType = recipeType;

        WPlainPanel root = new WPlainPanel();
        setRootPanel(root);
        root.setSize(150, 100);

        WItemSlot inputSlot = WItemSlot.of(inventory, 0);
        WItemSlot outputSlot = WItemSlot.of(inventory, 1);
        root.add(inputSlot, 18 * 2, 18);
        root.add(outputSlot, 18 * 6, 18);

        WBar bar = new WBar(
                new Identifier(MOD_ID, "textures/gui/arrow_empty.png"),
                new Identifier(MOD_ID, "textures/gui/arrow_full.png"),
                0, 1, WBar.Direction.RIGHT
        );

        root.add(bar, 18 * 4 - 2, 18);
        bar.setSize(22, 17);

        root.add(createPlayerInventoryPanel(), 0, 18 * 3);

        root.validate(this);
    }

    @Override
    public @Nullable PropertyDelegate getPropertyDelegate() {
        return super.getPropertyDelegate();
    }

    public boolean canUse(PlayerEntity player) {
        return blockInventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot != null && slot.hasStack()) {
            ItemStack slotStack = slot.getStack();
            result = slotStack.copy();
            if (index == 1) {
                if (!insertItem(slotStack, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onStackChanged(slotStack, result);
            } else if (index != 0) {
                if (isProcessable(slotStack)) {
                    if (!insertItem(slotStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 2 && index < 29) {
                    if (!insertItem(slotStack, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 29 && index < 38 && !insertItem(slotStack, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!insertItem(slotStack, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (slotStack.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, slotStack);
        }

        return result;
    }

    protected boolean isProcessable(ItemStack itemStack) {
        return world.getRecipeManager().getFirstMatch(recipeType, new SimpleInventory(itemStack), world).isPresent();
    }


//
//    WBar fire;
//    WBar progress;
//
////    public AlloyFurnaceContainer(int syncID, PlayerInventory playerInventory, ScreenHandlerContext context) {
////        super(MRegister.ALLOY_FURNACE_CONTAINER, syncID, playerInventory, getBlockInventory(context),
////                getBlockPropertyDelegate(context));
//
////
////    protected GenericDirectProcessorScreenHandler(ScreenHandlerType<?> type, RecipeType<? extends ProcessingRecipe> recipeType, int syncId, PlayerInventory playerInventory) {
////        this(type, recipeType, syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(4));
//    }
//
//    public AlloySmelterGui(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
//        super(type, syncId, playerInventory, new SimpleInventory(4),  new ArrayPropertyDelegate(4));
//
//
//        WGridPanel root = new WGridPanel();
//        setRootPanel(root);
//
//        WItemSlot inputA = WItemSlot.of(blockInventory, 0);
//        WItemSlot inputB = WItemSlot.of(blockInventory, 1);
//        WItemSlot fuel_slot = WItemSlot.of(blockInventory, 2);
//        WItemSlot output_tile = WItemSlot.outputOf(blockInventory, 3);
//
//        root.add(inputA, 2, 0);
//        inputA.setLocation(inputA.getX() + 9, inputA.getY());
//        root.add(inputB, 3, 0);
//        inputB.setLocation(inputB.getX() + 9, inputB.getY());
//        root.add(fuel_slot, 3, 2);
//        root.add(output_tile, 7, 1);
//
////        fire = new WBar(new Identifier(MOD_ID, "textures/gui/furnace_flames_bg.png"), new Identifier(MOD_ID, "textures/gui/furnace_flames.png"), 1, 2, WBar.Direction.UP);
////        root.add(fire, 3, 1);
////
////        progress = new WBar(PROGRESS_BAR_BG, PROGRESS_BAR, 0, 3, WBar.Direction.RIGHT);
////        root.add(progress,5,1);
//
//        root.add(createPlayerInventoryPanel(), 0, 4);
//
//
//        root.validate(this);
//    }
//
//    public static class AlloySmelterScreen extends CottonInventoryScreen<AlloySmelterGui> {
//        public AlloySmelterScreen(AlloySmelterGui gui, PlayerEntity player, Text title) {
//            super(gui, player, title);
//        }


}


