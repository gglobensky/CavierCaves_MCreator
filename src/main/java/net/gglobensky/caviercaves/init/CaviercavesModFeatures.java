
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.gglobensky.caviercaves.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import net.gglobensky.caviercaves.world.features.plants.GhostFungusFeature;
import net.gglobensky.caviercaves.world.features.ores.WhiteMarbleFeature;
import net.gglobensky.caviercaves.world.features.ores.ChromastoneFeature;
import net.gglobensky.caviercaves.world.features.ores.BlackMarbleFeature;
import net.gglobensky.caviercaves.world.features.WestLateralCrystalFeature;
import net.gglobensky.caviercaves.world.features.SouthLateralCrystalFeature;
import net.gglobensky.caviercaves.world.features.NorthLateralCrystalFeature;
import net.gglobensky.caviercaves.world.features.LargeDescendingCrystalFeature;
import net.gglobensky.caviercaves.world.features.LargeAscendingCrystalFeature;
import net.gglobensky.caviercaves.world.features.GiantGhostFungusFeatureBKFeature;
import net.gglobensky.caviercaves.world.features.GiantGhostFungusFeature;
import net.gglobensky.caviercaves.world.features.GhostFungusSpectralBiomeFeature;
import net.gglobensky.caviercaves.world.features.GhostFungusFeatureFeature;
import net.gglobensky.caviercaves.world.features.EastLateralCrystalFeature;
import net.gglobensky.caviercaves.world.features.DescendingCrystalFeature;
import net.gglobensky.caviercaves.world.features.AscendingCrystalFeature;
import net.gglobensky.caviercaves.CaviercavesMod;

@Mod.EventBusSubscriber
public class CaviercavesModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, CaviercavesMod.MODID);
	public static final RegistryObject<Feature<?>> GHOST_FUNGUS_FEATURE = REGISTRY.register("ghost_fungus_feature", GhostFungusFeatureFeature::new);
	public static final RegistryObject<Feature<?>> GHOST_FUNGUS = REGISTRY.register("ghost_fungus", GhostFungusFeature::feature);
	public static final RegistryObject<Feature<?>> WHITE_MARBLE = REGISTRY.register("white_marble", WhiteMarbleFeature::feature);
	public static final RegistryObject<Feature<?>> LARGE_ASCENDING_CRYSTAL = REGISTRY.register("large_ascending_crystal", LargeAscendingCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> LARGE_DESCENDING_CRYSTAL = REGISTRY.register("large_descending_crystal", LargeDescendingCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> DESCENDING_CRYSTAL = REGISTRY.register("descending_crystal", DescendingCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> ASCENDING_CRYSTAL = REGISTRY.register("ascending_crystal", AscendingCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> NORTH_LATERAL_CRYSTAL = REGISTRY.register("north_lateral_crystal", NorthLateralCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> SOUTH_LATERAL_CRYSTAL = REGISTRY.register("south_lateral_crystal", SouthLateralCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> EAST_LATERAL_CRYSTAL = REGISTRY.register("east_lateral_crystal", EastLateralCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> WEST_LATERAL_CRYSTAL = REGISTRY.register("west_lateral_crystal", WestLateralCrystalFeature::feature);
	public static final RegistryObject<Feature<?>> BLACK_MARBLE = REGISTRY.register("black_marble", BlackMarbleFeature::feature);
	public static final RegistryObject<Feature<?>> CHROMASTONE = REGISTRY.register("chromastone", ChromastoneFeature::feature);
	public static final RegistryObject<Feature<?>> GIANT_GHOST_FUNGUS_FEATURE_BK = REGISTRY.register("giant_ghost_fungus_feature_bk", GiantGhostFungusFeatureBKFeature::new);
	public static final RegistryObject<Feature<?>> GIANT_GHOST_FUNGUS = REGISTRY.register("giant_ghost_fungus", GiantGhostFungusFeature::feature);
	public static final RegistryObject<Feature<?>> GHOST_FUNGUS_SPECTRAL_BIOME = REGISTRY.register("ghost_fungus_spectral_biome", GhostFungusSpectralBiomeFeature::new);
}
