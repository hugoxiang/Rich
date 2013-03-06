package com.tw.rich.terrain;

import com.tw.rich.util.InputController;
import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.prop.Prop;

import java.util.ArrayList;
import java.util.List;

public class PropsHouse extends Terrain {

    private List<Prop> props = new ArrayList<Prop>();

    public PropsHouse(int ID){
        super(ID,'T');
        this.props = Prop.ReturnAllKindsProps();
    }
    private int MinPoint(){
        int Min = props.get(0).getPoint();
        for(int i = 0; i < props.size(); i++){
        	if(Min > props.get(i).getPoint())
        		Min = props.get(i).getPoint();
        }
        return Min;
    }

	@Override
	public void enterTerrainEvent(Player player, RichMap richMap) {
		if(player.getPoint() < MinPoint()){
			Prop p = new Prop(Integer.parseInt(player.getInputController().getInput()));
			System.out.println("您当前剩余的点数为"+player.getPoint()+"， 不足以购买"+
							p.getPropName()+"道具。");
		}
		while(!player.getInputController().getValidatedInput()
				&& player.getInputController().getInput() != InputController.EXIT
				&& player.getPoint() >= MinPoint()){
			System.out.println("欢迎光临道具屋， 请选择您所需要的道具：");
			System.out.println("1 路障");
			System.out.println("2 机器娃娃");
			System.out.println("3 炸弹");
			player.getInputController().propsHouseInputValidated();
			player.buyProp();
		}
	}
}
