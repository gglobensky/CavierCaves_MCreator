package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class IsHangedProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return !((world.getBlockState(new BlockPos(x, y + 1, z))).getBlock() instanceof LiquidBlock || world.isEmptyBlock(new BlockPos(x, y + 1, z)));
	}
}
