package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.gui.BoxGuiDescription;
import com.xuul.soulsmith.gui.BoxScreen;
import com.xuul.soulsmith.gui.BoxScreenHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

import javax.swing.*;

import static com.xuul.soulsmith.registry.ModBlocks.BOXID;

public class GuiRegistry {

    public static ScreenHandlerType<BoxGuiDescription> BOX_SCREEN_HANDLER;




    public static void register() {



        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOXID, (int syncId, PlayerInventory inventory) -> {
            return new BoxGuiDescription(BOX_SCREEN_HANDLER, syncId, inventory, ScreenHandlerContext.EMPTY);
        });

//        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOXID, BoxGuiDescription::new);
    }

    public static void registerClient() {

        ScreenRegistry.<BoxGuiDescription, BoxScreen>register(BOX_SCREEN_HANDLER, (gui, inventory, title) ->
                new BoxScreen(gui, inventory.player, title));

//        ScreenRegistry.register(BOX_SCREEN_HANDLER, BoxScreen::new);

    }
}
