package com.xuul.soulsmith.gui;

import com.xuul.soulsmith.blocks.entities.BoxBlockEntity;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class BoxGuiDescription extends SyncedGuiDescription {
    private static final Identifier TEST_MESSAGE = new Identifier("libgui", "test");
    private static final Identifier UNREGISTERED_ON_SERVER = new Identifier("libgui", "unregistered_on_server");

    public BoxGuiDescription(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, getBlockInventory(context, BoxBlockEntity.INVENTORY_SIZE), null);

        WGridPanel root = (WGridPanel) this.getRootPanel();

        root.add(new WLabel(new LiteralText("Box Block")), 0, 0);

        root.add(WItemSlot.of(blockInventory, 0, 3, 1), 2, 1);
        root.add(WItemSlot.of(blockInventory, 3, 3, 1), 2, 2);
        root.add(WItemSlot.of(blockInventory, 6, 3, 1), 2, 3);

//        WButton buttonA = new WButton(new LiteralText("Button A"));
//
//        buttonA.setOnClick(() -> {
//            ScreenNetworking.of(this, NetworkSide.CLIENT).send(TEST_MESSAGE, buf -> {});
//            ScreenNetworking.of(this, NetworkSide.CLIENT).send(UNREGISTERED_ON_SERVER, buf -> {});
//        });
//
//        root.add(buttonA, 0, 3, 4, 1);
//        root.add(new WButton(new LiteralText("Button B")), 5, 3, 4, 1);
//        root.add(new WButton(new LiteralText("Button C")), 0, 5, 4, 1);
//        root.add(new WButton(new LiteralText("Button D")), 5, 5, 4, 1);
//        root.add(new WTextField(new LiteralText("Type something...")), 0, 7, 5, 1);
//
//        root.add(new WLabel(new LiteralText("Large slot:")), 0, 9);
//        root.add(WItemSlot.outputOf(blockInventory, 0), 4, 9);

        root.add(createPlayerInventoryPanel(), 0, 4);


        this.getRootPanel().validate(this);

    }

    public static class BoxScreen extends CottonInventoryScreen<BoxGuiDescription> {
        public BoxScreen(BoxGuiDescription gui, PlayerEntity player, Text title) {
            super(gui, player, title);
        }


    }
}




