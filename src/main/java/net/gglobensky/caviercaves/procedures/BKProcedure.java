package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;

public class BKProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double crystalHeight = 0;
		double i = 0;
		double j = 0;
		double HasFoundSurface = 0;
		double surfaceYLevel = 0;
		double maxCrystalHeight = 0;
		double minCrystalHeight = 0;
		double minCrystalClearance = 0;
		double crystalQty = 0;
		double xOffsetRange = 0;
		double zOffsetRange = 0;
		double xOffset = 0;
		double zOffset = 0;
		boolean hasStartedInAir = false;
		crystalQty = Mth.nextInt(RandomSource.create(), 12, 36);
		minCrystalHeight = 2;
		maxCrystalHeight = 9;
		xOffsetRange = 24;
		zOffsetRange = 24;
		for (int index0 = 0; index0 < (int) (crystalQty); index0++) {
			xOffset = Mth.nextInt(RandomSource.create(), (int) (0 - xOffsetRange), (int) xOffsetRange);
			zOffset = Mth.nextInt(RandomSource.create(), (int) (0 - zOffsetRange), (int) zOffsetRange);
			j = 1;
			HasFoundSurface = 0;
			hasStartedInAir = world.isEmptyBlock(new BlockPos(x + xOffset, y - j, z + zOffset));
			for (int index1 = 0; index1 < (int) (50); index1++) {
				if (hasStartedInAir) {
					surfaceYLevel = y - j;
					if ((world.getBlockState(new BlockPos(x + xOffset, surfaceYLevel, z + zOffset))).is(BlockTags.create(new ResourceLocation("forge:stone")))) {
						HasFoundSurface = 1;
						break;
					}
				} else {
					surfaceYLevel = y + j;
					if (world.isEmptyBlock(new BlockPos(x + xOffset, surfaceYLevel, z + zOffset))) {
						surfaceYLevel = surfaceYLevel - 1;
						HasFoundSurface = 1;
						break;
					}
				}
				j = j + 1;
			}
			i = 0;
			if (HasFoundSurface == 1 && world.getBiome(new BlockPos(x + xOffset, surfaceYLevel + i, z + zOffset)).is(new ResourceLocation("caviercaves:crystal_cave"))) {
				crystalHeight = minCrystalHeight + Math.random() * (maxCrystalHeight - minCrystalHeight);
				for (int index2 = 0; index2 < (int) (crystalHeight); index2++) {
					world.setBlock(new BlockPos(x + xOffset, surfaceYLevel + i, z + zOffset), CaviercavesModBlocks.WHITE_CRYSTAL.get().defaultBlockState(), 3);
					i = i + 1;
				}
			}
		}
	}
}
