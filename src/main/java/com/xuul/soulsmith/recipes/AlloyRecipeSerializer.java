package com.xuul.soulsmith.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AlloyRecipeSerializer implements  RecipeSerializer<AlloyRecipe> {


    private AlloyRecipeSerializer() {
    }

    public static final AlloyRecipeSerializer INSTANCE = new AlloyRecipeSerializer();

    // This will be the "type" field in the json
    public static final Identifier ID = new Identifier("soulsmith:alloy_recipe");



    /*TODO add validation to this code*/
    // Turns json into Recipe
    public AlloyRecipe read(Identifier identifier, JsonObject json) {
        AlloyRecipeJsonFormat recipeJson = new Gson().fromJson(json, AlloyRecipeJsonFormat.class);

        // Ingredient easily turns JsonObjects of the correct format into Ingredients
        Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
        Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);

        // This pair of lines first uses the JSON to specifiy the output item (THE STRING) then the output amount
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem)).get();
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new AlloyRecipe(identifier, inputA, inputB, output);

    }

    // Turns PacketByteBuf into Recipe
    public AlloyRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
        // Make sure the read in the same order you have written!
        Ingredient inputA = Ingredient.fromPacket(packetByteBuf);
        Ingredient inputB = Ingredient.fromPacket(packetByteBuf);
        ItemStack output = packetByteBuf.readItemStack();
        return new AlloyRecipe(identifier, inputA, inputB, output);
    }

    @Override
    public void write(PacketByteBuf buf, AlloyRecipe recipe) {
        recipe.getInputA().write(buf);
        recipe.getInputB().write(buf);
        buf.writeItemStack(recipe.getOutput());
    }


    class AlloyRecipeJsonFormat {
        JsonObject inputA;
        JsonObject inputB;
        String outputItem;
        int outputAmount;
    }



}

