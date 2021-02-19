package com.xuul.soulsmith.blocks;

import com.xuul.soulsmith.gui.BoxGuiDescription;
import com.xuul.soulsmith.gui.ImplementedInventory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import static com.xuul.soulsmith.registry.GuiRegistry.BOX_SCREEN_HANDLER;
import static com.xuul.soulsmith.registry.ModBlockEntities.BOX_BLOCK_ENTITY;

public class BoxBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    public static final int INVENTORY_SIZE = 9;

    DefaultedList<ItemStack> items =  DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

    public BoxBlockEntity() {
        super(BOX_BLOCK_ENTITY);
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
        return new BoxGuiDescription(BOX_SCREEN_HANDLER, syncId, inv, ScreenHandlerContext.create(world, pos));
    }





}