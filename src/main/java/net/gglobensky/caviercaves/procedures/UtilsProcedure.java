package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;

import net.minecraft.resources.ResourceLocation;



public class UtilsProcedure {
	public static Double getFloorYLevel(LevelAccessor world, double x, double y, double z){
		Double surfaceYLevel = null;
		boolean hasStartedInNonSolid = IsAirOrFluidProcedure.execute(world, x, y, z);
		
		for (int j = 1; j < 50; j++) {
			if (hasStartedInNonSolid) {
				surfaceYLevel = y - j;
				if ((world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(BlockTags.create(new ResourceLocation("forge:stone")))) {
					return surfaceYLevel;
				}
			} else { // TODO: This would allow something a non stone surface to be returned
				surfaceYLevel = y + j;
				if (IsAirOrFluidProcedure.execute(world, x, surfaceYLevel, z)) {
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
				if ((world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(BlockTags.create(new ResourceLocation("forge:stone")))) {
					surfaceYLevel = surfaceYLevel - 1;
					return surfaceYLevel;
				}
			} else {
				surfaceYLevel = y - j;
				if (IsAirOrFluidProcedure.execute(world, x, surfaceYLevel, z)) {
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
	            if ((world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(BlockTags.create(new ResourceLocation("forge:stone")))) {
	                surfaceZValue = surfaceZValue - 1;
	                return surfaceZValue;
	            }
	        } else {
	            surfaceZValue = z - j;
	            if (IsAirOrFluidProcedure.execute(world, x, y, surfaceZValue)) {
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
	            if ((world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(BlockTags.create(new ResourceLocation("forge:stone")))) {
	                surfaceZValue = surfaceZValue + 1;
	                return surfaceZValue;
	            }
	        } else {
	            surfaceZValue = z + j;
	            if (IsAirOrFluidProcedure.execute(world, x, y, surfaceZValue)) {
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
	            if ((world.getBlockState(new BlockPos(surfaceXValue, y, z))).is(BlockTags.create(new ResourceLocation("forge:stone")))) {
	                surfaceXValue = surfaceXValue + 1;
	                return surfaceXValue;
	            }
	        } else {
	            surfaceXValue = x + j;
	            if (IsAirOrFluidProcedure.execute(world, surfaceXValue, y, z)) {
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
	            if ((world.getBlockState(new BlockPos(surfaceXValue, y, z))).is(BlockTags.create(new ResourceLocation("forge:stone")))) {
	                surfaceXValue = surfaceXValue - 1;
	                return surfaceXValue;
	            }
	        } else {
	            surfaceXValue = x - j;
	            if (IsAirOrFluidProcedure.execute(world, surfaceXValue, y, z)) {
	                return surfaceXValue;
	            }
	        }
	    }
	    
	    return null;
	}
}
