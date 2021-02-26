package com.xuul.soulsmith;

import com.xuul.soulsmith.registry.GuiRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class SoulsmithClient implements ClientModInitializer {

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        GuiRegistry.registerClient();

    }
}
