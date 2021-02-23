package com.xuul.soulsmith.recipes;

import com.xuul.soulsmith.registry.RecipeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class AlloyingRecipe extends ProcessingRecipe {
    public static final ProcessingRecipeSerializer<AlloyingRecipe> SERIALIZER = new ProcessingRecipeSerializer<>(200, AlloyingRecipe::new);

    public AlloyingRecipe(
            Identifier id,
            String group,
            Ingredient input,
            ItemStack output,
            int processingTime
    ) {
        super(RecipeRegistry.ALLOYING, id, group, input, output, processingTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}
