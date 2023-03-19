package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.gglobensky.caviercaves.init.CaviercavesModBlocks;

public class CreateSpectralVineProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		int xOffsetRange = 16;
		int zOffsetRange = 16;
		
		int minLength = 1;
		int maxLength = 20;
		int length = 0;

		Double surfaceYLevel = 0.00;
		int vineQty = Utils.randomRange(12, 36);
        ResourceLocation spectralCaverns = new ResourceLocation("caviercaves:spectral_caverns");
        
		// Set random seed same as world seed
		for (int i = 0; i < (int) (vineQty); i++) {
			int xOffset = Utils.randomRange(-xOffsetRange, xOffsetRange);
			int zOffset = Utils.randomRange(-zOffsetRange, zOffsetRange);

			surfaceYLevel = Utils.getCeilingYLevel(world, x + xOffset, y, z + zOffset);
			if (surfaceYLevel != null && world.getBiome(new BlockPos(x + xOffset, surfaceYLevel, z + zOffset)).is(spectralCaverns)) {
				length = Utils.randomRange(minLength, maxLength);
				for (int k = 0; k < (length); k++) {
					BlockPos pos = new BlockPos(x + xOffset, surfaceYLevel - k, z + zOffset);
					
					if (!world.isEmptyBlock(new BlockPos(x + xOffset, surfaceYLevel - k, z + zOffset))) {
						return;
					}
					
					world.setBlock(pos, CaviercavesModBlocks.SPECTRAL_VINE.get().defaultBlockState(), 3);
				}
			}
		}
	}
}
