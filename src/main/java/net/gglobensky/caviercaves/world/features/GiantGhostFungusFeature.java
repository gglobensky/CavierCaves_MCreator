
package net.gglobensky.caviercaves.world.features;

import net.gglobensky.caviercaves.featureManagers.GiantGhostFungusManager;
import net.gglobensky.caviercaves.procedures.Utils;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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
		PLACED_FEATURE = PlacementUtils.register("caviercaves:giant_ghost_fungus", CONFIGURED_FEATURE, List.of());
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

		int y = context.level().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z);
		y = Utils.randomRange(8 + context.level().getMinBuildHeight(), Math.max(y, 9 + context.level().getMinBuildHeight()));

		WorldGenLevel world = context.level();

		GiantGhostFungusManager.createMushrooms(world, x, y, z);

		return true;
	}
}
