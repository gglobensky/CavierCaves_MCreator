package net.gglobensky.caviercaves.procedures;

import net.gglobensky.caviercaves.enums.Orientation;
import net.gglobensky.caviercaves.featureManagers.CrystalManager;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.LiquidBlock;

import java.util.Random;


public class Utils {
    private static TagKey<Block> stoneTag = BlockTags.create(new ResourceLocation("forge:stone"));
	private static final Random random = new Random();

	public static Double getFloorYLevel(LevelAccessor world, double x, double y, double z){
		Double surfaceYLevel = null;
		boolean hasStartedInNonSolid = isAirOrFluid(world, x, y, z);
		boolean hasPassedStoneBlock = false;
		
		for (int j = 1; j < 50; j++) {
			if (hasStartedInNonSolid) {
				surfaceYLevel = y - j;
				if ((world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag)) {
					return surfaceYLevel;
				}
			} else {
				surfaceYLevel = y + j;
				if (hasPassedStoneBlock && isAirOrFluid(world, x, surfaceYLevel, z) && (world.getBlockState(new BlockPos(x, surfaceYLevel - 1, z))).is(stoneTag)) {
					surfaceYLevel = surfaceYLevel - 1;
					return surfaceYLevel;
				}

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag);
			}
		}
		
		return null;
	}

	public static Double getCeilingYLevel(LevelAccessor world, double x, double y, double z){
		Double surfaceYLevel = null;
		boolean hasStartedInNonSolid = isAirOrFluid(world, x, y, z);
		boolean hasPassedStoneBlock = false;
		
		for (int j = 1; j < 50; j++) {
			if (hasStartedInNonSolid) {
				surfaceYLevel = y + j;
				if ((world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag)) {
					surfaceYLevel = surfaceYLevel - 1;
					return surfaceYLevel;
				}
			} else {
				surfaceYLevel = y - j;
				if (hasPassedStoneBlock && isAirOrFluid(world, x, surfaceYLevel, z) && (world.getBlockState(new BlockPos(x, surfaceYLevel + 1, z))).is(stoneTag)) {
					return surfaceYLevel;
				}

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag);
			}
		}
		
		return null;
	}

	public static Double getNorthWallZValue(LevelAccessor world, double x, double y, double z){
	    Double surfaceZValue = null;
	    boolean hasStartedInNonSolid = isAirOrFluid(world, x, y, z);
		boolean hasPassedStoneBlock = false;
	    
	    for (int j = 1; j < 50; j++) {
	        if (hasStartedInNonSolid) {
	            surfaceZValue = z + j;
	            if ((world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag)) {
	                surfaceZValue = surfaceZValue - 1;
	                return surfaceZValue;
	            }
	        } else {
	            surfaceZValue = z - j;
	            if (hasPassedStoneBlock && isAirOrFluid(world, x, y, surfaceZValue) && (world.getBlockState(new BlockPos(x, y, surfaceZValue + 1))).is(stoneTag)) {
	                return surfaceZValue;
	            }

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag);
	        }
	    }
	    
	    return null;
	}

	public static Double getSouthWallZValue(LevelAccessor world, double x, double y, double z){
	    Double surfaceZValue = null;
	    boolean hasStartedInNonSolid = isAirOrFluid(world, x, y, z);
		boolean hasPassedStoneBlock = false;
	    
	    for (int j = 1; j < 50; j++) {
	        if (hasStartedInNonSolid) {
	            surfaceZValue = z - j;
	            if (hasPassedStoneBlock && (world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag)) {
	                surfaceZValue = surfaceZValue + 1;
	                return surfaceZValue;
	            }
	        } else {
	            surfaceZValue = z + j;
	            if (isAirOrFluid(world, x, y, surfaceZValue) && (world.getBlockState(new BlockPos(x, y, surfaceZValue - 1))).is(stoneTag)) {
	                return surfaceZValue;
	            }

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag);
	        }
	    }
	    
	    return null;
	}

	public static Double getWestWallXValue(LevelAccessor world, double x, double y, double z){
	    Double surfaceXValue = null;
	    boolean hasStartedInNonSolid = isAirOrFluid(world, x, y, z);
		boolean hasPassedStoneBlock = false;
	    
	    for (int j = 1; j < 50; j++) {
	        if (hasStartedInNonSolid) {
	            surfaceXValue = x - j;
	            if ((world.getBlockState(new BlockPos(surfaceXValue, y, z))).is(stoneTag)) {
	                surfaceXValue = surfaceXValue + 1;
	                return surfaceXValue;
	            }
	        } else {
	            surfaceXValue = x + j;
	            if (hasPassedStoneBlock && isAirOrFluid(world, surfaceXValue, y, z) && (world.getBlockState(new BlockPos(surfaceXValue - 1, y, z))).is(stoneTag)) {
	                return surfaceXValue;
	            }

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(surfaceXValue, y, z))).is(stoneTag);
	        }
	    }
	    
	    return null;
	}

	public static Double getEastWallXValue(LevelAccessor world, double x, double y, double z){
	    Double surfaceXValue = null;
	    boolean hasStartedInNonSolid = isAirOrFluid(world, x, y, z);
		boolean hasPassedStoneBlock = false;
	    
	    for (int j = 1; j < 50; j++) {
	        if (hasStartedInNonSolid) {
	            surfaceXValue = x + j;
	            if ((world.getBlockState(new BlockPos(surfaceXValue, y, z))).is(stoneTag)) {
	                surfaceXValue = surfaceXValue - 1;
	                return surfaceXValue;
	            }
	        } else {
	            surfaceXValue = x - j;
	            if (hasPassedStoneBlock && isAirOrFluid(world, surfaceXValue, y, z) & (world.getBlockState(new BlockPos(surfaceXValue + 1, y, z))).is(stoneTag)) {
	                return surfaceXValue;
	            }

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(surfaceXValue, y, z))).is(stoneTag);
	        }
	    }
	    
	    return null;
	}

	public static int getYSpace(LevelAccessor world, double x, double y, double z, int maxScan){
		for (int i = 1; i < maxScan; i++){
			if (!isAirOrFluid(world, x, y + i, z)){
				return i;
			}
		}

		return maxScan;
	}

	public static int randomRange(int min, int max){
		if (min == max)
			return max;

		return min + random.nextInt(max - min);
	}

	public static boolean isUnderground(LevelAccessor world, double x, double y, double z) {
		return !world.canSeeSkyFromBelowWater(new BlockPos(x, y, z));
	}

	public static boolean isAirOrFluid(LevelAccessor world, double x, double y, double z) {
		return world.isEmptyBlock(new BlockPos(x, y, z)) || (world.getBlockState(new BlockPos(x, y, z))).getBlock() instanceof LiquidBlock;
	}

}
