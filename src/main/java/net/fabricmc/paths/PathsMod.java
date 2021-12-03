package net.fabricmc.paths;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathsMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("paths");

	public static final Block SAND_PATH_BLOCK = new SandPathBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(1.0F).sounds(BlockSoundGroup.SAND));

	public static final Identifier SAND_PATH_IDENTIFIER = new Identifier("paths", "sand_path_block");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registry.BLOCK, SAND_PATH_IDENTIFIER, SAND_PATH_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("paths", "sand_path_block"), new BlockItem(SAND_PATH_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
	}
}
