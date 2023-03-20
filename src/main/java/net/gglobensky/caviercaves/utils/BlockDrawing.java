package net.gglobensky.caviercaves.utils;

import net.gglobensky.caviercaves.enums.Orientation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class BlockDrawing {
    //Adds width height length to an oriented position
    //This way you can add values like if it was on a regular flat plane then orient it any way
    public static BlockPos addRelativePosition(int wOffset, int lOffset, int hOffset, double x, double y, double z, Orientation orientation){
        switch(orientation) {
            case UP:
                return new BlockPos(x + wOffset, y + hOffset, z + lOffset);
            case DOWN:
                return new BlockPos(x + wOffset, y - hOffset, z + lOffset);
            case WEST:
                return new BlockPos(x + hOffset, y + wOffset, z + lOffset);
            case EAST:
                return new BlockPos(x - hOffset, y + wOffset, z + lOffset);
            case NORTH:
                return new BlockPos(x + wOffset, y + lOffset, z - hOffset);
            case SOUTH:
                return new BlockPos(x + wOffset, y + lOffset, z + hOffset);
            default:
                return new BlockPos(x, y, z);
        }
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
                    BlockPos pos = addRelativePosition(w, l, 0, centerX, centerY, centerZ, orientation);

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
                    BlockPos pos = addRelativePosition(w, l, 0, centerX, centerY, centerZ, orientation);

                    action.accept(pos);
                }
            }
        }
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
                case UP: yield Orientation.NORTH;
                case DOWN: yield Orientation.SOUTH;
                case WEST: yield Orientation.DOWN;
                case EAST: yield Orientation.UP;
                case NORTH: yield Orientation.EAST;
                case SOUTH: yield Orientation.WEST;
            };
            case EAST: yield switch (absoluteDirection){
                case UP: yield Orientation.SOUTH;
                case DOWN: yield Orientation.NORTH;
                case WEST: yield Orientation.UP;
                case EAST: yield Orientation.DOWN;
                case NORTH: yield Orientation.EAST;
                case SOUTH: yield Orientation.WEST;
            };
            case NORTH: yield switch (absoluteDirection){
                case UP: yield Orientation.NORTH;
                case DOWN: yield Orientation.SOUTH;
                case NORTH: yield Orientation.DOWN;
                case SOUTH: yield Orientation.UP;
                default: yield absoluteDirection;
            };
            case SOUTH: yield switch (absoluteDirection){
                case UP: yield Orientation.SOUTH;
                case DOWN: yield Orientation.NORTH;
                case NORTH: yield Orientation.UP;
                case SOUTH: yield Orientation.DOWN;
                default: yield absoluteDirection;
            };
        };
    }
}
