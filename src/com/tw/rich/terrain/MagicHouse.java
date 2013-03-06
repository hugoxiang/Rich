package com.tw.rich.terrain;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class MagicHouse extends Terrain {
    public MagicHouse(int ID){
    	super(ID,'M');
    }

	@Override
	public void enterTerrainEvent(Player player, RichMap richMap) {
		System.out.println("Maigc will come soon!");
	}
}
