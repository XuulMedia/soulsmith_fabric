package com.xuul.soulsmith.blocks;

import com.xuul.soulsmith.gui.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.registry.ModBlockEntities.DEMO_BLOCK_ENTITY;

public class DemoBlockEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory {

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





    @Override
    public Text getDisplayName() {
        // Using the block name as the screen title
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }


    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }
}