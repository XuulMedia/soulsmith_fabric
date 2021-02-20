package com.xuul.soulsmith.blocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AlloyFurnaceBlock extends BlockWithEntity {

    public AlloyFurnaceBlock(Settings settings) {
        super(settings);
    }


    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new AlloyFurnaceEntity();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }



//    DROP ITEMS WHEN BROKEN
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        BlockEntity entity = world.getBlockEntity(pos);
        if (entity instanceof AlloyFurnaceEntity) {
            ItemScatterer.spawn(world, pos, (AlloyFurnaceEntity)entity);
              world.updateComparators(pos,this);
        }
    }



    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }


    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }


    //    This method will drop all items onto the ground when the block is broken
//    @Override
//    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//        if (state.getBlock() != newState.getBlock()) {
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if (blockEntity instanceof AlloyFurnaceEntity) {
//                ItemScatterer.spawn(world, pos, (AlloyFurnaceEntity)blockEntity);
//                // update comparators
//                world.updateComparators(pos,this);
//            }
//            super.onStateReplaced(state, world, pos, newState, moved);
//        }
//    }



}
