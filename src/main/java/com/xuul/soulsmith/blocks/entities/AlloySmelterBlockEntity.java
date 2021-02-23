package com.xuul.soulsmith.blocks.entities;

import com.xuul.soulsmith.gui.AlloySmelterScreenHandler;
import com.xuul.soulsmith.recipes.ProcessingRecipe;
import com.xuul.soulsmith.registry.ModBlockEntities;
import com.xuul.soulsmith.registry.RecipeRegistry;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import javax.sound.midi.MidiMessage;

public class AlloySmelterBlockEntity extends AbstractDirectProcessorBlockEntity {
    private static final int[] BOTTOM_SLOTS = new int[]{1};
    private static final int[] SIDE_SLOTS = new int[]{0};

    public AlloySmelterBlockEntity() {
        super(ModBlockEntities.ALLOY_SMELTER_ENTITY, RecipeRegistry.ALLOYING);
    }


    @Override
    protected Text getContainerName() {
        return new TranslatableText("block.mechanicaltech.grinder");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new AlloySmelterScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    private Direction getFront() {
        return world.getBlockState(pos).get(HorizontalFacingBlock.FACING);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return BOTTOM_SLOTS;
        } else {
            return SIDE_SLOTS;
        }
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return dir != getFront() && slot != 1;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return !canInsert(slot, stack, dir);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return player.squaredDistanceTo((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    protected int getProcessTime() {
        return this.world.getRecipeManager().getFirstMatch(this.recipeType, this, this.world).map(ProcessingRecipe::getProcessingTime).orElse(200);
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {

    }

    @Override
    public void close() {

    }
}