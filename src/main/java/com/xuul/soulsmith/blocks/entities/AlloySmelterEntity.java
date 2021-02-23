package com.xuul.soulsmith.blocks.entities;


import com.xuul.soulsmith.blocks.AlloySmelterBlock;
import com.xuul.soulsmith.gui.ImplementedInventory;
import com.xuul.soulsmith.recipes.AlloyRecipe;
import com.xuul.soulsmith.util.InventoryTools;
import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.registry.ModBlockEntities.ALLOY_SMELTER_ENTITY;

public class AlloySmelterEntity extends BlockEntity implements ImplementedInventory,SidedInventory, InventoryProvider, PropertyDelegateHolder, Tickable {

    DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);


    Recipe<?> recipe = this.world.getRecipeManager().getFirstMatch(, AlloyRecipe.AlloyRecipeType.INSTANCE, this.world).orElse(null);

    static final int[] INPUT_SLOTS = new int[]{0, 1};
    static final int[] FUEL_SLOT = new int[]{2};
    static final int[] OUTPUT_SLOT = new int[]{3};

    //    static final int CRAFT_AMOUNT = 3;
    public static final int SMELT_TIME = 400;

    int progress = 0;
    int fuel = 0;
    int maxFuel = 0;

    public AlloySmelterEntity() {
        super(ALLOY_SMELTER_ENTITY);
    }

    @Override
    public void tick() {
        if (world.isClient)
            return;
        if (progress == SMELT_TIME) {
            progress = 0;
            smelt(recipe);
        } else if (isRecipeValid(recipe) && fuel > 0) {
            ++progress;
        } else {
            progress = 0;
        }

        if (fuel > 0) {
            --fuel;
        }
        if (fuel == 0 && isRecipeValid(recipe)) {
            Integer fuelAmount = FuelRegistryImpl.INSTANCE.get(inventory.get(FUEL_SLOT[0]).getItem());
            if (fuelAmount != null) {
                InventoryTools.decrementFuel(this, FUEL_SLOT[0]);
                fuel = fuelAmount;
                maxFuel = fuelAmount;
                updateBlockState();
            }
        }
    }

    private void updateBlockState() {
        if (world.getBlockState(pos).get(AlloySmelterBlock.LIT) != fuel > 0)
            world.setBlockState(pos, getCachedState().with(AlloySmelterBlock.LIT, fuel > 0));
    }

    /*TODO: Make decrement amount dynamic*/
    protected void smelt(@Nullable Recipe<?> recipe) {
        ItemStack inputA = inventory.get(0);
        ItemStack inputB = inventory.get(1);
        ItemStack output = inventory.get(3);
        if (recipe != null && isRecipeValid(recipe)) {
            ItemStack recipeOutput = recipe.getOutput();
            if (output.isEmpty()) {
                inventory.set(1, recipeOutput.copy());
            } else if (output.getItem() == recipeOutput.getItem()) {
                output.increment(recipeOutput.getCount());
            }
            inputA.decrement(1);
            inputB.decrement(1);
        }
    }


    private boolean isRecipeValid(@Nullable Recipe<?> recipe) {
        if (!inventory.get(0).isEmpty() && !inventory.get(1).isEmpty() && recipe != null) {
            ItemStack recipeOutput = recipe.getOutput();
            ItemStack output = inventory.get(3);
            if (recipeOutput.isEmpty()) {
                return false;
            } else {
                if (output.isEmpty()) {
                    return true;
                } else if (output.getCount() <= getMaxCountPerStack() - recipeOutput.getCount() && output.getCount() <= output.getMaxCount() - recipeOutput.getCount()) {
                    return true;
                } else {
                    return output.getCount() <= recipeOutput.getMaxCount() - recipeOutput.getCount();
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, inventory);
        tag.putInt("fuel", fuel);
        tag.putInt("maxFuel", maxFuel);
        tag.putInt("progress", progress);
        return super.toTag(tag);
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, inventory);
        fuel = tag.getInt("fuel");
        maxFuel = tag.getInt("maxFuel");
        progress = tag.getInt("progress");
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return OUTPUT_SLOT;
        } else {
            return side == Direction.UP ? INPUT_SLOTS : FUEL_SLOT;
        }
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == OUTPUT_SLOT[0])
            return false;
        if (slot == FUEL_SLOT[0])
            return FuelRegistryImpl.INSTANCE.get(stack.getItem()) != null;
        return true;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        return this;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        if (slot == OUTPUT_SLOT[0])
            return false;
        if (slot == FUEL_SLOT[0])
            return FuelRegistryImpl.INSTANCE.get(stack.getItem()) > 0;
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot == OUTPUT_SLOT[0];
    }

    PropertyDelegate propdel = new PropertyDelegate() {

        @Override
        public int size() {
            return 4;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    progress = value;
                    break;
                case 1:
                    fuel = value;
                    break;
                case 2:
                    maxFuel = value;
                    break;
            }
        }

        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return progress;
                case 1:
                    return fuel;
                case 2:
                    return maxFuel;
                case 3:
                    return SMELT_TIME;
            }
            return 0;
        }
    };

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propdel;
    }
}




















//import static com.xuul.soulsmith.registry.GuiRegistry.ALLOY_SMELTER_SCREEN_HANDLER;
//import static com.xuul.soulsmith.registry.ModBlockEntities.ALLOY_SMELTER_ENTITY;
//
//public class AlloySmelterEntity extends BlockEntity
//        implements NamedScreenHandlerFactory, ImplementedInventory, SidedInventory, InventoryProvider, PropertyDelegateHolder, Tickable {
//
//    public static final int INVENTORY_SIZE = 4;
//
//    DefaultedList<ItemStack> items =  DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
//    static final int[] INPUT_SLOTS = new int[] { 0, 1 };
//    static final int[] FUEL_SLOT = new int[] { 2 };
//    static final int[] OUTPUT_SLOT = new int[] { 3 };
//
//    static final int CRAFT_AMOUNT = 3;
//    public static final int SMELT_TIME = 400;
//
//    int progress = 0;
//    int fuel = 0;
//    int maxFuel = 0;
////
//    public AlloySmelterEntity() {super(ALLOY_SMELTER_ENTITY); }
//
//    @Override
//    public void tick() {
//        if (world.isClient)
//            return;
//        if (progress == SMELT_TIME) {
//            progress = 0;
//            smelt();
//        } else if (isRecipeValid() && fuel > 0) {
//            ++progress;
//        } else {
//            progress = 0;
//        }
//        if (fuel > 0) {
//            fuel--;
//        }
//        if (fuel == 0 && isRecipeValid()) {
//            Integer fuelAmount = FuelRegistryImpl.INSTANCE.get(items.get(FUEL_SLOT[0]).getItem());
//            if (fuelAmount != null) {
//                InventoryTools.decrementFuel(this, FUEL_SLOT[0]);
//                fuel = fuelAmount;
//                maxFuel = fuelAmount;
////                updateBlockState();
//            }
//        }
//
//    }
//
//    @Override
//    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
//        if (slot == OUTPUT_SLOT[0])
//            return false;
//        if(slot == FUEL_SLOT[0])
//            return FuelRegistryImpl.INSTANCE.get(stack.getItem()) > 0;
//        return true;
//    }
//
//    @Override
//    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
//        return slot == OUTPUT_SLOT[0];
//    }
//
//
//
//
////    Toggle block lighting
////    private void updateBlockState() {
////        if (world.getBlockState(pos).get(AlloySmelterBlock.LIT) != fuel > 0);
////    }
//
//
//    private void smelt() {
//        Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(AlloyRecipe.AlloyRecipeType.INSTANCE, this,
//                world);
//        if (match.isPresent()) {

//            AlloyRecipe recipe = match.get();
//            InventoryTools.insertItemstack(this, OUTPUT_SLOT[0], recipe.craft(this));
//        }
//    }
//
//    private boolean isRecipeValid() {
//        Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(AlloyRecipe.AlloyRecipeType.INSTANCE, this,
//                world);
//        return match.isPresent() && InventoryTools.insertItemstack(this, OUTPUT_SLOT[0], match.get().getOutput(), true);
//    }
//
//    @Override
//       public DefaultedList<ItemStack> getItems() {
//            return items;
//        }
//
//
//    @Override
//    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) { return this; }
//
//
//
//    @Override
//    public Text getDisplayName() {
//        return new LiteralText("Alloy Smelter"); // no title
//        }
//
//        @Nullable
//        @Override
//        public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
//            return new AlloySmelterGui(ALLOY_SMELTER_SCREEN_HANDLER, syncId, inv, ScreenHandlerContext.create(world, pos));
//        }
//
//
//    @Override
//    public int[] getAvailableSlots(Direction side) {
//        if (side == Direction.DOWN) {
//            return OUTPUT_SLOT;
//        } else {
//            return side == Direction.UP ? INPUT_SLOTS : FUEL_SLOT;
//        }
//    }
//
//
//    PropertyDelegate propdel = new PropertyDelegate() {
//
//        @Override
//        public int size() {
//            return 4;
//        }
//
//        @Override
//        public void set(int index, int value) {
//            switch (index) {
//                case 0:
//                    progress = value;
//                    break;
//                case 1:
//                    fuel = value;
//                    break;
//                case 2:
//                    maxFuel = value;
//                    break;
//            }
//        }
//
//        @Override
//        public int get(int index) {
//            switch (index) {
//                case 0:
//                    return progress;
//                case 1:
//                    return fuel;
//                case 2:
//                    return maxFuel;
//                case 3:
//                    return SMELT_TIME;
//            }
//            return 0;
//        }
//    };
//
//    @Override
//    public PropertyDelegate getPropertyDelegate() {
//        return propdel;
//    }
//
//
//    }


