package com.xuul.soulsmith.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;

public class AlphaGui extends LightweightGuiDescription {

    public AlphaGui() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300,200);

        WLabel label = new WLabel("Hello World");
        root.add(label,1,1);
    }

    public static class AlphaScreen extends CottonClientScreen {
        public AlphaScreen(GuiDescription description) {
            super(description);
        }
    }
}
