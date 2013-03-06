package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.prop.Prop;
import com.tw.rich.terrain.Hospital;
import com.tw.rich.terrain.Terrain;

public class BombCommand extends Command {

	public BombCommand(int Step, Player player, RichMap richMap){
		Prop bomb = new Prop(Prop.BOMB);
		bomb.setOwner(player.getPlayerID());
		player.userProp(bomb);
		Terrain territory = richMap.getRichMapCellList().get(
				(player.getPosition()+ Step) % richMap.getRichMapCellList().size());
		territory.setBomb(bomb);
		territory.setDisplayNow(Terrain.BOMB);
	}
	
	@Override
	public void runCommand(Player player, RichMap richMap) {
		Terrain territory = richMap.getRichMapCellList().get(player.getPosition());
		Prop bomb = territory.getBomb();
		if(bomb != null && player.getPlayerID() != bomb.getOwner()){
			player.setStopTimes(Hospital.DAYS_IN_HOSPITAL);
			player.setPosition(Hospital.HOSPITAL_POSITION);
			territory.setBomb(null);
			territory.setDisplayNow(richMap.getOrigRichMapCellListByChar().get(territory.getId()));
		}
	}

}
