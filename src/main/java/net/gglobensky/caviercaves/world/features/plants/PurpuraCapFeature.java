
package net.gglobensky.caviercaves.world.features.plants;

import net.gglobensky.caviercaves.utils.ModPlacementUtils;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Holder;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;

import java.util.Set;
import java.util.List;

public class PurpuraCapFeature extends RandomPatchFeature {
	public static PurpuraCapFeature FEATURE = null;
	public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CONFIGURED_FEATURE = null;
	public static Holder<PlacedFeature> PLACED_FEATURE = null;

	public static Feature<?> feature() {
		FEATURE = new PurpuraCapFeature();
		CONFIGURED_FEATURE = FeatureUtils.register("caviercaves:purpura_cap", FEATURE,
				FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CaviercavesModBlocks.PURPURA_CAP.get())), List.of(), 64));
		PLACED_FEATURE = PlacementUtils.register("caviercaves:purpura_cap", CONFIGURED_FEATURE, List.of(CountPlacement.of(5), RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
		return FEATURE;
	}

	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public PurpuraCapFeature() {
		super(RandomPatchConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
		WorldGenLevel world = context.level();
		if (!generate_dimensions.contains(world.getLevel().dimension()))
			return false;
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!ModPlacementUtils.isUnderground(world, x, y, z))
			return false;
		return super.place(context);
	}
}
