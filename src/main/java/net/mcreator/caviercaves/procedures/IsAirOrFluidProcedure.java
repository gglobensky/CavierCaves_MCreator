package net.mcreator.caviercaves.procedures;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class IsAirOrFluidProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return world.isEmptyBlock(new BlockPos(x, y, z)) || (world.getBlockState(new BlockPos(x, y, z))).getBlock() instanceof LiquidBlock;
	}
}
