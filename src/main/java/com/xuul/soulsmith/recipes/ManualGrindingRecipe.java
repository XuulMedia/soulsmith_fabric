package com.xuul.soulsmith.recipes;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static com.xuul.soulsmith.registry.ModRecipes.*;

public class ManualGrindingRecipe implements Recipe<Inventory> {

    private final Ingredient ingredient;
    private final Ingredient grindstone;
    private final ItemStack output;
    private final Identifier id;

    public ManualGrindingRecipe(Ingredient ingredient, Ingredient grindstone, ItemStack output, Identifier id){
        this.ingredient = ingredient;
        this.grindstone = grindstone;
        this.output = output;
        this.id = id;
    }

    public Ingredient getIngredient(){
        return this.ingredient;
    }

    public Ingredient getGrindstone() {
        return this.grindstone;
    }


    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (inventory.size() < 2) {
            return false;
        }
        return ingredient.test(inventory.getStack(0)) && grindstone.test(inventory.getStack(1));
    }

    /*TODO Damage the grindstone*/
    @Override
    public ItemStack craft(Inventory inv) {
        if (!this.ingredient.isEmpty() && !this.grindstone.isEmpty()) {
            inv.removeStack(0, 1);
            inv.getStack(1);
            return this.output.copy();
        }
        return ItemStack.EMPTY;
    }


    @Override
    public Identifier getId() {
        return ManualGrinderRecipe_ID;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ManualGrindingSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return MANUAL_GRINDER_RECIPE;
    }

}
