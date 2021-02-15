package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.Soulsmith;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {


    public static final Block RYKLE_BLOCK = new Block(FabricBlockSettings
            .of(Material.GLASS)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.LANTERN)
    );
    public static final Block TIN_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.METAL)
    );
    public static final Block BRONZE_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.METAL)
    );
    public static final Block STEEL_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.METAL)
    );
    public static final Block BLACK_BRONZE_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.METAL)
    );
    public static final Block SILVER_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool()
            .strength(3.0F, 3.0F)
            .sounds(BlockSoundGroup.METAL)
    );



    public static void registerItems() {
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "rykle_block"), RYKLE_BLOCK);

        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "tin_block"), TIN_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "bronze_block"), BRONZE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "steel_block"), STEEL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "black_bronze_block"), BLACK_BRONZE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "silver_block"), SILVER_BLOCK);

    }
}
