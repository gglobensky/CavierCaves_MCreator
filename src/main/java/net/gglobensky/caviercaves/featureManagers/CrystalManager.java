package net.gglobensky.caviercaves.featureManagers;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;
import net.gglobensky.caviercaves.procedures.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class CrystalManager {
    enum CrystalOrientation {
        UP,
        DOWN,
        EAST,
        WEST,
        NORTH,
        SOUTH
    }

    private static int[][] orientations = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    public static void createCrystals(LevelAccessor world, double x, double y, double z) {
        int minQuantity = 4;
        int maxQuantity = 16;
        int maxOffset = 20;
        int minHeight = 2;
        int maxHeight = 8;
        int thickness = 1;
        int maxNumberOfBentSections = 3;
        float bendingChance = 0.25f;

        Double surfaceLevel;
        double wPosition;
        double lPosition;

        int quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = x + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = z + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getFloorYLevel(world, wPosition, y, lPosition);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, wPosition, surfaceLevel, lPosition, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.UP);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++){
            wPosition = x + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = z + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getCeilingYLevel(world, wPosition, y, lPosition);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, wPosition, surfaceLevel, lPosition, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.DOWN);
        }

        minQuantity = 1;
        maxQuantity = 4;
        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = y + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = z + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getWestWallXValue(world, x, wPosition, lPosition);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, surfaceLevel, wPosition, lPosition, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.EAST);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = y + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = z + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getEastWallXValue(world, x, wPosition, lPosition);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, surfaceLevel, wPosition, lPosition, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.WEST);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = x + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = y + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getSouthWallZValue(world, wPosition, lPosition, z);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, wPosition, lPosition, surfaceLevel, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.NORTH);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = x + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = y + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getSouthWallZValue(world, wPosition, lPosition, z);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, wPosition, lPosition, surfaceLevel, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.SOUTH);
        }
    }

    public static void createLargeCrystals(LevelAccessor world, double x, double y, double z) {
        int minQuantity = 1;
        int maxQuantity = 3;
        int maxOffset = 20;
        int minHeight = 6;
        int maxHeight = 12;
        int thickness = 2;
        int maxNumberOfBentSections = 2;
        float bendingChance = 0.25f;

        Double surfaceLevel;
        double wPosition;
        double lPosition;

        int quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = x + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = z + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getFloorYLevel(world, wPosition, y, lPosition);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, wPosition, surfaceLevel - 1, lPosition, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.UP);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++){
            wPosition = x + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = z + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getCeilingYLevel(world, wPosition, y, lPosition);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, wPosition, surfaceLevel + 1, lPosition, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.DOWN);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = y + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = z + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getWestWallXValue(world, x, wPosition, lPosition);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, surfaceLevel - 1, wPosition, lPosition, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.EAST);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = y + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = z + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getEastWallXValue(world, x, wPosition, lPosition);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, surfaceLevel + 1, wPosition, lPosition, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.WEST);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = x + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = y + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getSouthWallZValue(world, wPosition, lPosition, z);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, wPosition, lPosition, surfaceLevel - 1, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.NORTH);
        }

        quantity = Utils.randomRange(minQuantity, maxQuantity);
        for (int i = 0; i < quantity; i++) {
            wPosition = x + Utils.randomRange(-maxOffset, maxOffset);
            lPosition = y + Utils.randomRange(-maxOffset, maxOffset);

            surfaceLevel = Utils.getNorthWallZValue(world, wPosition, lPosition, z);
            if (surfaceLevel == null)
                continue;
            createCrystal(world, wPosition, lPosition, surfaceLevel + 1, minHeight, maxHeight, thickness, maxNumberOfBentSections, bendingChance, CrystalOrientation.SOUTH);
        }
    }

    private static void createCrystal(LevelAccessor world, double x, double y, double z, int minHeight, int maxHeight, int thickness, int maxNumberOfBentSections, float bendingChance, CrystalOrientation crystalOrientation){
        int height = Utils.randomRange(minHeight, maxHeight);
        int orientationIndex = (int) (Math.random() * orientations.length);
        int[] currentOrientation = {0, 0};

        /*if (!Utils.isUnderground(world, x, y, z)){
            return;
        }*/

        if (Math.random() <= bendingChance){
            int sections = (int) (Math.random() * maxNumberOfBentSections) + 1;
            int snappingInterval = (int) (height / sections);
            int intervalCounter = 0;

            for (int i = 0; i < height; i++){
                createLayer(world, x, y, z, thickness, i, i == height - 1, crystalOrientation, currentOrientation);

                // Avoid having a bend as the last layer
                if (i != height -1 && intervalCounter++ >= snappingInterval){
                    intervalCounter = 0;
                    currentOrientation[0] += orientations[orientationIndex][0];
                    currentOrientation[1] += orientations[orientationIndex][1];

                    createLayer(world, x, y, z, thickness, i, false, crystalOrientation, currentOrientation);
                }
            }
        }
        else{
            for (int i = 0; i < height; i++){
                createLayer(world, x, y, z, thickness, i, i == height - 1, crystalOrientation, currentOrientation);
            }
        }
    }

    private static void createLayer(LevelAccessor world, double x, double y, double z, int thickness, int currentLayer, boolean lastRow, CrystalOrientation crystalOrientation, int[] orientation){
        int[] peak = { 0, 0 };
        int[] low = { 0, 0 };

        if (lastRow && thickness > 1){
            peak[0] = Utils.randomRange(0, 2) == 1 ? 0 : thickness - 1;
            peak[1] = Utils.randomRange(0, 2) == 1 ? 0 : thickness - 1;

            low[0] = peak[0] == 0 ? thickness - 1 : 0;
            low[1] = peak[1] == 0 ? thickness - 1 : 0;
        }

        for (int wOffset = 0; wOffset < thickness; wOffset++){
            for (int lOffset = 0; lOffset < thickness; lOffset++){
                if (lastRow && thickness > 1 && wOffset == low[0] && lOffset == low[1]){
                    continue;
                }

                BlockPos pos = getOffsetPosition(wOffset, lOffset, x, y, z, crystalOrientation);
                pos = getOrientedBlockPos(pos.getX(), pos.getY(), pos.getZ(), orientation, crystalOrientation);
                int[] increment = getIncrement(currentLayer, crystalOrientation);

                world.setBlock(new BlockPos(pos.getX() + increment[0], pos.getY() + increment[1], pos.getZ() + increment[2]), CaviercavesModBlocks.WHITE_CRYSTAL.get().defaultBlockState(), 3);

                if (lastRow && thickness > 1 && wOffset == peak[0] && lOffset == peak[1]){
                    increment = getIncrement(currentLayer + 1, crystalOrientation);

                    world.setBlock(new BlockPos(pos.getX() + increment[0], pos.getY() + increment[1], pos.getZ() + increment[2]), CaviercavesModBlocks.WHITE_CRYSTAL.get().defaultBlockState(), 3);
                }
            }
        }
    }

    //Takes in x y z values for an upright elements and outputs the orientated coordinates
    private static int[] getIncrement(int currentValue, CrystalOrientation crystalOrientation){
        switch(crystalOrientation) {
            case UP:
                return new int[] {0, currentValue, 0};
            case DOWN:
                return new int[] {0, -currentValue, 0};
            case WEST:
                return new int[] {-currentValue, 0, 0};
            case EAST:
                return new int[] {currentValue, 0, 0};
            case NORTH:
                return new int[] {0, 0, currentValue};
            case SOUTH:
                return new int[] {0, 0, -currentValue};
            default:
                return new int[] {0, 0, 0};
        }
    }

    private static BlockPos getOffsetPosition(int wOffset, int lOffset, double x, double y, double z, CrystalOrientation crystalOrientation){
        switch(crystalOrientation) {
            case UP, DOWN:
                return new BlockPos(x + wOffset, y, z + lOffset);
            case WEST, EAST:
                return new BlockPos(x, y + wOffset, z + lOffset);
            case NORTH, SOUTH:
                return new BlockPos(x + wOffset, y + lOffset, z);
            default:
                return new BlockPos(x, y, z);
        }
    }

    private static BlockPos getOrientedBlockPos(double x, double y, double z, int[] orientation, CrystalOrientation crystalOrientation){
        switch(crystalOrientation) {
            case UP:
                return new BlockPos(x + orientation[0], y, z + orientation[1]);
            case DOWN:
                return new BlockPos(x - orientation[0], y, z - orientation[1]);
            case WEST:
                return new BlockPos(x, y + orientation[0], z + orientation[1]);
            case EAST:
                return new BlockPos(x, y - orientation[0], z - orientation[1]);
            case NORTH:
                return new BlockPos(x + orientation[0], y + orientation[1], z);
            case SOUTH:
                return new BlockPos(x - orientation[0], y - orientation[1], z);
            default:
                return new BlockPos(x, y, z);
        }
    }

}
