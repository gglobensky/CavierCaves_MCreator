package net.mcreator.caviercaves.procedures;

import java.util.Random;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

import net.mcreator.caviercaves.init.CaviercavesModBlocks;

public class CreateLargeAscendingCrystalProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {    
		Random random = new Random();
		long seed = random.nextLong();
		random.setSeed(seed);
		
		double xOffsetRange = 24;
		double zOffsetRange = 24;
		double xOffset = 0;
		double zOffset = 0;
		int minCrystalHeight = 2;
		int maxCrystalHeight = 9;
		int crystalHeight = 0;
		int[][] offsets = { {0, 0}, {0, 1}, {1, 0}, {1, 1} };
		
		Double surfaceYLevel = 0.00;
		int crystalQty = random.nextInt(12, 36);
		
		for (int i = 0; i < (int) (crystalQty); i++) {
			int lowCorner = random.nextInt(0, 4);
			int highCorner = lowCorner - 3;
	
			if (highCorner < 0){
				highCorner = lowCorner + 3;
			}
	        
			xOffset = random.nextInt((int) (0 - xOffsetRange), (int) xOffsetRange);
			zOffset = random.nextInt((int) (0 - zOffsetRange), (int) zOffsetRange);

			surfaceYLevel = UtilsProcedure.getFloorYLevel(world, x + xOffset, y - 2, z + zOffset);
			if (surfaceYLevel != null && world.getBiome(new BlockPos(x + xOffset, surfaceYLevel, z + zOffset)).is(new ResourceLocation("caviercaves:crystal_cave"))) {
				crystalHeight = minCrystalHeight + random.nextInt(0, (maxCrystalHeight - minCrystalHeight));
				for (int k = 0; k < (crystalHeight); k++) {
					for (int j = 0; j < 4; j++) {
						if (!(k == crystalHeight - 1 && j == lowCorner)){
							world.setBlock(new BlockPos(x + xOffset + offsets[j][0], surfaceYLevel + k, z + zOffset + offsets[j][1]), CaviercavesModBlocks.WHITE_CRYSTAL.get().defaultBlockState(), 3);
						}
						
						if (k == crystalHeight - 1 && j == highCorner){
							world.setBlock(new BlockPos(x + xOffset + offsets[j][0], surfaceYLevel + k + 1, z + zOffset + offsets[j][1]), CaviercavesModBlocks.WHITE_CRYSTAL.get().defaultBlockState(), 3);
						}
					}
				}
			}
		}
	}
}