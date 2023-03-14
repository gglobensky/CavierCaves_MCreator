package net.mcreator.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CanGhostFungusGrowProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return IsUndergroundProcedure.execute(world, x, y, z) && world.getMaxLocalRawBrightness(new BlockPos(x, y, z)) <= 4 && IsNotInLushCaveProcedure.execute(world, x, y, z);
	}
}
