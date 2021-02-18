package com.xuul.soulsmith.registry;


import com.xuul.soulsmith.blocks.MachineBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.xuul.soulsmith.Soulsmith.MOD_ID;
import static com.xuul.soulsmith.registry.ModBlocks.MACHINE_BLOCK;

public class ModBlockEntities {

    public static BlockEntityType<MachineBlockEntity> MACHINE_BLOCK_ENTITY = BlockEntityType.Builder.create(
    MachineBlockEntity::new, MACHINE_BLOCK).build(null);

    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "machine_block_entity"), MACHINE_BLOCK_ENTITY);

    }
}
