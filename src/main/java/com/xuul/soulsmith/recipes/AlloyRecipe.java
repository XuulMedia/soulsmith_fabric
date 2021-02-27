package com.xuul.soulsmith.recipes;

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



public class AlloyRecipe implements Recipe<Inventory> {
    //You can add as much inputs as you want here.
    //It is important to always use Ingredient, so you can support tags.
    private final Ingredient inputA;
    private final Ingredient inputB;
    private final ItemStack output;
    private final Identifier id;

    public AlloyRecipe(Identifier id, Ingredient inputA, Ingredient inputB, ItemStack output) {
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
        return this.output.copy();
    }

    @Override
    public Identifier getId() {
        return this.id;
    }



//    USED TO ACTUALLY CRAFT THE ITEM. RETURN A COPY OF GET OUTPUT

    @Override
    public ItemStack craft(Inventory inv) {return this.getOutput();}

    @Override
    public boolean matches(Inventory inv, World world) {
        if(inv.size()< 2) return false;
        return  inputA.test(inv.getStack(0)) && inputB.test(inv.getStack(1));
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean fits(int width, int height) {
        return true;
    }



    @Override
    public RecipeType<?> getType() {
        return AlloyRecipeType.INSTANCE;
    }




    @Override
    public RecipeSerializer<?> getSerializer() {
        return AlloyRecipeSerializer.INSTANCE;
    }



    public static class AlloyRecipeType implements RecipeType<AlloyRecipe>{
        private AlloyRecipeType(){}
        public static final AlloyRecipeType INSTANCE = new AlloyRecipeType();
        public static final String ID = "alloy_recipe";
    }

}

