package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.LiquidBlock;

import java.util.List;
import java.util.Collections;

import net.gglobensky.caviercaves.init.CaviercavesModBlocks;

public class OnNeighbourChangedBlockstateManagerProcedure {
	public static void ShowInsideFace(BlockState blockstate, Level world, BlockPos pos, BlockPos fromPos, Block fromBlock) {
        /*PlayerList playerlist = world.getServer().getPlayerList();
        List<ServerPlayer> players = playerlist.getPlayers();
        players.get(0).sendSystemMessage(Component.literal("pos: " + pos + ", fromPos: " + fromPos));*/
 
        int xDiff = pos.getX() - fromPos.getX();
        int yDiff = pos.getY() - fromPos.getY();
        int zDiff = pos.getZ() - fromPos.getZ();

		if (!(fromBlock instanceof LiquidBlock)){
	        if (xDiff < 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("east"), false), 3);	
	        }
	        else if (xDiff > 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("west"), false), 3);	
	        }
	        else if (yDiff < 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("up"), false), 3);	
	        }
	        else if (yDiff > 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("down"), false), 3);	
	        }
	        else if (zDiff < 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("south"), false), 3);	
	        }
	        else if (zDiff > 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("north"), false), 3);	
	        }
		}
	}

	public static void ShowInsideFaceOnSameNeighbour(BlockState blockstate, Level world, BlockPos pos, BlockPos fromPos, Block block, Block fromBlock) {
        /*PlayerList playerlist = world.getServer().getPlayerList();
        List<ServerPlayer> players = playerlist.getPlayers();
        players.get(0).sendSystemMessage(Component.literal("pos: " + pos + ", fromPos: " + fromPos));*/

        int xDiff = pos.getX() - fromPos.getX();
        int yDiff = pos.getY() - fromPos.getY();
        int zDiff = pos.getZ() - fromPos.getZ();
        
		if (block == fromBlock){
	        if (xDiff < 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("east"), false), 3);	
	        }
	        else if (xDiff > 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("west"), false), 3);	
	        }
	        else if (yDiff < 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("up"), false), 3);	
	        }
	        else if (yDiff > 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("down"), false), 3);	
	        }
	        else if (zDiff < 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("south"), false), 3);	
	        }
	        else if (zDiff > 0){
	        	world.setBlock(pos, blockstate.setValue(BooleanProperty.create("north"), false), 3);	
	        }
		}
	}

}
