package com.xuul.soulsmith.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class GenericDirectProcessorScreen<T extends SyncedGuiDescription> extends CottonInventoryScreen<T> {
    public GenericDirectProcessorScreen(T description, PlayerEntity player, Text title) {
        super(description, player, title);
    }
}
