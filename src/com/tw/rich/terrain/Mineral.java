package com.tw.rich.terrain;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class Mineral extends Terrain {

    private  int value =0;

    public Mineral(int id){
    	super(id,'$');
    }
    public Mineral(int id, int value){
    	super(id,'$');
    	this.value = value;
    }
    public int getValue(){
        return value;
    }
	@Override
	public void enterTerrainEvent(Player player, RichMap richMap) {
		Mineral mineral = (Mineral) richMap.getRichMapCellList().get(super.id);
		int point = mineral.getValue() + player.getPoint();
		player.setPoint(point);
	}
}
