package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;
import com.google.common.net.MediaType;

public class CreateGiantGhostFungusProcedure {
	private static int[][] orientations = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
	private static float normalTrunkBendingThreshold = 0.5f;
	private static float longTrunkBendingThreshold = 0.25f;
	
	enum CornerType {
	  NONE,
	  SIMPLE,
	  RANDOM;
	  
		 public static CornerType getValue(int value) {
		    switch (value) {
		        case 0:
		            return NONE;
		        case 1:
		            return SIMPLE;
		        case 2:
		            return RANDOM;
		        default:
		            throw new IllegalArgumentException("int value: " + value + " not related to enum value");
		    }
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		int xOffsetRange = 16;
		int zOffsetRange = 16;
		int xOffset = 0;
		int zOffset = 0;

		Double surfaceYLevel = 0.00;
		int MushroomQty = RandomRange(12, 32);

		for (int i = 0; i < (int) (MushroomQty); i++) {
			xOffset = RandomRange((int) (0 - xOffsetRange), (int) xOffsetRange);
			zOffset = RandomRange((int) (0 - zOffsetRange), (int) zOffsetRange);

			surfaceYLevel = UtilsProcedure.getFloorYLevel(world, x + xOffset, y - 1, z + zOffset);
			int ySpace = UtilsProcedure.getYSpace(world, x, y, z, 20);
			int mushroomHeight = RandomRange(0, ySpace);

			if (surfaceYLevel != null && world.getBiome(new BlockPos(x + xOffset, surfaceYLevel, z + zOffset)).is(new ResourceLocation("caviercaves:spectral_caverns"))) {
				if (mushroomHeight < 4){
					BlockPos capStart = createSmallTrunk(world, x + xOffset, surfaceYLevel, z + zOffset);
					createSmallCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
				}
				else if (mushroomHeight < 8){
					BlockPos capStart = createNormalTrunk(world, x + xOffset, surfaceYLevel, z + zOffset);
					createNormalCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
				}
				else if (mushroomHeight < 12){
					BlockPos capStart = createLongTrunk(world, x + xOffset, surfaceYLevel, z + zOffset);
					createLargeCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
				}
				else{
					//TODO:Make huge mushroom
					BlockPos capStart = createLongTrunk(world, x + xOffset, surfaceYLevel, z + zOffset);
					createLargeCap(world, capStart.getX(), capStart.getY(), capStart.getZ());
				}
			}
			
		}
	}

	public static BlockPos createSmallTrunk(LevelAccessor world, double x, double y, double z){
		int minTrunkHeight = 2;
		int maxTrunkHeight = 6;

		int trunkHeight = RandomRange(minTrunkHeight, maxTrunkHeight);
		for (int i = 0; i < trunkHeight; i++){
			world.setBlock(new BlockPos(x, y + i, z), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);
		}

		return new BlockPos(x, y + trunkHeight, z);
	}

	public static BlockPos createNormalTrunk(LevelAccessor world, double x, double y, double z){
		int minTrunkHeight = 4;
		int maxTrunkHeight = 8;

		int trunkHeight = RandomRange(minTrunkHeight, maxTrunkHeight);
		int orientationIndex = (int) (Math.random() * orientations.length);

		if (Math.random() >= normalTrunkBendingThreshold){
			int snappingPoint = (int) (trunkHeight / 2f);

			for (int i = 0; i < trunkHeight; i++){
				if (i < snappingPoint){
					world.setBlock(new BlockPos(x, y + i, z), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);
				}
				else{
					if (i == snappingPoint){
						world.setBlock(new BlockPos(x + orientations[orientationIndex][0], y + i - 1, z + orientations[orientationIndex][1]), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);		
					}
					world.setBlock(new BlockPos(x + orientations[orientationIndex][0], y + i, z + orientations[orientationIndex][1]), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);
				}
			}
			
			return new BlockPos(x + orientations[orientationIndex][0], y + trunkHeight, z + orientations[orientationIndex][1]);
		}
		else{
			for (int i = 0; i < trunkHeight; i++){
				world.setBlock(new BlockPos(x, y + i, z), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);
			}
			
			return new BlockPos(x, y + trunkHeight, z);
		}

	}

	public static BlockPos createLongTrunk(LevelAccessor world, double x, double y, double z){
		int minTrunkHeight = 8;
		int maxTrunkHeight = 12;

		int trunkHeight = minTrunkHeight + (int) (Math.random() * (maxTrunkHeight - minTrunkHeight));
		int orientationIndex = (int) (Math.random() * orientations.length);
		int[] currentOrientation = {0, 0};

		if (Math.random() >= longTrunkBendingThreshold){
			int trunkSections = (int) (Math.random() * 3) + 1;
			int snappingInterval = (int) (trunkHeight / trunkSections);
			int intervalCounter = 0;

			for (int i = 0; i < trunkHeight; i++){
				world.setBlock(new BlockPos(x + currentOrientation[0], y + i, z + currentOrientation[1]), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);

				if (intervalCounter++ >= snappingInterval){			
					intervalCounter = 0;
					currentOrientation[0] += orientations[orientationIndex][0];
					currentOrientation[1] += orientations[orientationIndex][1];
					world.setBlock(new BlockPos(x + orientations[orientationIndex][0], y + i, z + orientations[orientationIndex][1]), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);		
				}
			}
		}
		else{
			for (int i = 0; i < trunkHeight; i++){
				world.setBlock(new BlockPos(x, y + i, z), CaviercavesModBlocks.GHOST_FUNGUS_STEM.get().defaultBlockState(), 3);
			}
		}

		return new BlockPos(x + currentOrientation[0], y + trunkHeight, z + currentOrientation[1]);
	}

	private static void createSmallCap(LevelAccessor world, double x, double y, double z){	
		world.setBlock(new BlockPos(x, y, z), CaviercavesModBlocks.GHOST_FUNGUS_BLOCK.get().defaultBlockState(), 3);	

		for (int i = 0; i < 4; i++){
			world.setBlock(new BlockPos(x + orientations[i][0], y - 1, z + orientations[i][1]), CaviercavesModBlocks.GHOST_FUNGUS_BLOCK.get().defaultBlockState(), 3);
		}
	}

	private static void createNormalCap(LevelAccessor world, double x, double y, double z){
		float skirtChance = 0.75f;
		float doubleSkirtChance = 0.5f;
		
		int capHeight = RandomRange(2, 5);
		int capRadius = capHeight - 1;
		int decreaseRate = 1;
		int heightIncreaseRate = 1;

		createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, decreaseRate, heightIncreaseRate, capRadius);
	}

	private static void createLargeCap(LevelAccessor world, double x, double y, double z){
		float skirtChance = 0.85f;
		float doubleSkirtChance = 0.75f;
		
		int capHeight = RandomRange(3, 6);
		int capRadius = capHeight - 1;
		int widthdecreaseRate = RandomRange(1, 3);
		int heightIncreaseRate = RandomRange(1, 3);

		createCap(world, x, y, z, skirtChance, doubleSkirtChance, capHeight, widthdecreaseRate, heightIncreaseRate, capRadius);
	}

	private static void createCap(LevelAccessor world, double x, double y, double z, float skirtChance, float doubleSkirtChance, int capHeight, int widthdecreaseRate, int heightIncreaseRate, int capRadius){
		if (Math.random() < skirtChance){
			createCapSkirt(world, x, y - 1, z, capRadius, CornerType.getValue(RandomRange(0, 3)));		
			
			if (Math.random() < doubleSkirtChance){
				createCapSkirt(world, x, y - 2, z, capRadius, CornerType.getValue(RandomRange(0, 3)));
			}
		}
		
		for (int yOffset = 0; yOffset < capHeight; yOffset += heightIncreaseRate){		
			for (int i = 0; i < heightIncreaseRate; i++){
				createCapLayer(world, x, y + yOffset + i, z, capRadius, CornerType.getValue(RandomRange(0, 3)));
			}
			capRadius -= widthdecreaseRate;
		}
		
		
	}

	private static void createCapLayer(LevelAccessor world, double x, double y, double z, int capRadius, CornerType cornerType){
		for (int xOffset = -capRadius; xOffset <= capRadius; xOffset++){
			for (int zOffset = -capRadius; zOffset <= capRadius; zOffset++){
				boolean placeBlock = true;
				
				if (Math.abs(xOffset) == capRadius && Math.abs(zOffset) == capRadius){
					if (cornerType == CornerType.NONE){
						placeBlock = false;
					}
					else if(cornerType == CornerType.RANDOM){
						if (Math.random() < 0.5f){
							placeBlock = false;	
						}
					}
				}

				if (placeBlock){
					world.setBlock(new BlockPos(x + xOffset, y, z + zOffset), CaviercavesModBlocks.GHOST_FUNGUS_BLOCK.get().defaultBlockState(), 3);	
				}
			}
		}
	}

	private static void createCapSkirt(LevelAccessor world, double x, double y, double z, int capRadius, CornerType cornerType){
		
		for (int xOffset = -capRadius; xOffset <= capRadius; xOffset++){
			for (int zOffset = -capRadius; zOffset <= capRadius; zOffset++){
				boolean placeBlock = true;
				
				if (Math.abs(xOffset) == capRadius && Math.abs(zOffset) == capRadius){
					if (cornerType == CornerType.NONE){
						placeBlock = false;
					}
					else if(cornerType == CornerType.RANDOM){
						if (Math.random() < 0.5f){
							placeBlock = false;	
						}
					}
				}

				if (Math.abs(xOffset) == capRadius || Math.abs(zOffset) == capRadius){
					if (placeBlock){
						world.setBlock(new BlockPos(x + xOffset, y, z + zOffset), CaviercavesModBlocks.GHOST_FUNGUS_BLOCK.get().defaultBlockState(), 3);	
					}
				}
			}
		}
	}
	
	private static int RandomRange(int min, int max){	
		return min + (int) (Math.random() * (max - min));
	}
}
