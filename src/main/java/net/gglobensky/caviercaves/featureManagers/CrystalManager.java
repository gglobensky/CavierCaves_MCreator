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

    //TODO: There may be a better way than using areBlocksInPath. Maybe just checking for air.
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
                    BlockPos[] startPoints = {currentPos};

                    if (currentPos != null && !Utils.areBlocksInPath(world, startPoints, crystalTypes, 20, localUp)) {
                        world.setBlock(currentPos, block.defaultBlockState(), 3);
                    }
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
                    BlockPos[] startPoints = {currentPos};

                    if (currentPos != null && !Utils.areBlocksInPath(world, startPoints, crystalTypes, 20, localUp) && !(wIndex == wLow && hIndex == height - 1 && lIndex == lLow)){
                        world.setBlock(currentPos, block.defaultBlockState(), 3);

                        if (wIndex == wPeak && hIndex == height - 1 && lIndex == lPeak){
                            BlockPos p = BlockDrawing.localPosition(0, 1, 0, currentPos.getX(), currentPos.getY(), currentPos.getZ(), localUp);
                            world.setBlock(p, block.defaultBlockState(), 3);
                        }
                    }
                });
            });
        }
    }
}
