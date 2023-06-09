package net.gglobensky.caviercaves.utils;

import net.gglobensky.caviercaves.enums.Orientation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

import java.util.function.Consumer;


public class ShapeUtils {

    private static int[][] offsets = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
    @FunctionalInterface
    public interface OnBlockPosSet<BlockPos, Integer> {
        void accept(BlockPos pos, Integer width, Integer wIndex, Integer hIndex, Integer lIndex);
    }

    public static void drawCircle(double centerX, double centerY, double centerZ, int radius, Orientation orientation, Consumer<BlockPos> action) {
        double radiusSquared = Math.pow(radius, 2);
        for (int w = -radius; w <= radius; w++){
            for (int l = -radius; l <= radius; l++){
                double distanceSquared = Math.pow(w, 2) + Math.pow(l, 2);

                if (Math.abs(w) == radius && Math.abs(l) == radius){
                    continue;
                }

                if (distanceSquared <= radiusSquared) {
                    BlockPos pos = Utils.localPosition(w, 0, l, centerX, centerY, centerZ, orientation);

                    action.accept(pos);
                }
            }
        }
    }

    public static void drawCirclePerimeter(double centerX, double centerY, double centerZ, int radius, Orientation orientation, Consumer<BlockPos> action) {
        double radiusSquared = Math.pow(radius, 2);
        for (int w = -radius; w <= radius; w++){
            for (int l = -radius; l <= radius; l++){
                double distanceSquared = Math.pow(w, 2) + Math.pow(l, 2);

                if (Math.abs(w) == radius && Math.abs(l) == radius){
                    continue;
                }

                if (distanceSquared >= radiusSquared - 1) {
                    BlockPos pos = Utils.localPosition(w, 0, l, centerX, centerY, centerZ, orientation);

                    action.accept(pos);
                }
            }
        }
    }

    public static void drawSquare(double x, double y, double z, int width, Orientation orientation, OnBlockPosSet<BlockPos, Integer> action){
        // calculate the lower and higher bounds for the x and y directions
        int[] wBounds = Utils.divideByTwo(width);
        int[] lBounds = Utils.divideByTwo(width);
        int wIndex = 0;
        int lIndex = 0;


        // iterate over the x and z positions relative to the center position
        for (int wOffset = -wBounds[0]; wOffset < wBounds[1]; wOffset++) {
            for (int lOffset = -lBounds[0]; lOffset < lBounds[1]; lOffset++) {
                BlockPos pos = Utils.localPosition(wOffset, 0, lOffset, x, y, z, orientation);

                action.accept(pos, width, wIndex, 0, lIndex++);
            }
            wIndex++;
            lIndex = 0;
        }
    }

    public static void drawSquarePerimeter(double x, double y, double z, int width, Orientation orientation, OnBlockPosSet<BlockPos, Integer> action){
        // calculate the lower and higher bounds for the x and y directions
        int[] wBounds = Utils.divideByTwo(width);
        int[] lBounds = Utils.divideByTwo(width);
        int wIndex = 0;
        int lIndex = 0;


        // iterate over the x and z positions relative to the center position
        for (int wOffset = -wBounds[0]; wOffset < wBounds[1]; wOffset++) {
            for (int lOffset = -lBounds[0]; lOffset < lBounds[1]; lOffset++) {
                // Avoid blocks inside perimeter
                if (!(wOffset == -wBounds[0] || wOffset == wBounds[1] - 1 || lOffset == -lBounds[0] || lOffset == lBounds[1] - 1))
                    continue;

                BlockPos pos = Utils.localPosition(wOffset, 0, lOffset, x, y, z, orientation);

                action.accept(pos, width, wIndex, 0, lIndex++);
            }
            wIndex++;
            lIndex = 0;
        }
    }

    public static BlockPos createTrunkStructure(LevelAccessor world, BlockPos startPoint, int width, int height, int sections, Orientation localUp, OnBlockPosSet<BlockPos, Integer> action){
        int rowsPerSection = height / sections;
        int currentRowInSection = 0;

        int[] offsetDirection = offsets[RandomUtils.randomRange(0, offsets.length)];
        int[] currentOffset = { 0, 0 };

        BlockPos bottomCenter = startPoint;

        if (bottomCenter != null) {

            BlockPos currentCenter = null;
            int[] wBounds = Utils.divideByTwo(width);
            int[] lBounds = Utils.divideByTwo(width);
            int wIndex = 0;
            int lIndex = 0;

            boolean placeNewSectionSupport = false;

            for (int y = 0; y < height; y++) {
                wIndex = 0;
                lIndex = 0;
                do {
                    currentCenter = Utils.localPosition(currentOffset[0], y, currentOffset[1], bottomCenter.getX(), bottomCenter.getY(), bottomCenter.getZ(), localUp);
                    placeNewSectionSupport = false;

                    // iterate over the x and z positions relative to the center position
                    for (int wOffset = -wBounds[0]; wOffset < wBounds[1]; wOffset++) {
                        for (int lOffset = -lBounds[0]; lOffset < lBounds[1]; lOffset++) {
                            BlockPos pos = Utils.localPosition(wOffset, 0, lOffset, currentCenter.getX(), currentCenter.getY(), currentCenter.getZ(), localUp);

                            action.accept(pos, width, wIndex, y, lIndex++);
                        }
                        wIndex++;
                        lIndex = 0;
                    }

                    if (currentRowInSection++ >= rowsPerSection) {
                        currentOffset[0] += offsetDirection[0];
                        currentOffset[1] += offsetDirection[1];

                        currentRowInSection = 0;
                        placeNewSectionSupport = true;
                    }

                }while(placeNewSectionSupport);
            }

            return Utils.localPosition(currentOffset[0], height, currentOffset[1], bottomCenter.getX(), bottomCenter.getY(), bottomCenter.getZ(), localUp);
        }

        return null;
    }

    public static Orientation getRelativeOrientation(Orientation currentOrientation, Orientation absoluteDirection){
        return switch (currentOrientation) {
            case UP:
                yield absoluteDirection;
            case DOWN: yield switch (absoluteDirection){
                case UP: yield Orientation.DOWN;
                case DOWN: yield Orientation.UP;
                case WEST: yield Orientation.EAST;
                case EAST: yield Orientation.WEST;
                default: yield absoluteDirection;
            };
            case WEST: yield switch (absoluteDirection){
                case UP: yield Orientation.EAST;
                case DOWN: yield Orientation.WEST;
                case WEST: yield Orientation.NORTH;
                case EAST: yield Orientation.SOUTH;
                case NORTH: yield Orientation.UP;
                case SOUTH: yield Orientation.DOWN;
            };
            case EAST: yield switch (absoluteDirection){
                case UP: yield Orientation.WEST;
                case DOWN: yield Orientation.EAST;
                case WEST: yield Orientation.SOUTH;
                case EAST: yield Orientation.NORTH;
                case NORTH: yield Orientation.DOWN;
                case SOUTH: yield Orientation.UP;
            };
            case NORTH: yield switch (absoluteDirection){
                case UP: yield Orientation.SOUTH;
                case DOWN: yield Orientation.NORTH;
                case NORTH: yield Orientation.UP;
                case SOUTH: yield Orientation.DOWN;
                default: yield absoluteDirection;
            };
            case SOUTH: yield switch (absoluteDirection){
                case UP: yield Orientation.NORTH;
                case DOWN: yield Orientation.SOUTH;
                case NORTH: yield Orientation.DOWN;
                case SOUTH: yield Orientation.UP;
                default: yield absoluteDirection;
            };
        };
    }
}
