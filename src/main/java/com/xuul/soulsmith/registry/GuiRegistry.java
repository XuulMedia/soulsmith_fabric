package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.gui.BoxScreen;
import com.xuul.soulsmith.gui.BoxScreenHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

import static com.xuul.soulsmith.registry.ModBlocks.BOXID;

public class GuiRegistry {

    public static ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER = null;

    public static void register() {

        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOXID, BoxScreenHandler::new);
    }

    public static void registerClient() {
        ScreenRegistry.register(BOX_SCREEN_HANDLER, BoxScreen::new);

    }
}
