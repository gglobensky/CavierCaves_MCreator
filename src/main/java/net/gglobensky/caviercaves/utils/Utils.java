package net.gglobensky.caviercaves.utils;

import net.gglobensky.caviercaves.enums.Orientation;
import net.minecraft.core.BlockPos;

public class Utils {
    public static int[] divideByTwo(int value) {
        int[] result = new int[2];
        int q = (value) / 2; // the quotient of the division
        int r = (value) % 2; // the remainder of the division
        int x = q; // one of the results
        int y = q + r; // the other result

        result[0] = x;
        result[1] = y;
        return result;
    }

    //Adds width height length to an oriented position
    //This way you can add values like if it was on a regular flat plane then orient it any way
    public static BlockPos localPosition(int localX, int localY, int localZ, double x, double y, double z, Orientation localUpDirection){
        switch(localUpDirection) {
            case UP:
                return new BlockPos(x + localX, y + localY, z + localZ);
            case DOWN:
                return new BlockPos(x + localX, y - localY, z + localZ);
            case WEST:
                return new BlockPos(x - localY, y + localX, z + localZ);
            case EAST:
                return new BlockPos(x + localY, y + localX, z + localZ);
            case NORTH:
                return new BlockPos(x + localX, y + localZ, z - localY);
            case SOUTH:
                return new BlockPos(x + localX, y + localZ, z + localY);
        }
        return null;
    }

}
