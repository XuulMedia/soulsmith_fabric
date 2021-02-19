package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.blocks.DemoBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;
import static com.xuul.soulsmith.registry.ModBlocks.DEMO_BLOCK;


public class ModBlockEntities {

    public static BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY;



    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID, BlockEntityType.Builder.create(DemoBlockEntity::new, DEMO_BLOCK).build(null));

    }
}
