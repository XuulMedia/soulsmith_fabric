package com.xuul.soulsmith.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;


public class MachineScreen extends CottonInventoryScreen<MachineGuiDescription> {
    public MachineScreen(MachineGuiDescription gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}

