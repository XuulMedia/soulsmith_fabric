package com.xuul.soulsmith;

import com.xuul.soulsmith.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Soulsmith implements ModInitializer {

    //Mod ID
    public static final String MOD_ID = "soulsmith";

    //Item Groups
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModItems.RYKLE));



    @Override
    public void onInitialize() {
        ModItems.register();
        ModBlocks.register();
        ModBlockEntities.register();
        GuiRegistry.register();
        RecipeRegistry.register();

    }
}

