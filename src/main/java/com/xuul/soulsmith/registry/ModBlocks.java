package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.Soulsmith;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final OreBlock ORE_TIN = new OreBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool().strength(3.0f,3.0f).sounds(BlockSoundGroup.STONE)
    );

    public static final OreBlock ORE_NETHER_SILVER = new OreBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool().strength(3.0f,3.0f).sounds(BlockSoundGroup.NETHER_GOLD_ORE)
    );

    public static final OreBlock ORE_RYKLE = new OreBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool().strength(3.0f,3.0f).sounds(BlockSoundGroup.STONE)
    );

    public static final OreBlock ORE_SHROUD_CRYSTAL = new OreBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 3)
            .requiresTool().strength(3.0f,3.0f).sounds(BlockSoundGroup.NETHER_GOLD_ORE)
    );

    public static final OreBlock ORE_TYRELLUS = new OreBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES, 4)
            .requiresTool().strength(3.0f,3.0f).sounds(BlockSoundGroup.STONE)
    );


    public static final Block BLOCK_RYKLE = new Block(FabricBlockSettings.of(Material.GLASS).breakByTool(FabricToolTags.PICKAXES, 1)
            .strength(3.0F, 3.0F).sounds(BlockSoundGroup.LANTERN)
    );
    public static final Block BLOCK_TIN = new Block(FabricBlockSettings.of(Material.METAL).breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.METAL)
    );
    public static final Block BLOCK_BRONZE = new Block(FabricBlockSettings.of(Material.METAL).breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.METAL)
    );
    public static final Block BLOCK_STEEL = new Block(FabricBlockSettings.of(Material.METAL).breakByTool(FabricToolTags.PICKAXES, 2)
            .requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.METAL)
    );
    public static final Block BLOCK_BLACK_BRONZE = new Block(FabricBlockSettings.of(Material.METAL).breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.METAL)
    );
    public static final Block BLOCK_SILVER = new Block(FabricBlockSettings.of(Material.METAL).breakByTool(FabricToolTags.PICKAXES, 1)
            .requiresTool().strength(3.0F, 3.0F).sounds(BlockSoundGroup.METAL)
    );


    public static void registerItems() {

        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "ore_tin"), ORE_TIN);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "ore_nether_silver"), ORE_NETHER_SILVER);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "ore_rykle"), ORE_RYKLE);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "ore_shroud_crystal"), ORE_SHROUD_CRYSTAL);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "ore_tyrellus"), ORE_TYRELLUS);

        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "block_rykle"), BLOCK_RYKLE);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "block_tin"), BLOCK_TIN);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "block_bronze"), BLOCK_BRONZE);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "block_steel"), BLOCK_STEEL);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "block_black_bronze"), BLOCK_BLACK_BRONZE);
        Registry.register(Registry.BLOCK, new Identifier(Soulsmith.MOD_ID, "block_silver"), BLOCK_SILVER);

    }
}
