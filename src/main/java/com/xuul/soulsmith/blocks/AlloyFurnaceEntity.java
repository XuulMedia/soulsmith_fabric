package com.xuul.soulsmith.blocks;


import com.xuul.soulsmith.gui.ImplementedInventory;
import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.registry.GuiRegistry.ALLOY_FURNACE_SCREEN_HANDLER;
import static com.xuul.soulsmith.registry.ModBlockEntities.ALLOY_FURNACE_ENTITY;

public class AlloyFurnaceEntity extends BlockEntity
        implements NamedScreenHandlerFactory, ImplementedInventory, SidedInventory, InventoryProvider, PropertyDelegateHolder, Tickable {

    public static final int INVENTORY_SIZE = 4;

    DefaultedList<ItemStack> items =  DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

    static final int[] INPUT_SLOTS = new int[] { 0, 1 };
    static final int[] FUEL_SLOT = new int[] { 2 };
    static final int[] OUTPUT_SLOT = new int[] { 3 };
    static final int CRAFT_AMOUNT = 3;
    public static final int SMELT_TIME = 400;

    int progress = 0;
    int fuel = 0;
    int maxFuel = 0;

    public AlloyFurnaceEntity() {super(ALLOY_FURNACE_ENTITY); }

    @Override
    public void tick() {
        if (world.isClient)
            return;
        if (progress == SMELT_TIME) {
            progress = 0;
            smelt();
        } else if (isRecipeValid() && fuel > 0) {
            ++progress;
        } else {
            progress = 0;
        }
        int oldFuel = fuel;
        if (fuel > 0) {
            --fuel;
        }
        if (fuel == 0 && isRecipeValid()) {
            Integer fuelAmount = FuelRegistryImpl.INSTANCE.get(items.get(FUEL_SLOT[0]).getItem());
            if (fuelAmount != null) {
                InventoryTools.decrementFuel(this, FUEL_SLOT[0]);
                fuel = fuelAmount;
                maxFuel = fuelAmount;
                updateBlockState();
            } else if (oldFuel != fuel) {
                updateBlockState();
            }
        } else if (fuel == 0 && oldFuel != fuel) {
            updateBlockState();
        }

    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        if (slot == OUTPUT_SLOT[0])
            return false;
        if(slot == FUEL_SLOT[0])
            return FuelRegistryImpl.INSTANCE.get(stack.getItem()) > 0;
        return true;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot) {
        return slot == OUTPUT_SLOT[0];
    }



//    NO IDEAD

    private void smelt() {
        Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(AlloyRecipe.AlloyRecipeType.INSTANCE, this,
                world);
        if (match.isPresent()) {
            AlloyRecipe recipe = match.get();
            InventoryTools.insertItemstack(this, OUTPUT_SLOT[0], recipe.craft(this));
        }
    }

    private boolean isRecipeValid() {
        Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(AlloyRecipe.AlloyRecipeType.INSTANCE, this,
                world);
        return match.isPresent() && InventoryTools.insertItemstack(this, OUTPUT_SLOT[0], match.get().getOutput(), true);
    }









        @Override
        public DefaultedList<ItemStack> getItems() {
            return items;
        }

        @Override
        public boolean canPlayerUse(PlayerEntity player) {
            return pos.isWithinDistance(player.getBlockPos(), 4.5);
        }

        @Override
        public Text getDisplayName() {
            return new LiteralText(""); // no title
        }

        @Nullable
        @Override
        public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
            return new AlloyFurnaceGuiDescription(ALLOY_SMELTER_SCREEN_HANDLER, syncId, inv, ScreenHandlerContext.create(world, pos));
        }


    @Override
    public PropertyDelegate getPropertyDelegate() {
        return null;
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        return null;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[0];
    }


    }


