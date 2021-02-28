package com.xuul.soulsmith.blocks;

import com.xuul.soulsmith.blocks.entities.AlloySmelterEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AlloySmelterBlock extends Block implements BlockEntityProvider {

    private static final Text TITLE = new TranslatableText("container.alloy_smelter");
//    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;



    public AlloySmelterBlock(Settings settings) {
        super(settings);

    }



    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new AlloySmelterEntity();
    }

    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);


        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }


    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        super.onSyncedBlockEvent(state, world, pos, type, data);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity != null && blockEntity.onSyncedBlockEvent(type, data);
    }


//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        if (!world.isClient) {
//            //This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
//            //a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
//            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
//
//            if (screenHandlerFactory != null) {
//                //With this call the server will request the client to open the appropriate Screenhandler
//                player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
//            }
//        }
//        BlockEntity be = world.getBlockEntity(pos);
//        if (be != null && be instanceof AlloySmelterEntity) {
//            ContainerProviderRegistry.INSTANCE.openContainer(new Identifier(MOD_ID, "alloy_smelter"), player,
//                    (packetByteBuf -> packetByteBuf.writeBlockPos(pos)));
//            return ActionResult.SUCCESS;
//        }
//
//        return ActionResult.SUCCESS;
//    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity != null && AlloySmelterEntity.class.isAssignableFrom(blockEntity.getClass())) {
                player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
            }
            return ActionResult.CONSUME;
        }
    }





    //This method will drop all items onto the ground when the block is broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AlloySmelterEntity) {
                ItemScatterer.spawn(world, pos, (AlloySmelterEntity) blockEntity);
                // update comparators
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
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




}
