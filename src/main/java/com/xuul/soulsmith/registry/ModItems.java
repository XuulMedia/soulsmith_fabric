package com.xuul.soulsmith.registry;

import com.xuul.soulsmith.Soulsmith;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    //Items
    public static final Item RYKLE = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item SHROUD = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item TYRELLUS = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));

    public static final Item INGOT_TIN = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item INGOT_BRONZE = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item INGOT_STEEL = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item INGOT_SILVER = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item INGOT_BLACK_BRONZE = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));

    public static final Item NUGGET_COPPER = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item NUGGET_TIN = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item NUGGET_BRONZE = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item NUGGET_STEEL = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item NUGGET_SILVER = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));


    //Block Items
    public static final BlockItem BLOCK_RYKLE = new BlockItem(ModBlocks.BLOCK_RYKLE, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem BLOCK_TIN = new BlockItem(ModBlocks.BLOCK_TIN, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem BLOCK_BRONZE = new BlockItem(ModBlocks.BLOCK_BRONZE, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem BLOCK_STEEL = new BlockItem(ModBlocks.BLOCK_STEEL, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem BLOCK_SILVER = new BlockItem(ModBlocks.BLOCK_SILVER, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem BLOCK_BLACK_BRONZE = new BlockItem(ModBlocks.BLOCK_BLACK_BRONZE, new Item.Settings().group(Soulsmith.ITEM_GROUP));

    public static final BlockItem ORE_RYKLE = new BlockItem(ModBlocks.ORE_RYKLE, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem ORE_SHROUD_CRYSTAL = new BlockItem(ModBlocks.ORE_SHROUD_CRYSTAL, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem ORE_TYRELLUS = new BlockItem(ModBlocks.ORE_TYRELLUS, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem ORE_TIN = new BlockItem(ModBlocks.ORE_TIN, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem ORE_NETHER_SILVER = new BlockItem(ModBlocks.ORE_NETHER_SILVER, new Item.Settings().group(Soulsmith.ITEM_GROUP));



    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"rykle"), RYKLE);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"shroud_crystal"), SHROUD);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"tyrellus"), TYRELLUS);

        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ingot_tin"), INGOT_TIN);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ingot_bronze"), INGOT_BRONZE);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ingot_steel"), INGOT_STEEL);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ingot_silver"), INGOT_SILVER);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ingot_black_bronze"), INGOT_BLACK_BRONZE);

        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"nugget_copper"), NUGGET_COPPER);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"nugget_tin"), NUGGET_TIN);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"nugget_bronze"), NUGGET_BRONZE);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"nugget_steel"), NUGGET_STEEL);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"nugget_silver"), NUGGET_SILVER);


        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"block_rykle"), BLOCK_RYKLE);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"block_tin"), BLOCK_TIN);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"block_bronze"), BLOCK_BRONZE);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"block_steel"), BLOCK_STEEL);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"block_silver"), BLOCK_SILVER);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"block_black_bronze"), BLOCK_BLACK_BRONZE);

        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ore_rykle"), ORE_RYKLE);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ore_shroud_crystal"), ORE_SHROUD_CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ore_tyrellus"), ORE_TYRELLUS);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ore_tin"), ORE_TIN);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"ore_nether_silver"), ORE_NETHER_SILVER);

















    }
}
