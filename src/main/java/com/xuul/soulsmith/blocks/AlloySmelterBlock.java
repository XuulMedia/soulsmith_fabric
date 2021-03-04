package com.xuul.soulsmith.blocks;

import com.xuul.soulsmith.blocks.entities.AlloySmelterEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AlloySmelterBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public AlloySmelterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(LIT, false));
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new AlloySmelterEntity();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING,LIT);

    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AlloySmelterEntity) {
                ((AlloySmelterEntity)blockEntity).setCustomName(itemStack.getName());
            }
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        return ActionResult.SUCCESS;
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



    /*Needed if not block with entity*/
//    @Nullable
//    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//
//
//        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
//    }
//
//
//    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
//        super.onSyncedBlockEvent(state, world, pos, type, data);
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//        return blockEntity != null && blockEntity.onSyncedBlockEvent(type, data);
//    }


    /*THIS WORKS WITH MAINHAND OFFHAND*/
//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult result) {
//        // Something that gives the player items should always go through the server.
//        // If you need to notify the client in some way, check in the server and then send a packet to the client.
//        if (!world.isClient()) {
//            // For the sake of simplicity we draw the items off of the player's hands and create an inventory from that.
//            // Usually you use an inventory of yours instead.
//            SimpleInventory inventory = new SimpleInventory(player.getMainHandStack(), player.getOffHandStack());
//
//            // Or use .getAllMatches if you want all of the matches
//            Optional<AlloyRecipe> match = world.getRecipeManager().getFirstMatch(ALLOY_RECIPE, inventory, world);
//
//            if (match.isPresent()) {
//                // Give the player the item and remove from what he has. Make sure to copy the ItemStack to not ruin it!
//                player.inventory.offerOrDrop(world, match.get().getOutput().copy());
//                player.getMainHandStack().decrement(1);
//                player.getOffHandStack().decrement(1);
//                return ActionResult.SUCCESS;
//            } else {
//                // If it doesn't match we tell the player
//                player.sendMessage(new LiteralText("No match!"), false);
//                return ActionResult.FAIL;
//            }
//        }
//        return ActionResult.PASS;
//    }
//











}
