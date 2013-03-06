package com.tw.rich.terrain;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class Hospital extends Terrain {

    public static final int HOSPITAL_POSITION = 14;
    public static final int DAYS_IN_HOSPITAL = 3;

    public Hospital(int ID){
    	super(ID,'H');
    }

	@Override
	public void enterTerrainEvent(Player player, RichMap richMap) {
		player.setStopTimes(Hospital.DAYS_IN_HOSPITAL);
	}
}
