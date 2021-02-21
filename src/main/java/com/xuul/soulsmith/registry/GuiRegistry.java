package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.gui.*;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

import static com.xuul.soulsmith.registry.ModBlocks.ALLOYID;
import static com.xuul.soulsmith.registry.ModBlocks.BOXID;

public class GuiRegistry {

    public static ScreenHandlerType<BoxGuiDescription> BOX_SCREEN_HANDLER;
    public static ScreenHandlerType<AlloySmelterGui> ALLOY_SMELTER_SCREEN_HANDLER;




    public static void register() {

        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOXID, (int syncId, PlayerInventory inventory) -> {
            return new BoxGuiDescription(BOX_SCREEN_HANDLER, syncId, inventory, ScreenHandlerContext.EMPTY);
        });

        ALLOY_SMELTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ALLOYID, (int syncId, PlayerInventory inventory) -> {
            return new AlloySmelterGui(ALLOY_SMELTER_SCREEN_HANDLER, syncId, inventory, ScreenHandlerContext.EMPTY);
        });


    }

    public static void registerClient() {

        ScreenRegistry.<BoxGuiDescription, BoxGuiDescription.BoxScreen>register(BOX_SCREEN_HANDLER, (gui, inventory, title) ->
                new BoxGuiDescription.BoxScreen(gui, inventory.player, title));


        ScreenRegistry.<AlloySmelterGui, AlloySmelterGui.AlloySmelterScreen>register(ALLOY_SMELTER_SCREEN_HANDLER, (gui, inventory, title) ->
                new AlloySmelterGui.AlloySmelterScreen(gui, inventory.player, title));



    }
}
