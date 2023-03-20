
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.gglobensky.caviercaves.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import net.gglobensky.caviercaves.world.features.plants.PurpuraCapFeature;
import net.gglobensky.caviercaves.world.features.plants.GhostFungusFeature;
import net.gglobensky.caviercaves.world.features.ores.WhiteMarbleFeature;
import net.gglobensky.caviercaves.world.features.ores.RhyoliteFeature;
import net.gglobensky.caviercaves.world.features.ores.MoonStoneFeature;
import net.gglobensky.caviercaves.world.features.ores.BlackMarbleFeature;
import net.gglobensky.caviercaves.world.features.SpectralVinesFeature;
import net.gglobensky.caviercaves.world.features.SeleniteCrystalFeature;
import net.gglobensky.caviercaves.world.features.PurpuraCapFeatureFeature;
import net.gglobensky.caviercaves.world.features.GiantGhostFungusFeature;
import net.gglobensky.caviercaves.world.features.GhostFungusSpectralBiomeFeature;
import net.gglobensky.caviercaves.world.features.GhostFungusFeatureFeature;
import net.gglobensky.caviercaves.CaviercavesMod;

@Mod.EventBusSubscriber
public class CaviercavesModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, CaviercavesMod.MODID);
	public static final RegistryObject<Feature<?>> GHOST_FUNGUS_FEATURE = REGISTRY.register("ghost_fungus_feature", GhostFungusFeatureFeature::new);
	public static final RegistryObject<Feature<?>> GHOST_FUNGUS = REGISTRY.register("ghost_fungus", GhostFungusFeature::feature);
	public static final RegistryObject<Feature<?>> WHITE_MARBLE = REGISTRY.register("white_marble", WhiteMarbleFeature::feature);
	public static final RegistryObject<Feature<?>> BLACK_MARBLE = REGISTRY.register("black_marble", BlackMarbleFeature::feature);
	public static final RegistryObject<Feature<?>> GIANT_GHOST_FUNGUS = REGISTRY.register("giant_ghost_fungus", GiantGhostFungusFeature::feature);
	public static final RegistryObject<Feature<?>> GHOST_FUNGUS_SPECTRAL_BIOME = REGISTRY.register("ghost_fungus_spectral_biome", GhostFungusSpectralBiomeFeature::new);
	public static final RegistryObject<Feature<?>> MOON_STONE = REGISTRY.register("moon_stone", MoonStoneFeature::feature);
	public static final RegistryObject<Feature<?>> SPECTRAL_VINES = REGISTRY.register("spectral_vines", SpectralVinesFeature::feature);
	public static final RegistryObject<Feature<?>> SELENITE_CRYSTAL = REGISTRY.register("selenite_crystal", SeleniteCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> PURPURA_CAP = REGISTRY.register("purpura_cap", PurpuraCapFeature::feature);
	public static final RegistryObject<Feature<?>> PURPURA_CAP_FEATURE = REGISTRY.register("purpura_cap_feature", PurpuraCapFeatureFeature::new);
	public static final RegistryObject<Feature<?>> RHYOLITE = REGISTRY.register("rhyolite", RhyoliteFeature::feature);
}
