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

    public static final Item TIN_INGOT = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item BRONZE_INGOT = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item STEEL_INGOT = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item SILVER_INGOT = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item BLACK_BRONZE_INGOT = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));

    public static final Item COPPER_NUGGET = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item TIN_NUGGET = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item BRONZE_NUGGET = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item STEEL_NUGGET = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final Item SILVER_NUGGET = new Item(new Item.Settings().group(Soulsmith.ITEM_GROUP));


    //Block Items
    public static final BlockItem RYKLE_BLOCK = new BlockItem(ModBlocks.RYKLE_BLOCK, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem TIN_BLOCK = new BlockItem(ModBlocks.TIN_BLOCK, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem BRONZE_BLOCK = new BlockItem(ModBlocks.BRONZE_BLOCK, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem STEEL_BLOCK = new BlockItem(ModBlocks.STEEL_BLOCK, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem SILVER_BLOCK = new BlockItem(ModBlocks.SILVER_BLOCK, new Item.Settings().group(Soulsmith.ITEM_GROUP));
    public static final BlockItem BLACK_BRONZE_BLOCK = new BlockItem(ModBlocks.BLACK_BRONZE_BLOCK, new Item.Settings().group(Soulsmith.ITEM_GROUP));

//    public static final BlockItem RYKLE_ORE = new BlockItem(ModBlocks.RYKLE_ORE, new Item.Settings().group(Soulsmith.ITEM_GROUP));
//    public static final BlockItem SHROUD_CRYSTAL_ORE = new BlockItem(ModBlocks.SHROUD_CRYSTAL_ORE, new Item.Settings().group(Soulsmith.ITEM_GROUP));
//    public static final BlockItem TYRELLUS_ORE = new BlockItem(ModBlocks.TYRELLUS_ORE, new Item.Settings().group(Soulsmith.ITEM_GROUP));
//    public static final BlockItem TIN_ORE = new BlockItem(ModBlocks.TIN_ORE, new Item.Settings().group(Soulsmith.ITEM_GROUP));
//    public static final BlockItem SILVER_ORE = new BlockItem(ModBlocks.SILVER_ORE, new Item.Settings().group(Soulsmith.ITEM_GROUP));



    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID, "rykle"), RYKLE);

        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"shroud_crystal"), SHROUD);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"tyrellus"), TYRELLUS);

        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"tin_ingot"), TIN_INGOT);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"bronze_ingot"), BRONZE_INGOT);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"steel_ingot"), STEEL_INGOT);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"silver_ingot"), SILVER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"black_bronze_ingot"), BLACK_BRONZE_INGOT);

        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"copper_nugget"), COPPER_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"tin_nugget"), TIN_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"bronze_nugget"), BRONZE_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"steel_nugget"), STEEL_NUGGET);
        Registry.register(Registry.ITEM, new Identifier(Soulsmith.MOD_ID,"silver_nugget"), SILVER_NUGGET);

    }
}
