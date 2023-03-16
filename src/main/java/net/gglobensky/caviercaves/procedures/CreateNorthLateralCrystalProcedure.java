package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import java.util.Random;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;

public class CreateNorthLateralCrystalProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Random random = new Random();
		long seed = random.nextLong();
		random.setSeed(seed);

		double xOffsetRange = 24;
		double yOffsetRange = 24;
		double xOffset = 0;
		double yOffset = 0;
		int minCrystalLength = 2;
		int maxCrystalLength = 5;
		int crystalLength = 0;
        int[][] offsets = { {0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
		Double surfaceZValue = 0.00;
		int crystalQty = random.nextInt(12, 36);

		// Set random seed same as world seed
		for (int i = 0; i < (int) (crystalQty); i++) {
            int[] offset = offsets[random.nextInt(0, 5)];

			xOffset = random.nextInt((int) (0 - xOffsetRange), (int) xOffsetRange);
			yOffset = random.nextInt((int) (0 - yOffsetRange), (int) yOffsetRange);

			surfaceZValue = UtilsProcedure.getNorthWallZValue(world, x + xOffset, y + yOffset, z + 1);
			if (surfaceZValue != null && world.getBiome(new BlockPos(x + xOffset, y + yOffset, surfaceZValue)).is(new ResourceLocation("caviercaves:crystal_cave"))) {
				crystalLength = minCrystalLength + (int) (Math.random() * (maxCrystalLength - minCrystalLength));
                int midPoint = crystalLength / 2;
				for (int k = 0; k < (crystalLength); k++) {
                    BlockPos blockPos;

                    if (k < midPoint){
                        blockPos = new BlockPos(x + xOffset, y + yOffset, surfaceZValue - k);
                    }
                    else{
                        blockPos = new BlockPos(x + xOffset + offset[0], y + yOffset + offset[1], surfaceZValue - k + 1);
                    }

					world.setBlock(blockPos, CaviercavesModBlocks.WHITE_CRYSTAL.get().defaultBlockState(), 3);
				}
			}
		}
	}
}
