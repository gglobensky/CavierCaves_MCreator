package net.gglobensky.caviercaves.featureManagers;

import net.gglobensky.caviercaves.enums.Orientation;
import net.gglobensky.caviercaves.init.CaviercavesModBlocks;
import net.gglobensky.caviercaves.procedures.Utils;
import net.gglobensky.caviercaves.utils.BlockDrawing;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static net.gglobensky.caviercaves.init.CaviercavesModBlocks.*;

public class CrystalManager {

    private static int[][] offsets = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    private static Block[] crystalTypes = {
            WHITE_CRYSTAL.get(),
            BLUE_CRYSTAL.get()
    };

    public static void createCrystals(LevelAccessor world, double x, double y, double z){
        Block block = crystalTypes[Utils.randomRange(0, crystalTypes.length)];

        for (Orientation localUp : Orientation.values()) {
            int width = 1;

            BlockDrawing.disperse(x, y, z, 4, 6, 10, 10, 8, localUp, (pos) -> {
                BlockDrawing.createTrunkStructure(world, pos, width, 3, 8, 1, 3, localUp, block, true);
            });
        }

        for (Orientation localUp : Orientation.values()) {
            int width = 2;

            BlockDrawing.disperse(x, y, z, 1, 4, 10, 10, 8, localUp, (pos) -> {
                BlockPos topCenter = BlockDrawing.createTrunkStructure(world, pos, width, 3, 12, 1, 3, localUp, block, true);

                if (topCenter != null)
                    createCrystalNub(world, topCenter, width, block, localUp);
            });
        }
    }

    private static void createCrystalNub(LevelAccessor world, BlockPos pos, int width, Block block, Orientation localUp){
        int[] bounds = Utils.divideByTwo(width);
        bounds[0] = -bounds[0];

        int xPeak = Utils.randomFrom(bounds);
        int zPeak = Utils.randomFrom(bounds);

        int xLow = xPeak == bounds[0]? bounds[1] - 1 : bounds[0];
        int zLow = zPeak == bounds[0]? bounds[1] - 1 : bounds[0];

        BlockPos lowPos = BlockDrawing.localPosition(xLow, 0, zLow, pos.getX(), pos.getY(), pos.getZ(), localUp);
        BlockPos peakPos = BlockDrawing.localPosition(xPeak, 1, zPeak, pos.getX(), pos.getY(), pos.getZ(), localUp);

        world.setBlock(lowPos, Blocks.CAVE_AIR.defaultBlockState(), 3);
        world.setBlock(peakPos, block.defaultBlockState(), 3);
    }

}
