
package net.gglobensky.caviercaves.world.features;

import net.gglobensky.caviercaves.featureManagers.CrystalManager;
import net.gglobensky.caviercaves.init.CaviercavesModBiomes;
import net.gglobensky.caviercaves.procedures.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Holder;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.List;

public class SeleniteCrystalFeature extends Feature<NoneFeatureConfiguration> {
	public static SeleniteCrystalFeature FEATURE = null;
	public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_FEATURE = null;
	public static Holder<PlacedFeature> PLACED_FEATURE = null;

	public static Feature<?> feature() {
		FEATURE = new SeleniteCrystalFeature();
		CONFIGURED_FEATURE = FeatureUtils.register("caviercaves:selenite_crystal", FEATURE, FeatureConfiguration.NONE);
		PLACED_FEATURE = PlacementUtils.register("caviercaves:selenite_crystal", CONFIGURED_FEATURE, List.of());
		return FEATURE;
	}

	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);
	//private final Set<ResourceLocation> generate_biomes = Set.of(new ResourceLocation("caviercaves:crystal_cave"));
	private StructureTemplate template = null;

	public SeleniteCrystalFeature() {
		super(NoneFeatureConfiguration.CODEC);
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

		/*if (!generate_biomes.contains(world.getBiome(new BlockPos(x, y, z))))
			return false;*/

		CrystalManager.createCrystals(world, x, y, z);
		//CrystalManager.createLargeCrystals(world, x, y, z);

		return true;
	}
}
