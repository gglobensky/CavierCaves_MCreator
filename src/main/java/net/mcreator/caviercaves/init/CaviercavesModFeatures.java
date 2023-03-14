
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.caviercaves.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import net.mcreator.caviercaves.world.features.plants.GhostFungusFeature;
import net.mcreator.caviercaves.world.features.ores.WhiteMarbleFeature;
import net.mcreator.caviercaves.world.features.ores.BlackMarbleFeature;
import net.mcreator.caviercaves.world.features.WestLateralCrystalFeature;
import net.mcreator.caviercaves.world.features.SouthLateralCrystalFeature;
import net.mcreator.caviercaves.world.features.NorthLateralCrystalFeature;
import net.mcreator.caviercaves.world.features.LargeDescendingCrystalFeature;
import net.mcreator.caviercaves.world.features.LargeAscendingCrystalFeature;
import net.mcreator.caviercaves.world.features.GhostFungusFeatureFeature;
import net.mcreator.caviercaves.world.features.EastLateralCrystalFeature;
import net.mcreator.caviercaves.world.features.DescendingCrystalFeature;
import net.mcreator.caviercaves.world.features.AscendingCrystalFeature;
import net.mcreator.caviercaves.CaviercavesMod;

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
}
