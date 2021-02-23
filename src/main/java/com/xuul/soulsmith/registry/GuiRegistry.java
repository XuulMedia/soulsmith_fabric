package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.gui.*;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

import static com.xuul.soulsmith.registry.Identifiers.ALLOY_ID;


public class GuiRegistry {

    public static ScreenHandlerType<AlloySmelterScreenHandler> ALLOY_SMELTER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(ALLOY_ID, AlloySmelterScreenHandler::new);;




    public static void register() {

        ScreenRegistry.<AlloySmelterScreenHandler, GenericDirectProcessorScreen<AlloySmelterScreenHandler>>register(
                ALLOY_SMELTER_SCREEN_HANDLER,
                (gui, inventory, title) -> new GenericDirectProcessorScreen<>(gui, inventory.player, title)
        );

    }

    public static void registerClient() {

        ScreenRegistry.<AlloySmelterScreenHandler, GenericDirectProcessorScreen<AlloySmelterScreenHandler>>register(
                ALLOY_SMELTER_SCREEN_HANDLER,
                (gui, inventory, title) -> new GenericDirectProcessorScreen<>(gui, inventory.player, title)
        );

    }
}
