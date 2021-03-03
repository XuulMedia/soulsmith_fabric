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

public class AlloyRecipeSerializer implements RecipeSerializer<AlloyRecipe> {

    public AlloyRecipe read(Identifier id, JsonObject json) {
        AlloyRecipeJsonFormat recipeJson = new Gson().fromJson(json, AlloyRecipeJsonFormat.class);
        if (recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
        Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
//        If any amount is set to zero default it to 1
        if (recipeJson.amountA == 0) recipeJson.amountA = 1;
        if (recipeJson.amountB == 0) recipeJson.amountB = 1;
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;
        int amountA = recipeJson.amountA;
        int amountB = recipeJson.amountB;


        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                // Validate the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);
        return new AlloyRecipe(inputA, inputB, amountA, amountB, output, id);
    }


    @Override
    public AlloyRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient inputA = Ingredient.fromPacket(buf);
        Ingredient inputB = Ingredient.fromPacket(buf);
        int amountA = buf.readInt();
        int amountB = buf.readInt();
        ItemStack output = buf.readItemStack();
        return new AlloyRecipe(inputA, inputB, amountA, amountB, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, AlloyRecipe recipe) {
        recipe.getInputA().write(buf);
        recipe.getInputB().write(buf);
        buf.writeInt(recipe.getAmountA());
        buf.writeInt(recipe.getAmountB());
        buf.writeItemStack(recipe.getOutput());

    }

    private AlloyRecipeSerializer() {
    }

    public static final AlloyRecipeSerializer INSTANCE = new AlloyRecipeSerializer();
}

    class AlloyRecipeJsonFormat {
        JsonObject inputA;
        JsonObject inputB;
        int amountA;
        int amountB;
        String outputItem;
        int outputAmount;
    }

