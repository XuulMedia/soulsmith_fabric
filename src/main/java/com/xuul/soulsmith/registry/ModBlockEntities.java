package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.blocks.entities.old.AlloySmelterEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.registry.Identifiers.ALLOY_ID;

import static com.xuul.soulsmith.registry.ModBlocks.*;


public class ModBlockEntities {


    public static BlockEntityType<AlloySmelterEntity> ALLOY_SMELTER_ENTITY;


    public static void register() {
        ALLOY_SMELTER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ALLOY_ID, BlockEntityType.Builder.create(AlloySmelterEntity::new, ALLOY_SMELTER_BLOCK).build(null));

    }
}
