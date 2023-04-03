package net.gglobensky.caviercaves.featureManagers;

import net.gglobensky.caviercaves.enums.Orientation;
import net.gglobensky.caviercaves.utils.ModPlacementUtils;
import net.gglobensky.caviercaves.utils.ShapeUtils;
import net.gglobensky.caviercaves.utils.RandomUtils;
import net.gglobensky.caviercaves.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

import net.minecraft.server.level.WorldGenRegion;
import static net.gglobensky.caviercaves.init.CaviercavesModBlocks.*;

public class CrystalManager {

    private static int[][] offsets = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    private static Block[] crystalTypes = {
            WHITE_CRYSTAL.get(),
            BLUE_CRYSTAL.get()
    };

    //TODO: There may be a better way than using areBlocksInPath. Maybe just checking for air.
    public static void createCrystals(LevelAccessor world, double x, double y, double z){
        Block block = crystalTypes[RandomUtils.randomRange(0, crystalTypes.length)];

        Orientation localUp = Orientation.values()[RandomUtils.randomRange(0, Orientation.values().length)];

        BlockPos pos = ModPlacementUtils.tryGetSurface(world, x, y, z, localUp);

        if (pos == null){
            return;
        }

        if (RandomUtils.randomBool(0.75f)) {
            int width = 1;
            int height = RandomUtils.randomRange(3, 8 + 1);
            int sections = RandomUtils.randomRange(1, 3 + 1);

            sections = sections < height / 2? sections : 1;

            ShapeUtils.createTrunkStructure(world, pos, width, height, sections, localUp, (currentPos, currentWidth, wIndex, hIndex, lIndex) -> {
                BlockPos[] startPoints = {currentPos};

                if (currentPos != null && !ModPlacementUtils.areBlocksInPath(world, startPoints, crystalTypes, 20, localUp)) {
                    world.setBlock(currentPos, block.defaultBlockState(), 3);
                }
            });
        }
        else {
            int width = 2;
            int height = RandomUtils.randomRange(6, 12 + 1);
            int sections = RandomUtils.randomRange(1, 3 + 1);

            sections = sections < height / 2? sections : 1;

            int cornerIndexes[] = {0, width - 1};
            int wPeak = RandomUtils.randomFrom(cornerIndexes);
            int lPeak = RandomUtils.randomFrom(cornerIndexes);

            int wLow = wPeak == cornerIndexes[0] ? cornerIndexes[1] : cornerIndexes[0];
            int lLow = lPeak == cornerIndexes[0] ? cornerIndexes[1] : cornerIndexes[0];

            ShapeUtils.createTrunkStructure(world, pos, width, height, sections, localUp, (currentPos, currentWidth, wIndex, hIndex, lIndex) -> {
                if (currentPos != null && !(wIndex == wLow && hIndex == height - 1 && lIndex == lLow)) {
                    world.setBlock(currentPos, block.defaultBlockState(), 3);

                    if (wIndex == wPeak && hIndex == height - 1 && lIndex == lPeak) {
                        BlockPos p = Utils.localPosition(0, 1, 0, currentPos.getX(), currentPos.getY(), currentPos.getZ(), localUp);
                        world.setBlock(p, block.defaultBlockState(), 3);
                    }
                }
            });
        }
    }

    public static boolean validateWidePlacement(LevelAccessor world, int sections, int height, BlockPos pos, int[] leanDirection, Orientation localUp){
        BlockPos pos2 = Utils.localPosition((leanDirection[0] * sections) + 1, 0, (leanDirection[1] * sections) + 1, pos.getX(), pos.getY(), pos.getZ(), localUp);
        BlockPos[] startPoints = { pos, pos2 };
        BlockPos endPoint = Utils.localPosition(0, height, 0, pos2.getX(), pos2.getY(), pos2.getZ(), localUp);
        BlockPos[] writeCheck = { pos, endPoint };

        WorldGenRegion region = (WorldGenRegion)world;

        for (BlockPos blockPos : writeCheck){
            if (!region.ensureCanWrite(blockPos)){
                return false;
            }
        }

        return !ModPlacementUtils.areBlocksInPath(world, startPoints, crystalTypes, 20, localUp);
    }

    public static boolean validatePlacement(LevelAccessor world, int sections, int height, BlockPos pos, int[] leanDirection, Orientation localUp){
        WorldGenRegion region = (WorldGenRegion)world;
        BlockPos endPos = Utils.localPosition(0, height, 0, pos.getX(), pos.getY(), pos.getZ(), localUp);
        BlockPos[] positions = { pos, endPos };

        for (BlockPos blockPos : positions){
            if (!region.ensureCanWrite(blockPos)){
                return false;
            }
        }

        return true;
    }
}
