package com.xuul.soulsmith.blocks;

import com.xuul.soulsmith.blocks.entities.old.AlloySmelterEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.world.BlockView;

public class AlloySmelterBlock extends Block implements BlockEntityProvider{
    public static final BooleanProperty LIT = Properties.LIT;

    public AlloySmelterBlock(Settings settings){
        super(settings);
        setDefaultState(getDefaultState().with(LIT, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new AlloySmelterEntity();
    }
}

