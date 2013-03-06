package com.tw.rich.terrain;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class Prison extends Terrain {

    private static final int INPRISION = 2;

	public Prison(int ID){
    	super(ID,'P');
    }

	@Override
	public void enterTerrainEvent(Player player, RichMap richMap) {
		player.setStopTimes(INPRISION);
	}







}
