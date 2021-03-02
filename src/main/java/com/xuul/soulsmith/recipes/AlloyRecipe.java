package com.xuul.soulsmith.recipes;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static com.xuul.soulsmith.registry.ModRecipes.ALLOY_RECIPE;


public class AlloyRecipe implements Recipe<Inventory> {

    public static final Identifier ID = new Identifier("soulsmith:alloy_recipe");
    private final Item inputA;
    private final Item inputB;
    private final ItemStack output;

    public AlloyRecipe(Item inputA, Item inputB, ItemStack output) {
        this.inputA = inputA;
        this.inputB = inputB;
        this.output = output;
    }

    public Item getInputA() {
        return this.inputA;
    }

    public Item getInputB() {
        return this.inputB;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (inventory.size() < 2) {
            return false;
        }
        return this.inputA.equals(inventory.getStack(0).getItem()) && this.inputB.equals(inventory.getStack(1).getItem());
    }


    @Override
    public ItemStack craft(Inventory inv) {
        if(!inv.getStack(0).isEmpty() && !inv.getStack(1).isEmpty()  ){
            inv.removeStack(0,1);
            inv.removeStack(1,1);
            return this.output;
        }
      return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
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
        return ALLOY_RECIPE;
    }

    public static final Codec<AlloyRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registry.ITEM.fieldOf("inputA").forGetter(AlloyRecipe::getInputA),
            Registry.ITEM.fieldOf("inputB").forGetter(AlloyRecipe::getInputB),
            ItemStack.CODEC.fieldOf("result").forGetter(AlloyRecipe::getOutput)
    ).apply(instance, AlloyRecipe::new));
}


//
//    public static final Identifier ID = new Identifier("soulsmith:alloy_recipe");
//    private final Item inputA;
//    private final Item inputB;
//    private final ItemStack output;
//
//
//    public AlloyRecipe(Item inputA, Item inputB, ItemStack output) {
//
//        this.inputA = inputA;
//        this.inputB = inputB;
//        this.output = output;
//    }
//
//    public Item getInputA() {
//        return this.inputA;
//    }
//
//    public Item getInputB() {
//        return this.inputB;
//    }
//
//    @Override
//    public ItemStack getOutput() {
//        return this.output;
//    }
//
//    @Override
//    public boolean matches(Inventory inv, World world) {
//        if(inv.size()< 2) return false;
//        return this.inputA.equals(inv.getStack(0).getItem()) && this.inputB.equals(inv.getStack(1).getItem());
//    }
//
//    @Override
//    public Identifier getId() {
//        return ID;
//    }
//
//    @Override
//    public ItemStack craft(Inventory inv) {return ItemStack.EMPTY;}
//
//    @Override
//    public boolean fits(int width, int height) {
//        return false;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return ALLOY_RECIPE;
//    }
//
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return AlloyRecipeSerializer.INSTANCE;
//    }
//
//
//    public static final Codec<AlloyRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
//            Registry.ITEM.fieldOf("inputA").forGetter(AlloyRecipe::getInputA),
//            Registry.ITEM.fieldOf("inputB").forGetter(AlloyRecipe::getInputB),
//            ItemStack.CODEC.fieldOf("result").forGetter(AlloyRecipe::getOutput)
//    ).apply(instance, AlloyRecipe::new));
//
//
//
////    public static class AlloyRecipeType implements RecipeType<AlloyRecipe>{
////        private AlloyRecipeType(){}
////        public static final AlloyRecipeType INSTANCE = new AlloyRecipeType();
////        public static final String ID = "alloy_recipe";
////    }
//
//}

