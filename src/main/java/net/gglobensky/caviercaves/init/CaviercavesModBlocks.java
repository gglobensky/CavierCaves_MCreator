
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.gglobensky.caviercaves.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.gglobensky.caviercaves.block.WhiteMarbleBlock;
import net.gglobensky.caviercaves.block.WhiteCrystalBlock;
import net.gglobensky.caviercaves.block.SpectralVineBlock;
import net.gglobensky.caviercaves.block.MoonStoneBlock;
import net.gglobensky.caviercaves.block.GhostFungusStemBlock;
import net.gglobensky.caviercaves.block.GhostFungusBlockBlock;
import net.gglobensky.caviercaves.block.GhostFungusBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneWallBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneStairsBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneSlabBlock;
import net.gglobensky.caviercaves.block.DullMoonStonePressurePlateBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneButtonBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneBlock;
import net.gglobensky.caviercaves.block.BlackMarbleBlock;
import net.gglobensky.caviercaves.CaviercavesMod;

public class CaviercavesModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, CaviercavesMod.MODID);
	public static final RegistryObject<Block> GHOST_FUNGUS = REGISTRY.register("ghost_fungus", () -> new GhostFungusBlock());
	public static final RegistryObject<Block> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new WhiteMarbleBlock());
	public static final RegistryObject<Block> WHITE_CRYSTAL = REGISTRY.register("white_crystal", () -> new WhiteCrystalBlock());
	public static final RegistryObject<Block> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlackMarbleBlock());
	public static final RegistryObject<Block> GHOST_FUNGUS_STEM = REGISTRY.register("ghost_fungus_stem", () -> new GhostFungusStemBlock());
	public static final RegistryObject<Block> GHOST_FUNGUS_BLOCK = REGISTRY.register("ghost_fungus_block", () -> new GhostFungusBlockBlock());
	public static final RegistryObject<Block> MOON_STONE = REGISTRY.register("moon_stone", () -> new MoonStoneBlock());
	public static final RegistryObject<Block> SPECTRAL_VINE = REGISTRY.register("spectral_vine", () -> new SpectralVineBlock());
	public static final RegistryObject<Block> DULL_MOON_STONE = REGISTRY.register("dull_moon_stone", () -> new DullMoonStoneBlock());
	public static final RegistryObject<Block> DULL_MOON_STONE_STAIRS = REGISTRY.register("dull_moon_stone_stairs", () -> new DullMoonStoneStairsBlock());
	public static final RegistryObject<Block> DULL_MOON_STONE_SLAB = REGISTRY.register("dull_moon_stone_slab", () -> new DullMoonStoneSlabBlock());
	public static final RegistryObject<Block> DULL_MOON_STONE_WALL = REGISTRY.register("dull_moon_stone_wall", () -> new DullMoonStoneWallBlock());
	public static final RegistryObject<Block> DULL_MOON_STONE_BUTTON = REGISTRY.register("dull_moon_stone_button", () -> new DullMoonStoneButtonBlock());
	public static final RegistryObject<Block> DULL_MOON_STONE_PRESSURE_PLATE = REGISTRY.register("dull_moon_stone_pressure_plate", () -> new DullMoonStonePressurePlateBlock());
}
