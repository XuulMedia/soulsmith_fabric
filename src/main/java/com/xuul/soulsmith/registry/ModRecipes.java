
package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.recipes.AlloyRecipe;
import com.xuul.soulsmith.recipes.AlloyRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;

public class ModRecipes {


    public static RecipeType<AlloyRecipe> ALLOY_RECIPE;


    public static RecipeSerializer<AlloyRecipe> ALLOY_RECIPE_SERIALIZER;




    public static void register() {

        ALLOY_RECIPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, "alloy_recipe"), new RecipeType<AlloyRecipe>() {
            @Override
            public String toString() {
                return "alloy_recipe";
            }
        });



        ALLOY_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, AlloyRecipeSerializer.ID,
                AlloyRecipeSerializer.INSTANCE);

    }
}
