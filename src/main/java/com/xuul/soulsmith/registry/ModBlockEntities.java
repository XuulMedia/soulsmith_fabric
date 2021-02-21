package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.blocks.entities.AlloySmelterEntity;
import com.xuul.soulsmith.blocks.entities.BoxBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.registry.ModBlocks.*;


public class ModBlockEntities {

    public static BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY;
    public static BlockEntityType<AlloySmelterEntity> ALLOY_SMELTER_ENTITY;


    public static void register() {
        BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOXID, BlockEntityType.Builder.create(BoxBlockEntity::new, BOX_BLOCK).build(null));
        ALLOY_SMELTER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ALLOYID, BlockEntityType.Builder.create(AlloySmelterEntity::new, ALLOY_SMELTER_BLOCK).build(null));

    }
}
