package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.gui.AlloyGuiDescription;


import com.xuul.soulsmith.gui.ManualGrinderGui;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

import static com.xuul.soulsmith.registry.Identifiers.ALLOY_ID;
import static com.xuul.soulsmith.registry.Identifiers.MANUAL_GRINDER_ID;


public class GuiRegistry {

    /*VANILLA*/
//    public static ScreenHandlerType<AlloySmelterScreenHandler> ALLOY_SMELTER_SCREEN_HANDLER;

    /*LIBGUI*/
    public static ScreenHandlerType<AlloyGuiDescription> ALLOY_SMELTER_SCREEN_HANDLER;
    public static ScreenHandlerType<ManualGrinderGui> MANUAL_GRINDER_SCREEN_HANDLER;





    public static void register() {

        /*VANILLA*/
//        ALLOY_SMELTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ALLOY_ID, AlloySmelterScreenHandler::new);

        /*LIBGUI*/
        ALLOY_SMELTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ALLOY_ID, (int syncId, PlayerInventory inventory) ->
                new AlloyGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY));

        MANUAL_GRINDER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(MANUAL_GRINDER_ID, (int syncId, PlayerInventory inventory) ->
                new ManualGrinderGui(syncId, inventory, ScreenHandlerContext.EMPTY));

    }

    public static void registerClient() {
        /*Vanilla*/
//        ScreenRegistry.register(ALLOY_SMELTER_SCREEN_HANDLER, AlloySmelterScreen::new);

        /*LIBGUI*/
        ScreenRegistry.<AlloyGuiDescription, AlloyGuiDescription.AlloySmelterScreen>register(ALLOY_SMELTER_SCREEN_HANDLER, (gui, inventory, title) ->
                new AlloyGuiDescription.AlloySmelterScreen(gui, inventory.player, title));

        ScreenRegistry.<ManualGrinderGui, ManualGrinderGui.ManualGrinderScreen>register(MANUAL_GRINDER_SCREEN_HANDLER, (gui, inventory, title) ->
                new ManualGrinderGui.ManualGrinderScreen(gui, inventory.player, title));







    }
}
