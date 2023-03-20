package net.gglobensky.caviercaves.featureManagers;

import com.mojang.math.Vector3d;
import net.gglobensky.caviercaves.enums.Orientation;
import net.gglobensky.caviercaves.init.CaviercavesModBlocks;
import net.gglobensky.caviercaves.procedures.Utils;
import net.gglobensky.caviercaves.utils.BlockDrawing;
import net.gglobensky.caviercaves.utils.Vector3;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class GiantPurpuraCapManager {

    private static Orientation[] orientations = { Orientation.NORTH, Orientation.SOUTH, Orientation.EAST, Orientation.WEST };
    public static void createHorn(LevelAccessor world, double x, double y, double z, Orientation direction){
        int hornStartingRadius = 4;
        int maxHornLength = 8;

        int minShrinkRate = 1;
        int maxShrinkRate = 1;
        float shrinkChance = 1;

        int minCurvatureRate = 1;
        int maxCurvatureRate = 3;
        float curvatureChance = 1;
        float curvatureRandomness = 0;

        Orientation curvatureDirection = null;

        int currentRadius = hornStartingRadius;
        int currentHornLength = 0;

        while (currentRadius > 0 && currentHornLength++ < maxHornLength){
            BlockDrawing.drawCircle(x, y, z, currentRadius, direction, pos -> {
                world.setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Blocks.GLOWSTONE.defaultBlockState(), 3);
            });

            if (Math.random() < shrinkChance)
                currentRadius -= Utils.randomRange(minShrinkRate, maxShrinkRate);

            if (Math.random() < curvatureChance){
                if (curvatureDirection == null || Math.random() < curvatureRandomness){
                    curvatureDirection = BlockDrawing.getRelativeOrientation(direction, orientations[Utils.randomRange(0, 4)]);

                    Vector3d curvatureOffset = Vector3.orientations.get(curvatureDirection);

                    int curvatureRate = Utils.randomRange(minCurvatureRate, maxCurvatureRate);

                    x += curvatureOffset.x * curvatureRate;
                    y += curvatureOffset.y * curvatureRate;
                    z += curvatureOffset.z * curvatureRate;
                }
            }

            Orientation growthDirection = BlockDrawing.getRelativeOrientation(direction, Orientation.UP);
            Vector3d growthOffset = Vector3.orientations.get(growthDirection);

            x += growthOffset.x;
            y += growthOffset.y;
            z += growthOffset.z;
        }
    }
}
