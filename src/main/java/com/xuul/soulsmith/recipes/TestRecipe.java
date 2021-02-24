package com.xuul.soulsmith.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;



public class TestRecipe implements Recipe<CraftingInventory> {
    //You can add as much inputs as you want here.
    //It is important to always use Ingredient, so you can support tags.
    private final Ingredient inputA;
    private final Ingredient inputB;
    private final ItemStack result;
    private final Identifier id;

    public TestRecipe(Identifier id, ItemStack result, Ingredient inputA, Ingredient inputB) {
        this.id = id;
        this.inputA = inputA;
        this.inputB = inputB;
        this.result = result;
    }

    public Ingredient getInputA() {
        return this.inputA;
    }

    public Ingredient getInputB() {
        return this.inputB;
    }

    @Override
    public ItemStack getOutput() {
        return this.result;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }



//    USED TO ACTUALLY CRAFT THE ITEM. RETURN A COPY OF GET OUTPUT

    @Override
    public ItemStack craft(CraftingInventory inv) {
        return this.getOutput().copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public boolean matches(CraftingInventory inv, World world) {
        if(inv.size()< 2) return false;
        return  inputA.test(inv.getStack(0)) && inputA.test(inv.getStack(1));
    }

    public static class TestRecipeType implements RecipeType<TestRecipe>{
        private TestRecipeType(){}
        public static final TestRecipeType INSTANCE = new TestRecipeType();
        public static final String ID = "test_recipe";
    }


    @Override
    public RecipeType<?> getType() {
        return TestRecipeType.INSTANCE;
    }







}

