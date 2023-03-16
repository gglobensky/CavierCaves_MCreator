
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
import net.gglobensky.caviercaves.block.GhostFungusStemBlock;
import net.gglobensky.caviercaves.block.GhostFungusBlockBlock;
import net.gglobensky.caviercaves.block.GhostFungusBlock;
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
}
