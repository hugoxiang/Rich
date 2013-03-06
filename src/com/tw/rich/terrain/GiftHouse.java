package com.tw.rich.terrain;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class GiftHouse extends Terrain {

    private static final int MONEY = 1;
    private static final int POINT = 2;
    private static final int GOD = 3;

    public GiftHouse(int ID){
        super(ID,'G');
    }
    
	@Override
	public void enterTerrainEvent(Player player, RichMap richMap) {
		System.out.println("欢迎光临礼品屋，请选择一件您喜欢的礼品：");
		System.out.println("1 奖金");
		System.out.println("2 点数卡");
		System.out.println("3 福神");
		player.getInputController().propsHouseInputValidated();
		if(player.getInputController().getValidatedInput())
		{
			switch(Integer.parseInt(player.getInputController().getInput())){
			case MONEY:
				player.gainMoney();
				break;
			case POINT:
				player.gainPoint();
				break;
			case GOD:
				player.gainGod();
				break;
			}
		}
	}



}
