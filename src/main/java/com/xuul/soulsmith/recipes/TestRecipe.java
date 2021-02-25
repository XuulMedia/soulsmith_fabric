package com.xuul.soulsmith.recipes;

import com.google.gson.JsonObject;
import com.xuul.soulsmith.registry.RecipeRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;



public class TestRecipe implements Recipe<Inventory> {
    //You can add as much inputs as you want here.
    //It is important to always use Ingredient, so you can support tags.
    private final Ingredient inputA;
    private final Ingredient inputB;
    private final ItemStack output;
    private final Identifier id;

    public TestRecipe(Identifier id, Ingredient inputA, Ingredient inputB, ItemStack output) {
        this.id = id;
        this.inputA = inputA;
        this.inputB = inputB;
        this.output = output;
    }

    public Ingredient getInputA() {
        return this.inputA;
    }

    public Ingredient getInputB() {
        return this.inputB;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }



//    USED TO ACTUALLY CRAFT THE ITEM. RETURN A COPY OF GET OUTPUT

    @Override
    public ItemStack craft(Inventory inv) {return this.getOutput().copy();}

    @Override
    public boolean matches(Inventory inv, World world) {
        if(inv.size()< 2) return false;
        return  inputA.test(inv.getStack(0)) && inputA.test(inv.getStack(1));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean fits(int width, int height) {
        return true;
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




    @Override
    public RecipeSerializer<?> getSerializer() {
        return TestRecipeSerializer.INSTANCE;
    }













}

