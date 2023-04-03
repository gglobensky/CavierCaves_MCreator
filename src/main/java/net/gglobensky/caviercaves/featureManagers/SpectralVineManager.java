package net.gglobensky.caviercaves.procedures;

        import net.gglobensky.caviercaves.utils.ModPlacementUtils;
        import net.gglobensky.caviercaves.utils.RandomUtils;
        import net.minecraft.world.level.LevelAccessor;
        import net.minecraft.resources.ResourceLocation;
        import net.minecraft.core.BlockPos;
        import net.gglobensky.caviercaves.init.CaviercavesModBlocks;

public class SpectralVineManager {
    public static void createVines(LevelAccessor world, double x, double y, double z) {
        int xOffsetRange = 16;
        int zOffsetRange = 16;

        int minLength = 1;
        int maxLength = 20;
        int length = 0;

        Double surfaceYLevel = 0.00;
        int vineQty = RandomUtils.randomRange(12, 36);
        ResourceLocation spectralCaverns = new ResourceLocation("caviercaves:spectral_caverns");

        // Set random seed same as world seed
        for (int i = 0; i < (int) (vineQty); i++) {
            int xOffset = RandomUtils.randomRange(-xOffsetRange, xOffsetRange);
            int zOffset = RandomUtils.randomRange(-zOffsetRange, zOffsetRange);

            surfaceYLevel = ModPlacementUtils.getCeilingYLevel(world, x + xOffset, y, z + zOffset);
            if (surfaceYLevel != null && world.getBiome(new BlockPos(x + xOffset, surfaceYLevel, z + zOffset)).is(spectralCaverns)) {
                length = RandomUtils.randomRange(minLength, maxLength);
                for (int k = 1; k < (length); k++) {
                    BlockPos pos = new BlockPos(x + xOffset, surfaceYLevel - k, z + zOffset);

                    if (!world.isEmptyBlock(pos)) {
                        return;
                    }

                    world.setBlock(pos, CaviercavesModBlocks.SPECTRAL_VINE.get().defaultBlockState(), 3);
                }
            }
        }
    }
}
