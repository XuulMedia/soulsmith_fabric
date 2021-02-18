package com.xuul.soulsmith;

import com.xuul.soulsmith.gui.MachineGuiDescription;
import com.xuul.soulsmith.gui.MachineScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class SoulsmithClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.<MachineGuiDescription, MachineScreen>register(Soulsmith.MACHINE_SCREEN_HANDLER, (gui, inventory, title) ->
                new MachineScreen(gui, inventory.player, title));

    }
}
