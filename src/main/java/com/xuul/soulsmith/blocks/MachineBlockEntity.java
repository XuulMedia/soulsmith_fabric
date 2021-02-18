package com.xuul.soulsmith.blocks;

import com.xuul.soulsmith.gui.MachineGuiDescription;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.registry.ModBlockEntities.MACHINE_BLOCK_ENTITY;


public class MachineBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, MachineImplementedInventory {
    public MachineBlockEntity() {
        super(MACHINE_BLOCK_ENTITY);
    }

//    Item stack for inventory
    DefaultedList<ItemStack> items = DefaultedList.ofSize(3, ItemStack.EMPTY);

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

//----
//  COTTON LIB
    @Override
    public Text getDisplayName() {
        // Using the block name as the screen title
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
        return new MachineGuiDescription(syncId, inventory, ScreenHandlerContext.create(world, pos));
    }


//----

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return pos.isWithinDistance(player.getBlockPos(),4.5);
    }


//    SAVING AND LOADING
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }


}
