package net.gglobensky.caviercaves.featureManagers;

import net.gglobensky.caviercaves.enums.Orientation;
import net.gglobensky.caviercaves.utils.ModPlacementUtils;
import net.gglobensky.caviercaves.utils.ShapeUtils;
import net.gglobensky.caviercaves.utils.RandomUtils;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;
import net.minecraft.world.level.block.Block;

public class GiantGhostFungusManager {
    private static int[][] orientations = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    private static Block block = CaviercavesModBlocks.GHOST_FUNGUS_STEM.get();
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
        Double surface = ModPlacementUtils.getFloorYLevel(world, x, y, z);

        if (surface == null)
            return;

        BlockPos pos = new BlockPos(x, surface, z);

        if (world.getBiome(pos).is(new ResourceLocation("caviercaves:spectral_caverns"))) {
            int ySpace = ModPlacementUtils.getYSpace(world, pos.getX(), pos.getY(), pos.getZ(), 20);
            int mushroomHeight = RandomUtils.randomRange(Math.max(1, ySpace / 2), ySpace + 1) + 1;
            mushroomHeight = Math.max(1, mushroomHeight - 3);

            if (mushroomHeight < 4) {
                BlockPos capStart = createSmallTrunk(world, mushroomHeight, pos.getX(), pos.getY(), pos.getZ());

                //if (capStart != null)
                    createSmallCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
            } else if (mushroomHeight < 8) {
                BlockPos capStart = createNormalTrunk(world, mushroomHeight, pos.getX(), pos.getY(), pos.getZ());

                //if (capStart != null)
                    createNormalCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
            } else if (mushroomHeight < 12) {
                BlockPos capStart = createLongTrunk(world, mushroomHeight, pos.getX(), pos.getY(), pos.getZ());

                //if (capStart != null)
                    createLargeCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
            } else {
                BlockPos capStart = createThickTrunk(world, mushroomHeight, pos.getX(), pos.getY(), pos.getZ());

                //if (capStart != null)
                    createThickCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
            }
        }
    }

    private static BlockPos createSmallTrunk(LevelAccessor world, int height, double x, double y, double z){
        int trunkThickness = 1;
        int maxNumberOfBentSections = 1;

        int sections = RandomUtils.randomRange(1, maxNumberOfBentSections + 1);
        sections = sections < height / 2? sections : 1;

        return ShapeUtils.createTrunkStructure(world, new BlockPos(x, y, z), trunkThickness, height,  sections, Orientation.UP, (currentPos, currentWidth, wIndex, hIndex, lIndex) -> {
            world.setBlock(currentPos, block.defaultBlockState(), 3);
        });
    }

    private static BlockPos createNormalTrunk(LevelAccessor world, int height, double x, double y, double z){
        int trunkThickness = 1;
        int maxNumberOfBentSections = 2;

        int sections = RandomUtils.randomRange(1, maxNumberOfBentSections + 1);
        sections = sections < height / 2? sections : 1;

        return ShapeUtils.createTrunkStructure(world, new BlockPos(x, y, z), trunkThickness, height, sections, Orientation.UP, (currentPos, currentWidth, wIndex, hIndex, lIndex) -> {
            world.setBlock(currentPos, block.defaultBlockState(), 3);
        });
    }

    private static BlockPos createLongTrunk(LevelAccessor world, int height, double x, double y, double z){
        int trunkThickness = 1;
        int maxNumberOfBentSections = 3;

        int sections = RandomUtils.randomRange(1, maxNumberOfBentSections + 1);
        sections = sections < height / 2? sections : 1;

        return ShapeUtils.createTrunkStructure(world, new BlockPos(x, y, z), trunkThickness, height, sections, Orientation.UP,  (currentPos, currentWidth, wIndex, hIndex, lIndex) -> {
            world.setBlock(currentPos, block.defaultBlockState(), 3);
        });
    }

    private static BlockPos createThickTrunk(LevelAccessor world, int height, double x, double y, double z){
        int trunkThickness = 2;
        int maxNumberOfBentSections = 4;

        int sections = RandomUtils.randomRange(1, maxNumberOfBentSections + 1);
        sections = sections < height / 2? sections : 1;

        return ShapeUtils.createTrunkStructure(world, new BlockPos(x, y, z), trunkThickness, height, sections, Orientation.UP, (currentPos, currentWidth, wIndex, hIndex, lIndex) -> {
            world.setBlock(currentPos, block.defaultBlockState(), 3);
        });
    }

    private static void createSmallCap(LevelAccessor world, double x, double y, double z){
        float skirtChance = 1f;
        float doubleSkirtChance = 0.15f;

        int capHeight = RandomUtils.randomRange(1, 3);

        int width = 3;

        int widthDecreaseRate = 1;
        int heightIncreaseRate = RandomUtils.randomRange(1, 2);

        createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthDecreaseRate, heightIncreaseRate, width);
    }

    private static void createNormalCap(LevelAccessor world, double x, double y, double z){
        float skirtChance = 0.75f;
        float doubleSkirtChance = 0.5f;

        int capHeight = RandomUtils.randomRange(2, 5);

        // To center a 1 wide trunk, we need an odd numbered cap
        int width = capHeight % 2 == 1 ? capHeight : capHeight + 1;

        int widthDecreaseRate = 1;
        int heightIncreaseRate = 1;

        createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthDecreaseRate, heightIncreaseRate, width);
    }

    private static void createLargeCap(LevelAccessor world, double x, double y, double z){
        float skirtChance = 0.85f;
        float doubleSkirtChance = 0.75f;

        int capHeight = RandomUtils.randomRange(3, 6);

        // To center a 1 wide trunk, we need an odd numbered cap
        int width = capHeight % 2 == 1 ? capHeight : capHeight + 1;

        int widthDecreaseRate = RandomUtils.randomRange(1, 3);
        int heightIncreaseRate = RandomUtils.randomRange(1, 3);

        createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthDecreaseRate, heightIncreaseRate, width);
    }

    private static void createThickCap(LevelAccessor world, double x, double y, double z){
        float skirtChance = 0.85f;
        float doubleSkirtChance = 0.75f;

        int capHeight = RandomUtils.randomRange(3, 6);

        // To center a 2 wide trunk, we need an even numbered cap
        int width = capHeight % 2 == 0 ? capHeight : capHeight + 1;
        int widthDecreaseRate = RandomUtils.randomRange(1, 3);
        int heightIncreaseRate = RandomUtils.randomRange(1, 3);

        createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthDecreaseRate, heightIncreaseRate, width);
    }

    private static void createCap(LevelAccessor world, double x, double y, double z, float skirtChance, float doubleSkirtChance, int capHeight, int widthDecreaseRate, int heightIncreaseRate, int capWidth){
        if (Math.random() < skirtChance){

            ShapeUtils.drawSquarePerimeter(x, y - 1, z, capWidth, Orientation.UP, (pos, width, wIndex, hIndex, lIndex) -> {
                placeBlockWithCornerType(world, pos, wIndex, lIndex, width, CornerType.getValue(RandomUtils.randomRange(0, 3)));
            });

            if (Math.random() < doubleSkirtChance){
                ShapeUtils.drawSquarePerimeter(x, y - 1, z, capWidth, Orientation.UP, (pos, width, wIndex, hIndex, lIndex) -> {
                    placeBlockWithCornerType(world, pos, wIndex, lIndex, width, CornerType.getValue(RandomUtils.randomRange(0, 3)));
                });
            }

        }

        for (int yOffset = 0; yOffset < capHeight; yOffset += heightIncreaseRate){
            for (int i = 0; i < heightIncreaseRate; i++){
                ShapeUtils.drawSquare(x, y + yOffset + i, z, capWidth, Orientation.UP, (pos, width, wIndex, hIndex, lIndex) -> {
                    placeBlockWithCornerType(world, pos, wIndex, lIndex, width, CornerType.getValue(RandomUtils.randomRange(0, 3)));
                });
            }
            capWidth -= 2 * widthDecreaseRate;
        }
    }
    private static void createCapLayer(LevelAccessor world, double x, double y, double z, int capRadius, CornerType cornerType, boolean thickCap){
        int upperLimit = thickCap? capRadius + 1 : capRadius;

        for (int xOffset = -capRadius; xOffset <= upperLimit; xOffset++){
            for (int zOffset = -capRadius; zOffset <= upperLimit; zOffset++){

            }
        }
    }

    private static void placeBlockWithCornerType(LevelAccessor world, BlockPos pos, int wIndex, int lIndex, int capWidth, CornerType cornerType){
        boolean placeBlock = true;

        boolean isCorner =
                (wIndex == 0 && lIndex == 0) 	||
                (wIndex == 0 && lIndex == capWidth - 1) 	||
                (wIndex == capWidth - 1 && lIndex == 0) 	||
                (wIndex == capWidth - 1 && lIndex == capWidth - 1);

        if (capWidth > 2 && isCorner){
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
            world.setBlock(pos, CaviercavesModBlocks.GHOST_FUNGUS_BLOCK.get().defaultBlockState(), 3);
        }
    }
}
