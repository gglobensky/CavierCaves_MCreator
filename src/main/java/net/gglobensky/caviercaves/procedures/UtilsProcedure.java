package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.ReorganizePoi;
import net.minecraft.tags.TagKey;



public class UtilsProcedure {
    private static TagKey<Block> stoneTag = BlockTags.create(new ResourceLocation("forge:stone"));

	public static Double getFloorYLevel(LevelAccessor world, double x, double y, double z){
		Double surfaceYLevel = null;
		boolean hasStartedInNonSolid = IsAirOrFluidProcedure.execute(world, x, y, z);
		
		for (int j = 1; j < 50; j++) {
			if (hasStartedInNonSolid) {
				surfaceYLevel = y - j;
				if ((world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag)) {
					return surfaceYLevel;
				}
			} else {
				surfaceYLevel = y + j;
				if (IsAirOrFluidProcedure.execute(world, x, surfaceYLevel, z) && (world.getBlockState(new BlockPos(x, surfaceYLevel - 1, z))).is(stoneTag)) {
					surfaceYLevel = surfaceYLevel - 1;
					return surfaceYLevel;
				}
			}
		}
		
		return null;
	}

	public static Double getCeilingYLevel(LevelAccessor world, double x, double y, double z){
		Double surfaceYLevel = null;
		boolean hasStartedInNonSolid = IsAirOrFluidProcedure.execute(world, x, y, z);
		
		for (int j = 1; j < 50; j++) {
			if (hasStartedInNonSolid) {
				surfaceYLevel = y + j;
				if ((world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag)) {
					surfaceYLevel = surfaceYLevel - 1;
					return surfaceYLevel;
				}
			} else {
				surfaceYLevel = y - j;
				if (IsAirOrFluidProcedure.execute(world, x, surfaceYLevel, z) && (world.getBlockState(new BlockPos(x, surfaceYLevel + 1, z))).is(stoneTag)) {
					return surfaceYLevel;
				}
			}
		}
		
		return null;
	}

	public static Double getNorthWallZValue(LevelAccessor world, double x, double y, double z){
	    Double surfaceZValue = null;
	    boolean hasStartedInNonSolid = IsAirOrFluidProcedure.execute(world, x, y, z);
	    
	    for (int j = 1; j < 50; j++) {
	        if (hasStartedInNonSolid) {
	            surfaceZValue = z + j;
	            if ((world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag)) {
	                surfaceZValue = surfaceZValue - 1;
	                return surfaceZValue;
	            }
	        } else {
	            surfaceZValue = z - j;
	            if (IsAirOrFluidProcedure.execute(world, x, y, surfaceZValue) && (world.getBlockState(new BlockPos(x, y, surfaceZValue + 1))).is(stoneTag)) {
	                return surfaceZValue;
	            }
	        }
	    }
	    
	    return null;
	}

	public static Double getSouthWallZValue(LevelAccessor world, double x, double y, double z){
	    Double surfaceZValue = null;
	    boolean hasStartedInNonSolid = IsAirOrFluidProcedure.execute(world, x, y, z);
	    
	    for (int j = 1; j < 50; j++) {
	        if (hasStartedInNonSolid) {
	            surfaceZValue = z - j;
	            if ((world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag)) {
	                surfaceZValue = surfaceZValue + 1;
	                return surfaceZValue;
	            }
	        } else {
	            surfaceZValue = z + j;
	            if (IsAirOrFluidProcedure.execute(world, x, y, surfaceZValue) && (world.getBlockState(new BlockPos(x, y, surfaceZValue - 1))).is(stoneTag)) {
	                return surfaceZValue;
	            }
	        }
	    }
	    
	    return null;
	}

	public static Double getWestWallZValue(LevelAccessor world, double x, double y, double z){
	    Double surfaceXValue = null;
	    boolean hasStartedInNonSolid = IsAirOrFluidProcedure.execute(world, x, y, z);
	    
	    for (int j = 1; j < 50; j++) {
	        if (hasStartedInNonSolid) {
	            surfaceXValue = x - j;
	            if ((world.getBlockState(new BlockPos(surfaceXValue, y, z))).is(stoneTag)) {
	                surfaceXValue = surfaceXValue + 1;
	                return surfaceXValue;
	            }
	        } else {
	            surfaceXValue = x + j;
	            if (IsAirOrFluidProcedure.execute(world, surfaceXValue, y, z) && (world.getBlockState(new BlockPos(surfaceXValue - 1, y, z))).is(stoneTag)) {
	                return surfaceXValue;
	            }
	        }
	    }
	    
	    return null;
	}

	public static Double getEastWallZValue(LevelAccessor world, double x, double y, double z){
	    Double surfaceXValue = null;
	    boolean hasStartedInNonSolid = IsAirOrFluidProcedure.execute(world, x, y, z);
	    
	    for (int j = 1; j < 50; j++) {
	        if (hasStartedInNonSolid) {
	            surfaceXValue = x + j;
	            if ((world.getBlockState(new BlockPos(surfaceXValue, y, z))).is(stoneTag)) {
	                surfaceXValue = surfaceXValue - 1;
	                return surfaceXValue;
	            }
	        } else {
	            surfaceXValue = x - j;
	            if (IsAirOrFluidProcedure.execute(world, surfaceXValue, y, z) & (world.getBlockState(new BlockPos(surfaceXValue + 1, y, z))).is(stoneTag)) {
	                return surfaceXValue;
	            }
	        }
	    }
	    
	    return null;
	}

	public static int getYSpace(LevelAccessor world, double x, double y, double z, int maxScan){
		for (int i = 1; i < maxScan; i++){
			if (!IsAirOrFluidProcedure.execute(world, x, y + i, z)){
				return i;
			}
		}

		return maxScan;
		
	}
}
