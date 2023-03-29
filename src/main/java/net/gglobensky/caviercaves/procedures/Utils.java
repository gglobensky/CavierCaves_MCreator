package net.gglobensky.caviercaves.procedures;

import net.gglobensky.caviercaves.enums.Orientation;
import net.gglobensky.caviercaves.featureManagers.CrystalManager;
import net.gglobensky.caviercaves.utils.BlockDrawing;
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
				if (hasPassedStoneBlock && isAirOrFluid(world, x, surfaceYLevel, z)) {
					//surfaceYLevel = surfaceYLevel - 1;
					return surfaceYLevel - 1;
				}

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag);
			}
		}
		
		return null;
	}

	// TODO: Could make blocktag a param array
	public static Double getCeilingYLevel(LevelAccessor world, double x, double y, double z){
		Double surfaceYLevel = null;
		boolean hasStartedInNonSolid = isAirOrFluid(world, x, y, z);
		boolean hasPassedStoneBlock = false;
		
		for (int j = 1; j < 50; j++) {
			if (hasStartedInNonSolid) {
				surfaceYLevel = y + j;
				if ((world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag)) {
					//surfaceYLevel = surfaceYLevel - 1;
					return surfaceYLevel;
				}
			} else {
				surfaceYLevel = y - j;
				if (hasPassedStoneBlock && isAirOrFluid(world, x, surfaceYLevel, z)) {
					return surfaceYLevel + 1;
				}

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(x, surfaceYLevel, z))).is(stoneTag);
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
	            surfaceZValue = z + j;
	            if ((world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag)) {
	                //surfaceZValue = surfaceZValue - 1;
	                return surfaceZValue;
	            }
	        } else {
	            surfaceZValue = z - j;
	            if (hasPassedStoneBlock && isAirOrFluid(world, x, y, surfaceZValue)) {
	                return surfaceZValue + 1;
	            }

				hasPassedStoneBlock = (world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag);
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
	            surfaceZValue = z - j;
	            if ((world.getBlockState(new BlockPos(x, y, surfaceZValue))).is(stoneTag)) {
	                surfaceZValue = surfaceZValue + 1;
	                return surfaceZValue;
	            }
	        } else {
	            surfaceZValue = z + j;
	            if (hasPassedStoneBlock && isAirOrFluid(world, x, y, surfaceZValue)) {
	                return surfaceZValue - 1;
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
	                //surfaceXValue = surfaceXValue + 1;
	                return surfaceXValue;
	            }
	        } else {
	            surfaceXValue = x + j;
	            if (hasPassedStoneBlock && isAirOrFluid(world, surfaceXValue, y, z)) {
	                return surfaceXValue - 1;
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
	                //surfaceXValue = surfaceXValue - 1;
	                return surfaceXValue;
	            }
	        } else {
	            surfaceXValue = x - j;
	            if (hasPassedStoneBlock && isAirOrFluid(world, surfaceXValue, y, z)) {
	                return surfaceXValue + 1;
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

	public static boolean areBlocksInPath(LevelAccessor world, BlockPos[] startPoints, Block[] blocks, int maxScan, Orientation localUp){
		for (BlockPos startPoint : startPoints){
			for (int localY = 0; localY < maxScan; localY++){
				BlockPos checkedPos = BlockDrawing.localPosition(0, localY, 0, startPoint.getX(), startPoint.getY(), startPoint.getZ(), localUp);
				for (Block block : blocks){
					if (world.getBlockState(checkedPos).getBlock() == block){
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean areBlocksInPath(LevelAccessor world, BlockPos[] startPoints, String[] tags, int maxScan, Orientation localUp){
		for (String tagName : tags){
			TagKey<Block> tag = BlockTags.create(new ResourceLocation(tagName));

			for (BlockPos startPoint : startPoints){
				for (int localY = 0; localY < maxScan; localY++){
					BlockPos checkedPos = BlockDrawing.localPosition(0, localY, 0, startPoint.getX(), startPoint.getY(), startPoint.getZ(), localUp);

					if (world.getBlockState(checkedPos).is(tag)){
						return true;
					}
				}
			}
		}
		return false;
	}

	public static int randomRange(int min, int max){
		if (min == max && max == 0)
			return 0;

		if (min == 0)
			return random.nextInt(max);

		if (min == max)
			return max;

		return min + random.nextInt(max - min);
	}

	public static int randomFrom(int[] array){
		int index = random.nextInt(array.length);

		return array[index];
	}

	public static boolean isUnderground(LevelAccessor world, double x, double y, double z) {
		return !world.canSeeSkyFromBelowWater(new BlockPos(x, y, z));
	}

	public static boolean isAirOrFluid(LevelAccessor world, double x, double y, double z) {
		BlockPos blockPos = new BlockPos(x, y, z);
		return world.isEmptyBlock(blockPos) || (world.getBlockState(blockPos)).getBlock() instanceof LiquidBlock;
	}

	public static boolean isAirOrFluid(LevelAccessor world, BlockPos blockPos) {
		return world.isEmptyBlock(blockPos) || (world.getBlockState(blockPos)).getBlock() instanceof LiquidBlock;
	}

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

	public static BlockPos getSnappedToSurface(LevelAccessor world, double x, double y, double z, Orientation surfaceDirection){
		Double surface = null;
		switch (surfaceDirection){
			case UP:
				surface = Utils.getCeilingYLevel(world, x, y, z);
				return surface != null? new BlockPos(x, surface, z) : null;
			case DOWN:
				surface = Utils.getFloorYLevel(world, x, y, z);
				return surface != null? new BlockPos(x, surface, z) : null;
			case WEST:
				surface = Utils.getWestWallXValue(world, x, y, z);
				return surface != null? new BlockPos(surface, y, z) : null;
			case EAST:
				surface = Utils.getEastWallXValue(world, x, y, z);
				return surface != null? new BlockPos(surface, y, z) : null;
			case NORTH:
				surface = Utils.getNorthWallZValue(world, x, y, z);
				return surface != null? new BlockPos(x, y, surface) : null;
			case SOUTH:
				surface = Utils.getSouthWallZValue(world, x, y, z);
				return surface != null? new BlockPos(x, y, surface) : null;
		}

		return null;
	}

	public static Orientation getOppositeDirection(double x, double y, double z, Orientation surfaceDirection){
		switch (surfaceDirection){
			case UP:
				return Orientation.DOWN;
			case DOWN:
				return Orientation.UP;
			case WEST:
				return Orientation.EAST;
			case EAST:
				return Orientation.WEST;
			case NORTH:
				return Orientation.SOUTH;
			case SOUTH:
				return Orientation.NORTH;
		}

		return null;
	}
}
