package com.xuul.soulsmith.util;

import com.xuul.soulsmith.registry.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class SteelToolMaterial implements ToolMaterial {

    /*Steel tools are intended to be high durability long lasting with high mining level*/

    public static final SteelToolMaterial INSTANCE = new SteelToolMaterial();

    @Override
    public int getDurability() {
        return 2700 ;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7.0F;
    }

    @Override
    public float getAttackDamage() {
        return 0F;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 8;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.INGOT_STEEL, ModItems.BLOCK_STEEL);
    }


}
