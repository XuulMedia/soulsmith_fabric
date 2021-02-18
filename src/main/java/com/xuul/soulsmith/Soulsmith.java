package com.xuul.soulsmith;



import com.xuul.soulsmith.blocks.MachineBlock;
//import com.xuul.soulsmith.gui.MachineGuiDescription;
import com.xuul.soulsmith.gui.MachineGuiDescription;
import com.xuul.soulsmith.gui.MachineScreenHandler;
import com.xuul.soulsmith.registry.ModBlockEntities;
import com.xuul.soulsmith.registry.ModBlocks;
import com.xuul.soulsmith.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class Soulsmith implements ModInitializer {

    //Mod ID
    public static final String MOD_ID = "soulsmith";

    //Item Groups
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModItems.RYKLE));
    public static final ScreenHandlerType<? extends MachineGuiDescription> MACHINE_SCREEN_HANDLER = ;


    private static ScreenHandlerType<MachineScreenHandler> MACHINE_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "machine_block"), MachineScreenHandler::new);


    @Override
    public void onInitialize() {
        ModItems.register();
        ModBlocks.register();
        ModBlockEntities.register();




    }
}

