package com.xuul.soulsmith.recipes;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.lang.reflect.Type;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;

public class AlloyRecipe implements Recipe<Inventory> {

    public static final Identifier ID = new Identifier(MOD_ID, "alloy_smelter_recipe");

    private final Ingredient inputA;
    private final Ingredient inputB;
    private int amountA;
    private int amountB;
    private final ItemStack output;
    private final Identifier id;

    public AlloyRecipe(Identifier id, ItemStack output, Ingredient inputA, Ingredient inputB, int amountA, int amountB) {
        this.id = id;
        this.inputA = inputA;
        this.inputB = inputB;
        this.amountA = amountA;
        this.amountB = amountB;
        this.output = output;
    }



    @Override
    public boolean matches(Inventory inv, World world) {
        if (inv.size() < 3)
            return false;
        return (inputA.test(inv.getStack(0)) && inv.getStack(0).getCount() >= amountA
                && inputB.test(inv.getStack(1)) && inv.getStack(1).getCount() >= amountB)
                || (inputA.test(inv.getStack(1)) && inv.getStack(1).getCount() >= amountA
                && inputB.test(inv.getStack(0)) && inv.getStack(0).getCount() >= amountB);
    }

    @Override
    public ItemStack craft(Inventory inv) {
        inv.removeStack(0, amountA);
        inv.removeStack(1, amountB);
        return getOutput();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }


    public Ingredient getInputA() {
        return this.inputA;
    }

    public Ingredient getInputB() {
        return this.inputB;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AlloyRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return AlloyRecipeType.INSTANCE;
    }




    public static class AlloyRecipeSerializer implements RecipeSerializer<AlloyRecipe> {
        public static final AlloyRecipeSerializer INSTANCE = new AlloyRecipeSerializer();

        private AlloyRecipeSerializer() {
        }


        @Override
        public AlloyRecipe read(Identifier id, JsonObject json) {

                AlloyRecipeJsonFormat recipeJson = new Gson().fromJson(json, AlloyRecipeJsonFormat.class);
                if ((recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.outputItem == null)) {
                    throw new JsonSyntaxException("A required attribute is missing!");
                }
                if (recipeJson.amountA <= 0)
                    recipeJson.amountA = 1;
                if (recipeJson.amountB <= 0)
                    recipeJson.amountB = 1;
                if (recipeJson.outputAmount <= 0)
                    recipeJson.outputAmount = recipeJson.amountA + recipeJson.amountB;

                Ingredient in1 = Ingredient.fromJson(recipeJson.inputA);
                Ingredient in2 = Ingredient.fromJson(recipeJson.inputB);

                if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

                Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                        // Validate the inputted item actually exists
                        .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
                ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);


                return new AlloyRecipe(id, output, in1, in2, recipeJson.amountA, recipeJson.amountB);
        }

        @Override
        public AlloyRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredientA = Ingredient.fromPacket(buf);
            Ingredient ingredientB = Ingredient.fromPacket(buf);
            int amountA = buf.readInt();
            int amountB = buf.readInt();
            ItemStack output = buf.readItemStack();
            return new AlloyRecipe(id, output, ingredientA, ingredientB, amountA, amountB);
        }

        @Override
        public void write(PacketByteBuf buf, AlloyRecipe recipe) {
            recipe.inputA.write(buf);
            recipe.inputB.write(buf);
            buf.writeInt(recipe.amountA);
            buf.writeInt(recipe.amountB);
            buf.writeItemStack(recipe.output);

        }
    }




    class AlloyRecipeJsonFormat {
        JsonObject inputA;
        JsonObject inputB;
        int amountA;
        int amountB;
        String outputItem;
        int outputAmount;
    }

    public static class AlloyRecipeType implements RecipeType<AlloyRecipe> {
        private AlloyRecipeType() {
        }

        public static final AlloyRecipeType INSTANCE = new AlloyRecipeType();
    }
}

