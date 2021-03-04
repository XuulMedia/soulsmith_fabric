package com.xuul.soulsmith.recipes;


import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static com.xuul.soulsmith.registry.ModRecipes.ALLOY_RECIPE;
import static com.xuul.soulsmith.registry.ModRecipes.AlloyRecipe_ID;


public class AlloyRecipe implements Recipe<Inventory> {

    private final Ingredient inputA;
    private final Ingredient inputB;
    private final int amountA;
    private final int amountB;
    private final ItemStack output;
    private final Identifier id;


    public AlloyRecipe(Ingredient inputA, Ingredient inputB, int amountA, int amountB, ItemStack output, Identifier id) {
        this.inputA = inputA;
        this.inputB = inputB;
        this.amountA = amountA;
        this.amountB = amountB;
        this.output = output;
        this.id= id;
    }

    public Ingredient getInputA() {
        return this.inputA;
    }

    public Ingredient getInputB() {
        return this.inputB;
    }

    public int getAmountA() {
        return amountA;
    }

    public int getAmountB() {
        return amountB;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (inventory.size() < 2) {
            return false;
        }
        return inputA.test(inventory.getStack(0)) && inputB.test(inventory.getStack(1));
    }


    @Override
    public ItemStack craft(Inventory inv) {
        if (!this.inputA.isEmpty() && !this.inputB.isEmpty()) {
            inv.removeStack(0, this.amountA);
            inv.removeStack(1, this.amountB);
            return this.output.copy();
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return AlloyRecipe_ID;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AlloyRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return ALLOY_RECIPE;
    }


}
