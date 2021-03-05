package com.xuul.soulsmith.util;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class ModAxe extends AxeItem {
    public ModAxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
