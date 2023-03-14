package net.mcreator.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

import net.mcreator.caviercaves.init.CaviercavesModBlocks;

public class CreateAscendingCrystalProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double xOffsetRange = 24;
		double zOffsetRange = 24;
		double xOffset = 0;
		double zOffset = 0;
		int minCrystalHeight = 2;
		int maxCrystalHeight = 9;
		int crystalHeight = 0;

		Double surfaceYLevel = 0.00;
		int crystalQty = Mth.nextInt(RandomSource.create(), 12, 36);

		// TODO:Set random seed same as world seed
		for (int i = 0; i < (int) (crystalQty); i++) {
			xOffset = Mth.nextInt(RandomSource.create(), (int) (0 - xOffsetRange), (int) xOffsetRange);
			zOffset = Mth.nextInt(RandomSource.create(), (int) (0 - zOffsetRange), (int) zOffsetRange);

			surfaceYLevel = UtilsProcedure.getFloorYLevel(world, x + xOffset, y - 1, z + zOffset);
			if (surfaceYLevel != null && world.getBiome(new BlockPos(x + xOffset, surfaceYLevel, z + zOffset)).is(new ResourceLocation("caviercaves:crystal_cave"))) {
				crystalHeight = minCrystalHeight + (int) (Math.random() * (maxCrystalHeight - minCrystalHeight));
				for (int k = 0; k <  (crystalHeight); k++) {
					world.setBlock(new BlockPos(x + xOffset, surfaceYLevel + k, z + zOffset), CaviercavesModBlocks.WHITE_CRYSTAL.get().defaultBlockState(), 3);
				}
			}
		}
	}
}
