package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.gui.AlloySmelterScreen;
import com.xuul.soulsmith.gui.AlloySmelterScreenHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

import static com.xuul.soulsmith.registry.Identifiers.ALLOY_ID;


public class GuiRegistry {
    public static ScreenHandlerType<AlloySmelterScreenHandler> ALLOY_SMELTER_SCREEN_HANDLER;



    public static void register() {
        ALLOY_SMELTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ALLOY_ID, AlloySmelterScreenHandler::new);


    }

    public static void registerClient() {
        ScreenRegistry.register(ALLOY_SMELTER_SCREEN_HANDLER, AlloySmelterScreen::new);


    }
}
