package com.xuul.soulsmith.util;

import com.xuul.soulsmith.registry.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class BronzeToolMaterial implements ToolMaterial {

    /*Bronze tools are intended to be similar to iron but much more enchantable*/

    public static final BronzeToolMaterial INSTANCE = new BronzeToolMaterial();

    @Override
    public int getDurability() {
        return 320;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6.0F;
    }

    @Override
    public float getAttackDamage() {
        return 0F;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 18;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.INGOT_BRONZE, ModItems.BLOCK_BRONZE);
    }


}
