package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.recipes.TestRecipe;
import com.xuul.soulsmith.recipes.TestRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;

public class ModRecipes {


    public static RecipeType<TestRecipe> TEST_RECIPE_TYPE;



    public static RecipeSerializer<TestRecipe> TEST_RECIPE_SERIALIZER;




    public static void register() {


        TEST_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, TestRecipeSerializer.ID,
                TestRecipeSerializer.INSTANCE);


        TEST_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, TestRecipe.TestRecipeType.ID), TestRecipe.TestRecipeType.INSTANCE);


    }
}
