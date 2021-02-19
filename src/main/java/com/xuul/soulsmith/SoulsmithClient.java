package com.xuul.soulsmith;

import com.xuul.soulsmith.registry.GuiRegistry;
import net.fabricmc.api.ClientModInitializer;

public class SoulsmithClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        GuiRegistry.registerClient();

    }
}
