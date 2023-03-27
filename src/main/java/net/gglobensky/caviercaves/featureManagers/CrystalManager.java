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

        int heightBuffer = Utils.randomRange(3, 8 + 1);
        int sectionsBuffer = Utils.randomRange(1, 3 + 1);
        sectionsBuffer = sectionsBuffer < heightBuffer / 2? sectionsBuffer : 1;

        for (Orientation localUp : Orientation.values()) {
            int width = 1;
            int height = heightBuffer;
            int sections = sectionsBuffer;

            BlockDrawing.disperse(x, y, z, 4, 6, 10, 10, 8, localUp, (pos) -> {
                BlockDrawing.createTrunkStructure(world, pos, width, height, sections, localUp,  true, (currentPos, currentWidth, wIndex, hIndex, lIndex) -> {
                    world.setBlock(currentPos, block.defaultBlockState(), 3);
                });
            });
        }

        heightBuffer = Utils.randomRange(6, 12 + 1);
        sectionsBuffer = Utils.randomRange(1, 3 + 1);
        sectionsBuffer = sectionsBuffer < heightBuffer / 2? sectionsBuffer : 1;

        for (Orientation localUp : Orientation.values()) {
            int width = 2;
            int height = heightBuffer;
            int sections = sectionsBuffer;

            int cornerIndexes[] = { 0, width - 1 };
            int wPeak = Utils.randomFrom(cornerIndexes);
            int lPeak = Utils.randomFrom(cornerIndexes);

            int wLow = wPeak == cornerIndexes[0] ? cornerIndexes[1] : cornerIndexes[0];
            int lLow = lPeak == cornerIndexes[0] ? cornerIndexes[1] : cornerIndexes[0];


            BlockDrawing.disperse(x, y, z, 1, 4, 10, 10, 8, localUp, (pos) -> {
                BlockDrawing.createTrunkStructure(world, pos, width, height, sections, localUp,  true, (currentPos, currentWidth, wIndex, hIndex, lIndex) -> {
                    if (currentPos != null && !(wIndex == wLow && hIndex == height - 1 && lIndex == lLow)){
                        world.setBlock(currentPos, block.defaultBlockState(), 3);

                        if (wIndex == wPeak && hIndex == height - 1 && lIndex == lPeak){
                            currentPos = BlockDrawing.localPosition(0, 1, 0, currentPos.getX(), currentPos.getY(), currentPos.getZ(), localUp);
                            world.setBlock(currentPos, block.defaultBlockState(), 3);
                        }
                    }

                });

                /*if (topCenter != null)
                    createCrystalNub(world, topCenter, width, block, localUp);*/
            });
        }
    }

    private static void createCrystalNub(LevelAccessor world, BlockPos pos, int width, Block block, Orientation localUp){
        int[] bounds = Utils.divideByTwo(width);

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
