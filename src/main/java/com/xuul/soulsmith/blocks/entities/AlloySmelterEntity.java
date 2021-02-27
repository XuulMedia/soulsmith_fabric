package com.xuul.soulsmith.blocks.entities;

import com.xuul.soulsmith.gui.AlloyGuiDescription;
import com.xuul.soulsmith.gui.ImplementedInventory;
import com.xuul.soulsmith.recipes.AlloyRecipe;
import com.xuul.soulsmith.registry.ModBlockEntities;
import com.xuul.soulsmith.util.InventoryTools;
import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlloySmelterEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, Tickable, PropertyDelegateHolder {


    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);


    private final int[] INPUT_SLOTS = new int[]{0, 1};
    private final int[] FUEL_SLOT = new int[]{2};
    private final int[] OUTPUT_SLOT = new int[]{3};

    public static final int SMELT_TIME = 1;

    int progress = 0;
    int fuel = 0;
    int maxFuel = 0;


    public AlloySmelterEntity() {
        super(ModBlockEntities.ALLOY_SMELTER_ENTITY);
            }

    //From the ImplementedInventory Interface

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;

    }

    //These Methods are from the NamedScreenHandlerFactory Interface
    //createMenu creates the ScreenHandler itself
    //getDisplayName will Provide its name which is normally shown at the top
//
//    @Override
//    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
//        //We provide *this* to the screenHandler as our class Implements Inventory
//        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
//        return new AlloyGuiDescription(syncId, playerInventory,  ScreenHandlerContext.create(world, pos), world, recipeType);
//    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, this.inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }

    //*TODO see if these methods work*/

    @Override
    public void tick() {
        if (world == null || world.isClient)
            return;
        if (progress == SMELT_TIME) {
            progress = 0;
            smelt();
        } else if (isRecipeValid() && fuel > 0) {
            ++progress;
        } else {
            progress = 0;
        }
        if (fuel > 0) {
            --fuel;
        }

    }


    private void smelt() {
        Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(AlloyRecipe.AlloyRecipeType.INSTANCE, this,
                world);
        if (match.isPresent()) {
            AlloyRecipe recipe = match.get();
            craftRecipe(recipe);
        }
    }


    protected void craftRecipe(@Nullable Recipe<?> recipe) {
        ItemStack inputA = inventory.get(0);
        ItemStack inputB = inventory.get(1);
        ItemStack output = inventory.get(3);
        if (recipe != null) {
            ItemStack recipeOutput = recipe.getOutput();
            if (output.isEmpty()) {
                inventory.set(3, recipeOutput.copy());
            } else if (output.getItem() == recipeOutput.getItem()) {
                output.increment(recipeOutput.getCount());
            }

            inputA.decrement(1);
            inputB.decrement(1);
        }
    }






    private boolean isRecipeValid() {
        Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(AlloyRecipe.AlloyRecipeType.INSTANCE, this,
                world);
        return match.isPresent() && InventoryTools.insertItemstack(this, OUTPUT_SLOT[0], match.get().getOutput(), true);
    }

    /**/

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }


    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
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
        public int size() {
            return 4;
        }
    };





//    @Override
//    public boolean isValidInvStack(int slot, ItemStack stack) {
//        if (slot == OUTPUT_SLOT[0])
//            return false;
//        if(slot == FUEL_SLOT[0])
//            return FuelRegistryImpl.INSTANCE.get(stack.getItem()) != null;
//        return true;
//    }
//
//    @Override
//    public boolean canInsertInvStack(int slot, ItemStack stack, Direction dir) {
//        if (slot == OUTPUT_SLOT[0])
//            return false;
//        if(slot == FUEL_SLOT[0])
//            return FuelRegistryImpl.INSTANCE.get(stack.getItem()) > 0;
//        return true;
//    }
//
//    @Override
//    public boolean canExtractInvStack(int slot, ItemStack stack, Direction dir) {
//        return slot == OUTPUT_SLOT[0];
//    }




    /*END OF RECIPIE METHODS*/

}

