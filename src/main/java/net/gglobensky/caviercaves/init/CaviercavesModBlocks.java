
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.gglobensky.caviercaves.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.gglobensky.caviercaves.block.YellowCrystalBlock;
import net.gglobensky.caviercaves.block.WhiteMarbleBlock;
import net.gglobensky.caviercaves.block.WhiteCrystalBlock;
import net.gglobensky.caviercaves.block.SpectralVineBlock;
import net.gglobensky.caviercaves.block.RhyoliteBlock;
import net.gglobensky.caviercaves.block.RedMarbleBlock;
import net.gglobensky.caviercaves.block.RedCrystalBlock;
import net.gglobensky.caviercaves.block.PurpuraCapBlock;
import net.gglobensky.caviercaves.block.PurpleCrystalBlock;
import net.gglobensky.caviercaves.block.PinkCrystalBlock;
import net.gglobensky.caviercaves.block.PetrifiedOakLogBlock;
import net.gglobensky.caviercaves.block.OrangeCrystalBlock;
import net.gglobensky.caviercaves.block.MoonStoneBlock;
import net.gglobensky.caviercaves.block.GreenMarbleBlock;
import net.gglobensky.caviercaves.block.GreenCrystalBlock;
import net.gglobensky.caviercaves.block.GhostFungusStemBlock;
import net.gglobensky.caviercaves.block.GhostFungusBlockBlock;
import net.gglobensky.caviercaves.block.GhostFungusBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneWallBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneStairsBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneSlabBlock;
import net.gglobensky.caviercaves.block.DullMoonStonePressurePlateBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneButtonBlock;
import net.gglobensky.caviercaves.block.DullMoonStoneBlock;
import net.gglobensky.caviercaves.block.BlueCrystalBlock;
import net.gglobensky.caviercaves.block.BlackMarbleBlock;
import net.gglobensky.caviercaves.block.BlackCrystalBlock;
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
	public static final RegistryObject<Block> RED_CRYSTAL = REGISTRY.register("red_crystal", () -> new RedCrystalBlock());
	public static final RegistryObject<Block> BLACK_CRYSTAL = REGISTRY.register("black_crystal", () -> new BlackCrystalBlock());
	public static final RegistryObject<Block> GREEN_CRYSTAL = REGISTRY.register("green_crystal", () -> new GreenCrystalBlock());
	public static final RegistryObject<Block> BLUE_CRYSTAL = REGISTRY.register("blue_crystal", () -> new BlueCrystalBlock());
	public static final RegistryObject<Block> PURPLE_CRYSTAL = REGISTRY.register("purple_crystal", () -> new PurpleCrystalBlock());
	public static final RegistryObject<Block> PINK_CRYSTAL = REGISTRY.register("pink_crystal", () -> new PinkCrystalBlock());
	public static final RegistryObject<Block> YELLOW_CRYSTAL = REGISTRY.register("yellow_crystal", () -> new YellowCrystalBlock());
	public static final RegistryObject<Block> ORANGE_CRYSTAL = REGISTRY.register("orange_crystal", () -> new OrangeCrystalBlock());
	public static final RegistryObject<Block> PURPURA_CAP = REGISTRY.register("purpura_cap", () -> new PurpuraCapBlock());
	public static final RegistryObject<Block> GREEN_MARBLE = REGISTRY.register("green_marble", () -> new GreenMarbleBlock());
	public static final RegistryObject<Block> RED_MARBLE = REGISTRY.register("red_marble", () -> new RedMarbleBlock());
	public static final RegistryObject<Block> RHYOLITE = REGISTRY.register("rhyolite", () -> new RhyoliteBlock());
	public static final RegistryObject<Block> PETRIFIED_OAK_LOG = REGISTRY.register("petrified_oak_log", () -> new PetrifiedOakLogBlock());
}
