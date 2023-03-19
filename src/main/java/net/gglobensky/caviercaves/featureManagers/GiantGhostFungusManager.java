package net.gglobensky.caviercaves.featureManagers;

import net.gglobensky.caviercaves.procedures.Utils;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;

public class GiantGhostFungusManager {
    private static int[][] orientations = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    enum CornerType {
        NONE,
        SIMPLE,
        RANDOM;

        public static CornerType getValue(int value) {
            switch (value) {
                case 0:
                    return NONE;
                case 1:
                    return SIMPLE;
                case 2:
                    return RANDOM;
                default:
                    throw new IllegalArgumentException("int value: " + value + " not related to enum value");
            }
        }
    }

    public static void createMushrooms(LevelAccessor world, double x, double y, double z) {
        int xOffsetRange = 16;
        int zOffsetRange = 16;
        int xOffset = 0;
        int zOffset = 0;

        Double surfaceYLevel = 0.00;
        int MushroomQty = Utils.randomRange(12, 32);

        for (int i = 0; i < (int) (MushroomQty); i++) {
            xOffset = Utils.randomRange((int) (0 - xOffsetRange), (int) xOffsetRange);
            zOffset = Utils.randomRange((int) (0 - zOffsetRange), (int) zOffsetRange);

            surfaceYLevel = Utils.getFloorYLevel(world, x + xOffset, y - 1, z + zOffset);
            int ySpace = Utils.getYSpace(world, x, y, z, 20);
            int mushroomHeight = Utils.randomRange(0, ySpace);

            if (surfaceYLevel != null && world.getBiome(new BlockPos(x + xOffset, surfaceYLevel, z + zOffset)).is(new ResourceLocation("caviercaves:spectral_caverns"))) {
                if (mushroomHeight < 4){
                    BlockPos capStart = createSmallTrunk(world, x + xOffset, surfaceYLevel, z + zOffset);
                    createSmallCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
                }
                else if (mushroomHeight < 8){
                    BlockPos capStart = createNormalTrunk(world, x + xOffset, surfaceYLevel, z + zOffset);
                    createNormalCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
                }
                else if (mushroomHeight < 12){
                    BlockPos capStart = createLongTrunk(world, x + xOffset, surfaceYLevel, z + zOffset);
                    createLargeCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
                }
                else{
                    BlockPos capStart = createThickTrunk(world, x + xOffset, surfaceYLevel, z + zOffset);
                    createThickCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
                }
            }

        }
    }

    private static BlockPos createSmallTrunk(LevelAccessor world, double x, double y, double z){
        int minTrunkHeight = 2;
        int maxTrunkHeight = 6;
        int trunkThickness = 1;
        int maxNumberOfBentSections = 1;
        float bendingChance = 0f;

        return createTrunk(world, x, y, z, minTrunkHeight, maxTrunkHeight, trunkThickness, maxNumberOfBentSections, bendingChance);
    }

    private static BlockPos createNormalTrunk(LevelAccessor world, double x, double y, double z){
        int minTrunkHeight = 4;
        int maxTrunkHeight = 8;
        int trunkThickness = 1;
        int maxNumberOfBentSections = 2;
        float bendingChance = 0.5f;

        return createTrunk(world, x, y, z, minTrunkHeight, maxTrunkHeight, trunkThickness, maxNumberOfBentSections, bendingChance);
    }

    private static BlockPos createLongTrunk(LevelAccessor world, double x, double y, double z){
        int minTrunkHeight = 8;
        int maxTrunkHeight = 12;
        int trunkThickness = 1;
        int maxNumberOfBentSections = 3;
        float bendingChance = 0.75f;

        return createTrunk(world, x, y, z, minTrunkHeight, maxTrunkHeight, trunkThickness, maxNumberOfBentSections, bendingChance);
    }

    private static BlockPos createThickTrunk(LevelAccessor world, double x, double y, double z){
        int minTrunkHeight = 8;
        int maxTrunkHeight = 16;
        int trunkThickness = 2;
        int maxNumberOfBentSections = 4;
        float bendingChance = 0.5f;

        return createTrunk(world, x, y, z, minTrunkHeight, maxTrunkHeight, trunkThickness, maxNumberOfBentSections, bendingChance);
    }

    private static void createSmallCap(LevelAccessor world, double x, double y, double z){
        float skirtChance = 1f;
        float doubleSkirtChance = 0.15f;

        int capHeight = Utils.randomRange(1, 3);
        int capRadius = 1;
        int widthDecreaseRate = 1;
        int heightIncreaseRate = Utils.randomRange(1, 2);

        createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthDecreaseRate, heightIncreaseRate, capRadius, false);
    }

    private static void createNormalCap(LevelAccessor world, double x, double y, double z){
        float skirtChance = 0.75f;
        float doubleSkirtChance = 0.5f;

        int capHeight = Utils.randomRange(2, 5);
        int capRadius = capHeight - 1;
        int widthDecreaseRate = 1;
        int heightIncreaseRate = 1;

        createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthDecreaseRate, heightIncreaseRate, capRadius, false);
    }

    private static void createLargeCap(LevelAccessor world, double x, double y, double z){
        float skirtChance = 0.85f;
        float doubleSkirtChance = 0.75f;

        int capHeight = Utils.randomRange(3, 6);
        int capRadius = capHeight - 1;
        int widthDecreaseRate = Utils.randomRange(1, 3);
        int heightIncreaseRate = Utils.randomRange(1, 3);

        createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthDecreaseRate, heightIncreaseRate, capRadius, false);
    }

    private static void createThickCap(LevelAccessor world, double x, double y, double z){
        float skirtChance = 0.85f;
        float doubleSkirtChance = 0.75f;

        int capHeight = Utils.randomRange(3, 6);
        int capRadius = capHeight;
        int widthDecreaseRate = Utils.randomRange(1, 3);
        int heightIncreaseRate = Utils.randomRange(1, 3);

        createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthDecreaseRate, heightIncreaseRate, capRadius, true);
    }

    private static BlockPos createTrunk(LevelAccessor world, double x, double y, double z, int minTrunkHeight, int maxTrunkHeight, int trunkThickness, int maxNumberOfBentSections, float bendingChance){
        int trunkHeight = Utils.randomRange(minTrunkHeight, maxTrunkHeight);
        int orientationIndex = (int) (Math.random() * orientations.length);
        int[] currentOrientation = {0, 0};

        if (Math.random() <= bendingChance){
            int trunkSections = (int) (Math.random() * maxNumberOfBentSections) + 1;
            int snappingInterval = (int) (trunkHeight / trunkSections);
            int intervalCounter = 0;

            for (int i = 0; i < trunkHeight; i++){
                for (int xOffset = 0; xOffset < trunkThickness; xOffset++){
                    for (int zOffset = 0; zOffset < trunkThickness; zOffset++){
                        world.setBlock(new BlockPos(x + currentOrientation[0] + xOffset, y + i, z + currentOrientation[1] + zOffset), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);
                    }
                }

                if (intervalCounter++ >= snappingInterval){
                    intervalCounter = 0;
                    currentOrientation[0] += orientations[orientationIndex][0];
                    currentOrientation[1] += orientations[orientationIndex][1];

                    for (int xOffset = 0; xOffset < trunkThickness; xOffset++){
                        for (int zOffset = 0; zOffset < trunkThickness; zOffset++){
                            world.setBlock(new BlockPos(x + currentOrientation[0] + xOffset, y + i, z + currentOrientation[1] + zOffset), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
        else{
            for (int i = 0; i < trunkHeight; i++){
                world.setBlock(new BlockPos(x, y + i, z), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);
            }
        }

        return new BlockPos(x + currentOrientation[0], y + trunkHeight, z + currentOrientation[1]);
    }

    private static void createCap(LevelAccessor world, double x, double y, double z, float skirtChance, float doubleSkirtChance, int capHeight, int widthDecreaseRate, int heightIncreaseRate, int capRadius, boolean thickCap){
        if (Math.random() < skirtChance){
            createCapSkirt(world, x, y - 1, z, capRadius, CornerType.getValue(Utils.randomRange(0, 3)), thickCap);

            if (Math.random() < doubleSkirtChance){
                createCapSkirt(world, x, y - 2, z, capRadius, CornerType.getValue(Utils.randomRange(0, 3)), thickCap);
            }
        }

        for (int yOffset = 0; yOffset < capHeight; yOffset += heightIncreaseRate){
            for (int i = 0; i < heightIncreaseRate; i++){
                createCapLayer(world, x, y + yOffset + i, z, capRadius, CornerType.getValue(Utils.randomRange(0, 3)), thickCap);
            }
            capRadius -= widthDecreaseRate;
        }
    }

    private static void createCapLayer(LevelAccessor world, double x, double y, double z, int capRadius, CornerType cornerType, boolean thickCap){
        int upperLimit = thickCap? capRadius + 1 : capRadius;

        for (int xOffset = -capRadius; xOffset <= upperLimit; xOffset++){
            for (int zOffset = -capRadius; zOffset <= upperLimit; zOffset++){
                boolean placeBlock = true;

                boolean isCorner =
                        (xOffset == -capRadius && zOffset == -capRadius) 	||
                        (xOffset == -capRadius && zOffset == upperLimit) 	||
                        (xOffset == upperLimit && zOffset == -capRadius) 	||
                        (xOffset == upperLimit && zOffset == upperLimit);

                if (capRadius != 1 && isCorner){
                    if (cornerType == CornerType.NONE){
                        placeBlock = false;
                    }
                    else if(cornerType == CornerType.RANDOM){
                        if (Math.random() < 0.5f){
                            placeBlock = false;
                        }
                    }
                }

                if (placeBlock){
                    world.setBlock(new BlockPos(x + xOffset, y, z + zOffset), CaviercavesModBlocks.GHOST_FUNGUS_BLOCK.get().defaultBlockState(), 3);
                }
            }
        }
    }

    private static void createCapSkirt(LevelAccessor world, double x, double y, double z, int capRadius, CornerType cornerType, boolean thickCap){
        int upperLimit = thickCap? capRadius + 1 : capRadius;

        for (int xOffset = -capRadius; xOffset <= upperLimit; xOffset++){
            for (int zOffset = -capRadius; zOffset <= upperLimit; zOffset++){

                if ((xOffset == -capRadius || xOffset == upperLimit) || (zOffset == -capRadius || zOffset == upperLimit)){
                    boolean placeBlock = true;

                    boolean isCorner =
                            (xOffset == -capRadius && zOffset == -capRadius) 	||
                            (xOffset == -capRadius && zOffset == upperLimit) 	||
                            (xOffset == upperLimit && zOffset == -capRadius) 	||
                            (xOffset == upperLimit && zOffset == upperLimit);

                    if (isCorner){
                        if (cornerType == CornerType.NONE){
                            placeBlock = false;
                        }
                        else if(cornerType == CornerType.RANDOM){
                            if (Math.random() < 0.5f){
                                placeBlock = false;
                            }
                        }
                    }

                    if (placeBlock){
                        world.setBlock(new BlockPos(x + xOffset, y, z + zOffset), CaviercavesModBlocks.GHOST_FUNGUS_BLOCK.get().defaultBlockState(), 3);
                    }
                }
            }
        }
    }
}
