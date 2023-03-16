package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import java.util.Random;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;

public class CreateEastLateralCrystalProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Random random = new Random();
		long seed = random.nextLong();
		random.setSeed(seed);

		double yOffsetRange = 24;
		double zOffsetRange = 24;
		double yOffset = 0;
		double zOffset = 0;
		int minCrystalLength = 2;
		int maxCrystalLength = 5;
		int crystalLength = 0;
        int[][] offsets = { {0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
		Double surfaceXValue = 0.00;
		int crystalQty = random.nextInt(12, 36);

		// Set random seed same as world seed
		for (int i = 0; i < (int) (crystalQty); i++) {
            int[] offset = offsets[random.nextInt(0, 5)];

			zOffset = random.nextInt((int) (0 - zOffsetRange), (int) zOffsetRange);
			yOffset = random.nextInt((int) (0 - yOffsetRange), (int) yOffsetRange);

			surfaceXValue = UtilsProcedure.getEastWallZValue(world, x + 1, y + yOffset, z + zOffset);
			if (surfaceXValue != null && world.getBiome(new BlockPos(surfaceXValue, y + yOffset, z + zOffset)).is(new ResourceLocation("caviercaves:crystal_cave"))) {
				crystalLength = minCrystalLength + (int) (Math.random() * (maxCrystalLength - minCrystalLength));
                int midPoint = crystalLength / 2;
				for (int k = 0; k < (crystalLength); k++) {
                    BlockPos blockPos;

                    if (k < midPoint){
                        blockPos = new BlockPos(surfaceXValue - k, y + yOffset, z + zOffset);
                    }
                    else{
                        blockPos = new BlockPos(surfaceXValue - k + 1, y + yOffset + offset[1], z + zOffset + offset[0]);
                    }

					world.setBlock(blockPos, CaviercavesModBlocks.WHITE_CRYSTAL.get().defaultBlockState(), 3);
				}
			}
		}
	}
}
