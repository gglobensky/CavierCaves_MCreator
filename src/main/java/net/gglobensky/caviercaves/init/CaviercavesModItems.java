
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
	public static final RegistryObject<Item> GHOST_FUNGUS = block(CaviercavesModBlocks.GHOST_FUNGUS, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> WHITE_MARBLE = block(CaviercavesModBlocks.WHITE_MARBLE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> WHITE_CRYSTAL = block(CaviercavesModBlocks.WHITE_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> BLACK_MARBLE = block(CaviercavesModBlocks.BLACK_MARBLE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> GHOST_FUNGUS_STEM = block(CaviercavesModBlocks.GHOST_FUNGUS_STEM, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> GHOST_FUNGUS_BLOCK = block(CaviercavesModBlocks.GHOST_FUNGUS_BLOCK, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> MOON_STONE = block(CaviercavesModBlocks.MOON_STONE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> SPECTRAL_VINE = block(CaviercavesModBlocks.SPECTRAL_VINE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> DULL_MOON_STONE = block(CaviercavesModBlocks.DULL_MOON_STONE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> DULL_MOON_STONE_STAIRS = block(CaviercavesModBlocks.DULL_MOON_STONE_STAIRS, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> DULL_MOON_STONE_SLAB = block(CaviercavesModBlocks.DULL_MOON_STONE_SLAB, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> DULL_MOON_STONE_WALL = block(CaviercavesModBlocks.DULL_MOON_STONE_WALL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> DULL_MOON_STONE_BUTTON = block(CaviercavesModBlocks.DULL_MOON_STONE_BUTTON, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> DULL_MOON_STONE_PRESSURE_PLATE = block(CaviercavesModBlocks.DULL_MOON_STONE_PRESSURE_PLATE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> RED_CRYSTAL = block(CaviercavesModBlocks.RED_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> BLACK_CRYSTAL = block(CaviercavesModBlocks.BLACK_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> GREEN_CRYSTAL = block(CaviercavesModBlocks.GREEN_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> BLUE_CRYSTAL = block(CaviercavesModBlocks.BLUE_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> PURPLE_CRYSTAL = block(CaviercavesModBlocks.PURPLE_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> PINK_CRYSTAL = block(CaviercavesModBlocks.PINK_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> YELLOW_CRYSTAL = block(CaviercavesModBlocks.YELLOW_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> ORANGE_CRYSTAL = block(CaviercavesModBlocks.ORANGE_CRYSTAL, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> PURPURA_CAP = block(CaviercavesModBlocks.PURPURA_CAP, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> GREEN_MARBLE = block(CaviercavesModBlocks.GREEN_MARBLE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> RED_MARBLE = block(CaviercavesModBlocks.RED_MARBLE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> RHYOLITE = block(CaviercavesModBlocks.RHYOLITE, CaviercavesModTabs.TAB_CAVIER_CAVES);
	public static final RegistryObject<Item> PETRIFIED_OAK_LOG = block(CaviercavesModBlocks.PETRIFIED_OAK_LOG, CaviercavesModTabs.TAB_CAVIER_CAVES);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
