package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.recipes.TestRecipe;
import com.xuul.soulsmith.recipes.TestRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeRegistry {


    public static void register() {

        Registry.register(Registry.RECIPE_SERIALIZER, TestRecipeSerializer.ID,
                TestRecipeSerializer.INSTANCE);
        

        Registry.register(Registry.RECIPE_TYPE, new Identifier("test_recipe", TestRecipe.TestRecipeType.ID), TestRecipe.TestRecipeType.INSTANCE);



    }
}
