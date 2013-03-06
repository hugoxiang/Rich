package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.terrain.Space;

public class SellCommand extends Command{

	private int HouseID;
	
	public SellCommand(int HouseID){
		this.HouseID = HouseID;
	}
	
	@Override
	public void runCommand(Player player, RichMap richMap) {
		Space space = (Space) richMap.getRichMapCellList().get(HouseID);
		player.sellArea(space, richMap);
	}

}
