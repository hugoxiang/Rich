package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.prop.Prop;
import com.tw.rich.terrain.Terrain;

public class MachineDollCommand extends Command {

	public MachineDollCommand(Player player){
		Prop machineDoll  = new Prop(Prop.MACHINE_DOLL);
		machineDoll.setOwner(player.getPlayerID());
		player.userProp(machineDoll);
	}
	
	@Override
	public void runCommand(Player player, RichMap richMap) {
		for(int i = 0; i <= 10; i++ ){
			int position = (player.getPosition() + i) % richMap.getRichMapCellList().size();
            clearBombAndBarrier(richMap, position);
		}
	}

    private void clearBombAndBarrier(RichMap richMap, int position) {
        Terrain territory = richMap.getRichMapCellList().get(position);
        territory.setBomb(null);
        territory.setBarrier(null);
        territory.setDisplayNow(richMap.getOrigRichMapCellListByChar().get(territory.getId()));
    }

}
