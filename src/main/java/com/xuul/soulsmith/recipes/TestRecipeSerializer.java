package com.xuul.soulsmith.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;

public class TestRecipeSerializer implements  RecipeSerializer<TestRecipe> {


    private TestRecipeSerializer() {
    }

    public static final TestRecipeSerializer INSTANCE = new TestRecipeSerializer();

    // This will be the "type" field in the json
    public static final Identifier ID = new Identifier(MOD_ID + ":test_recipe");



    /*TODO add validation to this code*/
    // Turns json into Recipe
    public TestRecipe read(Identifier identifier, JsonObject json) {
        TestRecipeJsonFormat recipeJson = new Gson().fromJson(json, TestRecipeJsonFormat.class);

        // Ingredient easily turns JsonObjects of the correct format into Ingredients
        Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
        Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);

        // This pair of lines first uses the JSON to specifiy the output item (THE STRING) then the output amount
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem)).get();
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new TestRecipe(identifier, inputA, inputB, output);

    }

    // Turns PacketByteBuf into Recipe
    public TestRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
        // Make sure the read in the same order you have written!
        Ingredient inputA = Ingredient.fromPacket(packetByteBuf);
        Ingredient inputB = Ingredient.fromPacket(packetByteBuf);
        ItemStack output = packetByteBuf.readItemStack();
        return new TestRecipe(identifier, inputA, inputB, output);
    }

    @Override
    public void write(PacketByteBuf buf, TestRecipe recipe) {
        recipe.getInputA().write(buf);
        recipe.getInputB().write(buf);
        buf.writeItemStack(recipe.getOutput());
    }


    class TestRecipeJsonFormat {
        JsonObject inputA;
        JsonObject inputB;
        String outputItem;
        int outputAmount;
    }

}

