
package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.recipes.AlloyRecipe;
import com.xuul.soulsmith.recipes.AlloyRecipeSerializer;
import com.xuul.soulsmith.recipes.ManualGrindingRecipe;
import com.xuul.soulsmith.recipes.ManualGrindingSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {

//Recipe ID's
    public static final Identifier AlloyRecipe_ID = new Identifier("soulsmith:alloy_recipe");
    public static final Identifier ManualGrinderRecipe_ID = new Identifier("soulsmith:manual_grinder_recipe");


    public static RecipeType<AlloyRecipe> ALLOY_RECIPE;
    public static RecipeType<ManualGrindingRecipe> MANUAL_GRINDER_RECIPE;


    public static RecipeSerializer<AlloyRecipe> ALLOY_RECIPE_SERIALIZER;


    public static void register() {

        ALLOY_RECIPE = RecipeType.register("soulsmith:alloy_recipe");
        MANUAL_GRINDER_RECIPE = RecipeType.register("soulsmith:manual_grinder_recipe");

        Registry.register(Registry.RECIPE_SERIALIZER, AlloyRecipe_ID, AlloyRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, ManualGrinderRecipe_ID, ManualGrindingSerializer.INSTANCE);


    }
}
