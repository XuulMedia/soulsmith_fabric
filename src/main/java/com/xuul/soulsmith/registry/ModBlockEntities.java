package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.blocks.entities.AlloySmelterEntity;
import com.xuul.soulsmith.blocks.entities.ManualGrinderEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.registry.Identifiers.ALLOY_ID;
import static com.xuul.soulsmith.registry.Identifiers.MANUAL_GRINDER_ID;
import static com.xuul.soulsmith.registry.ModBlocks.ALLOY_SMELTER_BLOCK;
import static com.xuul.soulsmith.registry.ModBlocks.MANUAL_GRINDER_BLOCK;

public class ModBlockEntities {

    public static BlockEntityType<AlloySmelterEntity> ALLOY_SMELTER_ENTITY;
    public static BlockEntityType<ManualGrinderEntity> MANUAL_GRINDER_ENTITY;


    public static void register() {
        ALLOY_SMELTER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ALLOY_ID, BlockEntityType.Builder.create(AlloySmelterEntity::new, ALLOY_SMELTER_BLOCK).build(null));
        MANUAL_GRINDER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MANUAL_GRINDER_ID, BlockEntityType.Builder.create(ManualGrinderEntity::new, MANUAL_GRINDER_BLOCK).build(null));

    }
}
