package com.xuul.soulsmith.blocks.entities;

import com.xuul.soulsmith.blocks.AlloySmelterBlock;
import com.xuul.soulsmith.gui.AlloyGuiDescription;
import com.xuul.soulsmith.recipes.AlloyRecipe;
import com.xuul.soulsmith.util.InventoryTools;
import net.fabricmc.fabric.impl.content.registry.FuelRegistryImpl;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

import java.util.Optional;

import static com.xuul.soulsmith.registry.ModBlockEntities.ALLOY_SMELTER_ENTITY;
import static com.xuul.soulsmith.registry.ModRecipes.ALLOY_RECIPE;

public class AlloySmelterEntity extends LootableContainerBlockEntity implements Tickable {
    private DefaultedList<ItemStack> inventory;
    private static final int INVENTORY_SIZE = 4;

    public static final int SMELT_TIME = 200;


    int progress = 0;
    int fuel = 0;
    int maxFuel = 0;

    public AlloySmelterEntity() {
        super(ALLOY_SMELTER_ENTITY);
        this.inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
    }


    @Override
    protected Text getContainerName() {
        return new TranslatableText("container.alloy_smelter");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new AlloyGuiDescription(syncId, playerInventory, ScreenHandlerContext.create(world, pos));
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    public int size() {
        return INVENTORY_SIZE;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(tag)) {
            Inventories.fromTag(tag, this.inventory);
        }
    }


    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        if (!this.serializeLootTable(tag)) {
            Inventories.toTag(tag, this.inventory);
        }
        return tag;
    }

    @Override
    public void tick() {
        if (world.isClient()) {return;}

        if (progress >= SMELT_TIME) {
            progress = 0;
            smelt();
        } else if (isRecipeValid() && fuel >0) {
            ++progress;
        } else {
            progress = 0;
        }
        int previousFuel = fuel;
        if(fuel >0){
            --fuel;
        }


        if (fuel == 0 && isRecipeValid()) {
            Integer fuelAmount = FuelRegistryImpl.INSTANCE.get(inventory.get(2).getItem());
            if (fuelAmount != null) {
                InventoryTools.decrementFuel(this, 2);
                fuel = fuelAmount;
                maxFuel = fuelAmount;
                updateBlockState();
            } else if (previousFuel != fuel){
                updateBlockState();
            }
        }  else if (fuel == 0 && previousFuel != fuel) {
        updateBlockState();
    }
    }

    private void smelt() {
        Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(ALLOY_RECIPE, this,
                world);
        if (match.isPresent()) {
            AlloyRecipe recipe = match.get();
            InventoryTools.insertItemstack(this, 3, recipe.craft(this));
        }
    }

    private boolean isRecipeValid() {Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(ALLOY_RECIPE, this,
            world);
    return match.isPresent();
    }

    private boolean hasFuel(){
        return FuelRegistryImpl.INSTANCE.get(inventory.get(2).getItem()) != null;
    }

    private void updateBlockState() {
        if (hasFuel() != world.getBlockState(pos).get(AlloySmelterBlock.LIT)){
            this.world.setBlockState(this.pos,this.world.getBlockState(pos).with(AlloySmelterBlock.LIT, hasFuel()));

//            world.setBlockState(pos, getCachedState().with(AlloySmelterBlock.LIT, hasFuel()));
        }
    }


}
