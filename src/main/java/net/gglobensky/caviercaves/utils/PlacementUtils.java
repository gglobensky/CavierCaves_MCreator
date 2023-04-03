package net.gglobensky.caviercaves.utils;

import net.gglobensky.caviercaves.enums.Orientation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;


public class PlacementUtils {
    private static TagKey<Block> stoneTag = BlockTags.create(new ResourceLocation("forge:stone"));
	private static final Random random = new Random();
	private static final int surfaceHorizontalScanLength = 4;
	private static final int surfaceVerticalScanLength = 36;

	public static Double getFloorYLevel(LevelAccessor world, double x, double y, double z){
		Double surfaceYLevel = null;
		boolean hasStartedInNonSolid = isAirOrFluid(world, x, y, z);
		boolean hasPassedStoneBlock = false;
		
		for (int j = 1; j < surfaceVerticalScanLength; j++) {
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
		
		for (int j = 1; j < surfaceVerticalScanLength; j++) {
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
	    
	    for (int j = 1; j < surfaceHorizontalScanLength; j++) {
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
	    
	    for (int j = 1; j < surfaceHorizontalScanLength; j++) {
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
	    
	    for (int j = 1; j < surfaceHorizontalScanLength; j++) {
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
	    
	    for (int j = 1; j < surfaceHorizontalScanLength; j++) {
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

	public static BlockPos getSurfacePosition(LevelAccessor world, double x, double y, double z, int searchRange, Orientation localUp){
		int startingValue = RandomUtils.randomRange(-searchRange, searchRange);
		int currentValue = startingValue;
		int stepLength = startingValue < 0 ? 3 : -3;

		BlockPos result = null;
		boolean lCheck = false;
		boolean checkedFullRange = false;

		while (result == null) {
			if (lCheck){
				result = tryGetSurface(world, x, y, z + currentValue, localUp);
			}
			else{
				result = tryGetSurface(world, x + currentValue, y, z, localUp);
			}

			currentValue += stepLength;

			if (currentValue < -searchRange){
				if (checkedFullRange){
					if (lCheck){
						break;
					}
					lCheck = true;
					checkedFullRange = false;
				}
				currentValue = searchRange;
				checkedFullRange = true;
			}
			else if (currentValue > searchRange){
				if (checkedFullRange){
					if (lCheck){
						break;
					}
					lCheck = true;
					checkedFullRange = false;
				}
				currentValue = -searchRange;
				checkedFullRange = true;
			}
		}

		return result;
	}

	public static BlockPos tryGetSurface(LevelAccessor world, double x, double y, double z, Orientation localUp){
		Double surfaceLevel = null;

		switch (localUp){
			case UP:
				surfaceLevel = getFloorYLevel(world, x, y, z);
				return surfaceLevel != null ? new BlockPos(x, surfaceLevel, z) : null;
			case DOWN:
				surfaceLevel = getCeilingYLevel(world, x, y, z);
				return surfaceLevel != null ? new BlockPos(x, surfaceLevel, z) : null;
			case WEST:
				surfaceLevel = getEastWallXValue(world, x, y, z);
				return surfaceLevel != null ? new BlockPos(surfaceLevel, y, z) : null;
			case EAST:
				surfaceLevel = getWestWallXValue(world, x, y, z);
				return surfaceLevel != null ? new BlockPos(surfaceLevel, y, z) : null;
			case NORTH:
				surfaceLevel = getSouthWallZValue(world, x, y, z);
				return surfaceLevel != null ? new BlockPos(x, y, surfaceLevel) : null;
			case SOUTH:
				surfaceLevel = getNorthWallZValue(world, x, y, z);
				return surfaceLevel != null ? new BlockPos(x, y, surfaceLevel) : null;
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
				BlockPos checkedPos = Utils.localPosition(0, localY, 0, startPoint.getX(), startPoint.getY(), startPoint.getZ(), localUp);
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
					BlockPos checkedPos = Utils.localPosition(0, localY, 0, startPoint.getX(), startPoint.getY(), startPoint.getZ(), localUp);

					if (world.getBlockState(checkedPos).is(tag)){
						return true;
					}
				}
			}
		}
		return false;
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
	public static final int MAX_TRIES = 10;

	public static BlockPos findRandomCaveAir(LevelAccessor world, int x, int z, int lowBound, int highBound) {
		for (int i = 0; i < MAX_TRIES; i++) {
			int y = RandomUtils.randomRange(lowBound, highBound);
			BlockPos pos = new BlockPos(x, y, z);
			BlockState state = world.getBlockState(pos);
			if (state.getMaterial().isReplaceable()) {
				return pos;
			}
		}
		return null;
	}
}
