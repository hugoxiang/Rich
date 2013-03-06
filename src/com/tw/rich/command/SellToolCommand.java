package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.prop.Prop;

public class SellToolCommand extends Command {

	private Prop prop;
	
	public SellToolCommand(int id){
		prop = new Prop(id);
	}

	@Override
	public void runCommand(Player player, RichMap richMap) {
		player.sellProp(prop);
	}

}
