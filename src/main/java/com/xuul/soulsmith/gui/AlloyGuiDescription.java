package com.xuul.soulsmith.gui;

import com.xuul.soulsmith.recipes.AlloyRecipe;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.registry.GuiRegistry.ALLOY_SMELTER_SCREEN_HANDLER;

public class AlloyGuiDescription extends SyncedGuiDescription {





    /*TODO find out how to get the recipie to hook into this */

    public AlloyGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ALLOY_SMELTER_SCREEN_HANDLER, syncId, playerInventory,  getBlockInventory(context, 4), getBlockPropertyDelegate(context));


        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        WItemSlot inputA = WItemSlot.of(blockInventory, 0);
        WItemSlot inputB = WItemSlot.of(blockInventory, 1);
        WItemSlot fuel_slot = WItemSlot.of(blockInventory, 2);
        WItemSlot output_tile = WItemSlot.outputOf(blockInventory, 3);

        root.add(inputA, 2, 1);
        inputA.setLocation(inputA.getX() + 9, inputA.getY());
        root.add(inputB, 3, 1);
        inputB.setLocation(inputB.getX() + 9, inputB.getY());
        root.add(fuel_slot, 3, 2);
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





    public static class AlloySmelterScreen extends CottonInventoryScreen<AlloyGuiDescription> {
        public AlloySmelterScreen(AlloyGuiDescription description, PlayerEntity player, Text title) {
            super(description, player, title);
        }
    }






}
