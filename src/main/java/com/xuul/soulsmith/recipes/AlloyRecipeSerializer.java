package com.xuul.soulsmith.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

public class AlloyRecipeSerializer<T extends AlloyRecipe> implements RecipeSerializer<T> {

    private final int processingTime;
    private final Factory<T> factory;

    public AlloyRecipeSerializer(int processingTime, Factory<T> factory) {
        this.processingTime = processingTime;
        this.factory = factory;
    }

    public T read(Identifier identifier, JsonObject jsonObject) {
        String string = JsonHelper.getString(jsonObject, "group", "");
        JsonElement jsonElementA = JsonHelper.hasArray(jsonObject, "ingredientA") ? JsonHelper.getArray(jsonObject, "ingredientA") : JsonHelper.getObject(jsonObject, "ingredienAt");
        Ingredient ingredientA = Ingredient.fromJson(jsonElementA);
        JsonElement jsonElementB = JsonHelper.hasArray(jsonObject, "ingredientB") ? JsonHelper.getArray(jsonObject, "ingredientB") : JsonHelper.getObject(jsonObject, "ingredientB");
        Ingredient ingredientB = Ingredient.fromJson(jsonElementB);
        JsonObject resultObject = jsonObject.getAsJsonObject("result");
        String resultString = JsonHelper.getString(resultObject, "item");
        Identifier result = new Identifier(resultString);
        int count = JsonHelper.getInt(resultObject, "count", 1);
        ItemStack itemStack = new ItemStack(Registry.ITEM.getOrEmpty(result).orElseThrow(() ->
                new IllegalStateException("Item: " + resultString + " does not exist")
        ), count);
        int i = JsonHelper.getInt(jsonObject, "time", this.processingTime);
        return factory.create(identifier, string, ingredientA, ingredientB, itemStack, i);
    }

    public T read(Identifier identifier, PacketByteBuf packetByteBuf) {
        String string = packetByteBuf.readString(32767);
        Ingredient ingredientA = Ingredient.fromPacket(packetByteBuf);
        Ingredient ingredientB = Ingredient.fromPacket(packetByteBuf);
        ItemStack itemStack = packetByteBuf.readItemStack();
        int i = packetByteBuf.readVarInt();
        return factory.create(identifier, string, ingredientA, ingredientB, itemStack, i);
    }

    public void write(PacketByteBuf packetByteBuf, AlloyRecipe alloyRecipe) {
        packetByteBuf.writeString(alloyRecipe.group);
        alloyRecipe.inputA.write(packetByteBuf);
        packetByteBuf.writeItemStack(alloyRecipe.output);
        packetByteBuf.writeVarInt(alloyRecipe.processingTime);
    }


    public interface Factory<T> {
        T create(Identifier id, String group, Ingredient inputA, Ingredient inputB, ItemStack output, int processingTime);
    }


}
