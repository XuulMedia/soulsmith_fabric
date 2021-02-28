package com.xuul.soulsmith.recipes;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

import java.io.IOException;

public enum AlloyRecipeSerializer implements  RecipeSerializer<AlloyRecipe> {
    INSTANCE;

    @Override
    // Turns json into Recipe
    public AlloyRecipe read(Identifier id, JsonObject json) {
        return AlloyRecipe.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow(false, System.err::println).getFirst();
    }

    @Override
    public AlloyRecipe read(Identifier id, PacketByteBuf buf) {
        AlloyRecipe decode = null;
        try {
            decode = buf.decode(AlloyRecipe.CODEC);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decode;
    }

    @Override
    public void write(PacketByteBuf buf, AlloyRecipe recipe) {
        try {
            buf.encode(AlloyRecipe.CODEC, recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

