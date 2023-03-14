package net.mcreator.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class IsNotInLushCaveProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return !world.getBiome(new BlockPos(x, y, z)).is(new ResourceLocation("lush_caves"));
	}
}
