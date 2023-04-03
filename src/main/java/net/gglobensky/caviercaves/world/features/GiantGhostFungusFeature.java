
package net.gglobensky.caviercaves.world.features;

import net.gglobensky.caviercaves.featureManagers.GiantGhostFungusManager;
import net.gglobensky.caviercaves.utils.ModPlacementUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Holder;

import java.util.Set;
import java.util.List;

public class GiantGhostFungusFeature extends Feature<NoneFeatureConfiguration> {
	public static GiantGhostFungusFeature FEATURE = null;
	public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_FEATURE = null;
	public static Holder<PlacedFeature> PLACED_FEATURE = null;

	public static Feature<?> feature() {
		FEATURE = new GiantGhostFungusFeature();
		CONFIGURED_FEATURE = FeatureUtils.register("caviercaves:giant_ghost_fungus", FEATURE, FeatureConfiguration.NONE);
		PLACED_FEATURE = net.minecraft.data.worldgen.placement.PlacementUtils.register("caviercaves:giant_ghost_fungus", CONFIGURED_FEATURE,
				List.of(CountPlacement.of(64), RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64)), BiomeFilter.biome()));
		return FEATURE;
	}

	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);
	private final List<Block> base_blocks;
	private StructureTemplate template = null;

	public GiantGhostFungusFeature() {
		super(NoneFeatureConfiguration.CODEC);
		base_blocks = List.of(Blocks.AIR, Blocks.CAVE_AIR);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		if (!generate_dimensions.contains(context.level().getLevel().dimension()))
			return false;

		int x = context.origin().getX();
		int z = context.origin().getZ();

		WorldGenLevel world = context.level();

		BlockPos pos = ModPlacementUtils.findRandomCaveAir(world, x, z, -64, 64);

		if (pos == null)
			return false;

		int y = pos.getY();

		GiantGhostFungusManager.createMushrooms(world, x, y, z);

		return true;
	}
}
