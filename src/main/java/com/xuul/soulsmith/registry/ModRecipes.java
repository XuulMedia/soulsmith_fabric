
package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.recipes.AlloyRecipe;
import com.xuul.soulsmith.recipes.AlloyRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {


    public static final Identifier AlloyRecipe_ID = new Identifier("soulsmith:alloy_recipe");
    public static RecipeType<AlloyRecipe> ALLOY_RECIPE;


    public static RecipeSerializer<AlloyRecipe> ALLOY_RECIPE_SERIALIZER;


    public static void register() {

        ALLOY_RECIPE = RecipeType.register("soulsmith:alloy_recipe");
        Registry.register(Registry.RECIPE_SERIALIZER, AlloyRecipe_ID, AlloyRecipeSerializer.INSTANCE);


    }
}
