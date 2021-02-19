package com.xuul.soulsmith.blocks;

import com.xuul.soulsmith.gui.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;

import static com.xuul.soulsmith.registry.ModBlockEntities.DEMO_BLOCK_ENTITY;

public class DemoBlockEntity extends BlockEntity implements ImplementedInventory {

//    IMPLEMENTED INVENTORY
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public DefaultedList<ItemStack> getItems() {
        return items;
    }


    public DemoBlockEntity() {
        super(DEMO_BLOCK_ENTITY);
    }


//    SAVE AND LOADING
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        return super.toTag(tag);
}

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
    }






}