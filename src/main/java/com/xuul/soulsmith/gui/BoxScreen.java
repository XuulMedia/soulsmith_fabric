package com.xuul.soulsmith.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;


//LIBGUI

public class BoxScreen extends CottonInventoryScreen<BoxGuiDescription> {
    public BoxScreen(BoxGuiDescription gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }


}

//

//
//
//
//    //A path to the gui texture. In this example we use the texture from the dispenser
//    private static final Identifier TEXTURE = new Identifier("minecraft", "textures/gui/container/dispenser.png");
//
//    public BoxScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
//        super(handler, inventory, title);
//    }
//
//    @Override
//    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
//        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//        client.getTextureManager().bindTexture(TEXTURE);
//        int x = (width - backgroundWidth) / 2;
//        int y = (height - backgroundHeight) / 2;
//        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
//    }
//
//    @Override
//    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
//        renderBackground(matrices);
//        super.render(matrices, mouseX, mouseY, delta);
//        drawMouseoverTooltip(matrices, mouseX, mouseY);
//    }
//
//    @Override
//    protected void init() {
//        super.init();
//        // Center the title
//        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
//    }
//}