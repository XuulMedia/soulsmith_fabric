package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.recipes.AlloyingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;

public class RecipeRegistry {
    public static RecipeType<AlloyingRecipe> ALLOYING;




    public static void initialize() {
        ALLOYING = RecipeType.register(Identifiers.ALLOY_ID.toString());

        RecipeSerializer.register(Identifiers.RECIPE_ALLOYING.toString(), AlloyingRecipe.SERIALIZER);

    }
}
