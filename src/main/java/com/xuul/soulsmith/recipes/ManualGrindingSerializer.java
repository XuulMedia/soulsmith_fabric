package com.xuul.soulsmith.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ManualGrindingSerializer implements RecipeSerializer<ManualGrindingRecipe> {
    @Override
    public ManualGrindingRecipe read(Identifier id, JsonObject json) {
        ManualGrindingRecipeJsonFormat recipeJson = new Gson().fromJson(json, ManualGrindingRecipeJsonFormat.class);
        if (recipeJson.ingredient == null || recipeJson.grindStone == null || recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        Ingredient ingredient = Ingredient.fromJson(recipeJson.ingredient);
        Ingredient grindStone = Ingredient.fromJson(recipeJson.grindStone);

        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
            ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new ManualGrindingRecipe(ingredient, grindStone, output, id);
    }


    @Override
    public ManualGrindingRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient ingredient = Ingredient.fromPacket(buf);
        Ingredient grindStone = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();

        return new ManualGrindingRecipe(ingredient, grindStone, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, ManualGrindingRecipe recipe) {
        recipe.getIngredient().write(buf);
        recipe.getGrindstone().write(buf);
        buf.writeItemStack(recipe.getOutput());
    }




    private ManualGrindingSerializer() {};

    public static final ManualGrindingSerializer INSTANCE = new ManualGrindingSerializer();
}

class ManualGrindingRecipeJsonFormat {
    JsonObject ingredient;
    JsonObject grindStone;
    String outputItem;
    int outputAmount;
}
