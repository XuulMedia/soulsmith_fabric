package com.xuul.soulsmith.recipes;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;

public class AlloyRecipe implements Recipe<Inventory> {

    public static final Identifier ID = new Identifier(MOD_ID, "alloy_smelter_recipe");

    protected final RecipeType<? extends AlloyRecipe> type;
    protected final Identifier id;
    protected final String group;
    protected final Ingredient inputA;
    protected final Ingredient inputB;
    protected int amountA;
    protected int amountB;
    protected final ItemStack output;
    protected final int processingTime;

    public AlloyRecipe(RecipeType<? extends AlloyRecipe> type, Identifier id, String group, Ingredient inputA, Ingredient inputB, int amountA, int amountB, ItemStack output, int processingTime) {
        this.type = type;
        this.id = id;
        this.group = group;
        this.inputA = inputA;
        this.inputB = inputB;
        this.amountA = amountA;
        this.amountB = amountB;
        this.output = output;
        this.processingTime = processingTime;
    }



    public boolean matches(Inventory inv, World world) {
        if (inv.size() < 3)
            return false;
        return (inputA.test(inv.getStack(0)) && inv.getStack(0).getCount() >= amountA
                && inputB.test(inv.getStack(1)) && inv.getStack(1).getCount() >= amountB)
                || (inputA.test(inv.getStack(1)) && inv.getStack(1).getCount() >= amountA
                && inputB.test(inv.getStack(0)) && inv.getStack(0).getCount() >= amountB);
    }


    public ItemStack craft(Inventory inv) {
        inv.removeStack(0, amountA);
        inv.removeStack(1, amountB);
        return this.output.copy();
    }


    public Ingredient getInputA() {
        return this.inputA;
    }

    public Ingredient getInputB() {
        return this.inputB;
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return true;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    @Environment(EnvType.CLIENT)
    public String getGroup() {
        return this.group;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AlloyRecipeSerializer.SERIALIZER;
    }


    public RecipeType<?> getType() {
        return type;
    }

    public static class AlloyRecipeType implements RecipeType<AlloyRecipe> {
        private AlloyRecipeType() {
        }

        public static final AlloyRecipeType INSTANCE = new AlloyRecipeType();
    }
}
//}
//
//        /*NOT SURE WHAT THIS IS FOR*/
//    public DefaultedList<Ingredient> getPreviewInputs() {
//        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
//        defaultedList.add(this.inputA);
//        defaultedList.add(this.inputB);
//        return defaultedList;
//    }
//
//    @Override
//    public ItemStack craft(Inventory inv) {
//        inv.takeInvStack(0, amount1);
//        inv.takeInvStack(1, amount2);
//        return getOutput();
//    }
//
//    @Override
//    public boolean fits(int width, int height) {
//        return false;
//    }
//
//    @Override
//    public ItemStack getOutput() {
//        return outStack.copy();
//    }
//
//    @Override
//    public Identifier getId() {
//        return id;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return AlloyRecipeSerializer.INSTANCE;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return AlloyRecipeType.INSTANCE;
//    }
//

//
//
//

//
//    @Override
//    public ItemStack craft(Inventory inv) {
//        inv.removeStack(0, amountA);
//        inv.removeStack(1, amountB);
//        return getOutput();
//    }
//
//    @Override
//    public boolean fits(int width, int height) {
//        return false;
//    }
//
//
//    public Ingredient getInputA() {
//        return this.inputA;
//    }
//
//    public Ingredient getInputB() {
//        return this.inputB;
//    }
//
//    @Override
//    public ItemStack getOutput() {
//        return output.copy();
//    }
//
//    @Override
//    public Identifier getId() {
//        return ID;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return AlloyRecipeSerializer.INSTANCE;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return AlloyRecipeType.INSTANCE;
//    }
//
//
//
//
//
//    class AlloyRecipeJsonFormat {
//        JsonObject inputA;
//        JsonObject inputB;
//        int amountA;
//        int amountB;
//        String outputItem;
//        int outputAmount;
//}
//
//
//public static class AlloyRecipeSerializer implements RecipeSerializer<AlloyRecipe> {
//
//    public static final AlloyRecipeSerializer INSTANCE = new AlloyRecipeSerializer();
//
//    private AlloyRecipeSerializer() {
//    }
//
//    @Override
//    public AlloyRecipe read(Identifier id, JsonObject json) {
//        AlloyRecipeJsonFormat recipeJson = new Gson().fromJson(json, AlloyRecipeJsonFormat.class);
//        if ((recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.outputItem == null)) {
//            throw new JsonSyntaxException("Alloy furnace recipe is missing a required attribute");
//        }
//        if (recipeJson.amountA <= 0)
//            recipeJson.amountA = 1;
//        if (recipeJson.amountB <= 0)
//            recipeJson.amountB = 1;
//        if (recipeJson.outputAmount <= 0)
//            recipeJson.outputAmount = recipeJson.amountA + recipeJson.amountB;
//        Ingredient in1 = Ingredient.fromJson(recipeJson.inputA);
//        Ingredient in2 = Ingredient.fromJson(recipeJson.inputB);
//
//        ItemStack out;
//        Item outItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
//                    .orElseThrow(() -> new JsonSyntaxException(
//                            "Alloy furnace recipe uses " + recipeJson.outputItem + " which does not exist"));
//            out = new ItemStack(outItem, recipeJson.outputAmount);
//
//        return new AlloyRecipe(id, in1, in2, recipeJson.amountA, recipeJson.amountB, out);
//    }
//
//    @Override
//    public AlloyRecipe read(Identifier id, PacketByteBuf buf) {
//        Ingredient in1 = Ingredient.fromPacket(buf);
//        Ingredient in2 = Ingredient.fromPacket(buf);
//        int am1 = buf.readInt();
//        int am2 = buf.readInt();
//        ItemStack out = buf.readItemStack();
//        return new AlloyRecipe(id, in1, in2, am1, am2, out);
//    }
//
//    @Override
//    public void write(PacketByteBuf buf, AlloyRecipe recipe) {
//        recipe.inputA.write(buf);
//        recipe.inputB.write(buf);
//        buf.writeInt(recipe.amountA);
//        buf.writeInt(recipe.amountB);
//        buf.writeItemStack(recipe.output);
//    }
//
//}


/*TODO*/

//    public static class AlloyRecipeSerializer<T extends AlloyRecipe> implements RecipeSerializer<T>{
//
//        @Override
//        public T read(Identifier id, JsonObject json) {
////            String string = JsonHelper.getString(json, "group", "");
////            JsonElement jsonElementA = JsonHelper.hasArray(json, "ingredientA") ? JsonHelper.getArray(json, "ingredientA") : JsonHelper.getObject(json, "ingredientA");
////            Ingredient ingredientA = Ingredient.fromJson(jsonElementA);
////            JsonElement jsonElementB = JsonHelper.hasArray(json, "ingredientB") ? JsonHelper.getArray(json, "ingredientB") : JsonHelper.getObject(json, "ingredientB");
////            Ingredient ingredientB = Ingredient.fromJson(jsonElementB);
////
////
////            String string = JsonHelper.getString(jsonObject, "group", "");
////            JsonElement jsonElement = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
////            Ingredient ingredient = Ingredient.fromJson(jsonElement);
////            JsonObject resultObject = jsonObject.getAsJsonObject("result");
////            String resultString = JsonHelper.getString(resultObject, "item");
////            Identifier result = new Identifier(resultString);
////            int count = JsonHelper.getInt(resultObject, "count", 1);
////            ItemStack itemStack = new ItemStack(Registry.ITEM.getOrEmpty(result).orElseThrow(() ->
////                    new IllegalStateException("Item: " + resultString + " does not exist")
////            ), count);
////            int i = JsonHelper.getInt(jsonObject, "time", this.processingTime);
////            return factory.create(identifier, string, ingredient, itemStack, i);
//
//
//
//
//
//                AlloyRecipeJsonFormat recipeJson = new Gson().fromJson(json, AlloyRecipeJsonFormat.class);
//                if ((recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.outputItem == null)) {
//                    throw new JsonSyntaxException("A required attribute is missing!");
//                }
//                if (recipeJson.amountA <= 0)
//                    recipeJson.amountA = 1;
//                if (recipeJson.amountB <= 0)
//                    recipeJson.amountB = 1;
//                if (recipeJson.outputAmount <= 0)
//                    recipeJson.outputAmount = recipeJson.amountA + recipeJson.amountB;
//
//                Ingredient inA= Ingredient.fromJson(recipeJson.inputA);
//                Ingredient inB = Ingredient.fromJson(recipeJson.inputB);
//            ItemStack out;
//
//            Item outItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
//                    .orElseThrow(() -> new JsonSyntaxException(
//                            "Alloy furnace recipe uses " + recipeJson.outputItem + " which does not exist"));
//            out = new ItemStack(outItem, recipeJson.outputAmount);
//
//
//                return new AlloyRecipe(id, out, inA, inB, recipeJson.amountA, recipeJson.amountB);
//        }
//
//        @Override
//        public AlloyRecipe read(Identifier id, PacketByteBuf buf) {
//            Ingredient ingredientA = Ingredient.fromPacket(buf);
//            Ingredient ingredientB = Ingredient.fromPacket(buf);
//            int amountA = buf.readInt();
//            int amountB = buf.readInt();
//            ItemStack output = buf.readItemStack();
//            return new AlloyRecipe(id, output, ingredientA, ingredientB, amountA, amountB);
//        }
//
//        @Override
//        public void write(PacketByteBuf buf, AlloyRecipe recipe) {
//            recipe.inputA.write(buf);
//            recipe.inputB.write(buf);
//            buf.writeInt(recipe.amountA);
//            buf.writeInt(recipe.amountB);
//            buf.writeItemStack(recipe.output);
//
//        }
////    }
////
////
////
////
//
////
//    public static class AlloyRecipeType implements RecipeType<AlloyRecipe> {
//        private AlloyRecipeType() {
//        }
//
//        public static final AlloyRecipeType INSTANCE = new AlloyRecipeType();
////    }
//}

