
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.gglobensky.caviercaves.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.gglobensky.caviercaves.CaviercavesMod;

public class CaviercavesModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CaviercavesMod.MODID);
	public static final RegistryObject<Item> GHOST_FUNGUS = block(CaviercavesModBlocks.GHOST_FUNGUS, CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Item> WHITE_MARBLE = block(CaviercavesModBlocks.WHITE_MARBLE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> WHITE_CRYSTAL = block(CaviercavesModBlocks.WHITE_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> BLACK_MARBLE = block(CaviercavesModBlocks.BLACK_MARBLE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> GHOST_FUNGUS_STEM = block(CaviercavesModBlocks.GHOST_FUNGUS_STEM, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> GHOST_FUNGUS_BLOCK = block(CaviercavesModBlocks.GHOST_FUNGUS_BLOCK, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> CHROMASTONE = block(CaviercavesModBlocks.CHROMASTONE, CaviercavesModTabs.TAB_CAVIER_CAVES);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
