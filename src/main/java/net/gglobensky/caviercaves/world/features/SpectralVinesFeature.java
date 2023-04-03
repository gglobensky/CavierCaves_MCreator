
package net.gglobensky.caviercaves.world.features;

import net.gglobensky.caviercaves.procedures.SpectralVineManager;
import net.gglobensky.caviercaves.utils.ModPlacementUtils;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.BlockPos;

import net.gglobensky.caviercaves.procedures.CreateSpectralVineProcedure;

import java.util.Set;
import java.util.List;

public class SpectralVinesFeature extends Feature<NoneFeatureConfiguration> {
	public static SpectralVinesFeature FEATURE = null;
	public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_FEATURE = null;
	public static Holder<PlacedFeature> PLACED_FEATURE = null;

	public static Feature<?> feature() {
		FEATURE = new SpectralVinesFeature();
		CONFIGURED_FEATURE = FeatureUtils.register("caviercaves:spectral_vines", FEATURE, FeatureConfiguration.NONE);
		PLACED_FEATURE = PlacementUtils.register("caviercaves:spectral_vines", CONFIGURED_FEATURE, List.of());
		return FEATURE;
	}

	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);
	private StructureTemplate template = null;

	public SpectralVinesFeature() {
		super(NoneFeatureConfiguration.CODEC);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		if (!generate_dimensions.contains(context.level().getLevel().dimension()))
			return false;

		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int z = context.origin().getZ();
		BlockPos pos = ModPlacementUtils.findRandomCaveAir(world, x, z, -64, 64);

		if (pos == null)
			return false;

		SpectralVineManager.createVines(world, pos.getX(), pos.getY(), pos.getZ());

		return true;
	}
}
