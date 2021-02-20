package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.blocks.AlloyFurnaceEntity;
import com.xuul.soulsmith.blocks.BoxBlockEntity;
import com.xuul.soulsmith.blocks.DemoBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;
import static com.xuul.soulsmith.registry.ModBlocks.*;


public class ModBlockEntities {

    public static BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY;
    public static BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY;
    public static BlockEntityType<AlloyFurnaceEntity> ALLOY_FURNACE_ENTITY;



    public static void register() {
        DEMO_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID, BlockEntityType.Builder.create(DemoBlockEntity::new, DEMO_BLOCK).build(null));
        BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOXID, BlockEntityType.Builder.create(BoxBlockEntity::new, BOX_BLOCK).build(null));
        ALLOY_FURNACE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ALLOYID, BlockEntityType.Builder.create(AlloyFurnaceEntity::new, ALLOY_FURNACE_BLOCK).build(null));

    }
}
