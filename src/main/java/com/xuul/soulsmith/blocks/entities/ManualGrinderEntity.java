package com.xuul.soulsmith.blocks.entities;

import com.xuul.soulsmith.gui.ManualGrinderGui;
import com.xuul.soulsmith.recipes.ManualGrindingRecipe;
import com.xuul.soulsmith.util.InventoryTools;
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
import net.minecraft.util.collection.DefaultedList;

import java.util.Optional;

import static com.xuul.soulsmith.registry.ModBlockEntities.MANUAL_GRINDER_ENTITY;
import static com.xuul.soulsmith.registry.ModRecipes.MANUAL_GRINDER_RECIPE;

public class ManualGrinderEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory;
    private static final int INVENTORY_SIZE = 3;

    public static final int GRINDING_TIME = 400;

    int progress = 0;

    public ManualGrinderEntity() {
        super(MANUAL_GRINDER_ENTITY);
        this.inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
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
    protected Text getContainerName() {
        return new TranslatableText("container.manual_grinder");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {

        return new ManualGrinderGui(syncId, playerInventory, ScreenHandlerContext.create(world, pos));
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


    /*TODO set up a function for click to grind*/
    private void process(){
        if (world.isClient()) {return;}

        progress += 20;
        if(progress >= GRINDING_TIME) {
            progress = 0;
            grind();
        }

    }

    private void grind(){
        Optional<ManualGrindingRecipe> match = world.getRecipeManager().getFirstMatch(MANUAL_GRINDER_RECIPE, this,
                world);
        if (match.isPresent()) {
            ManualGrindingRecipe recipe = match.get();
            InventoryTools.insertItemstack(this, 2, recipe.craft(this));
        }
    }
}
