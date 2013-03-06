package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class QuitCommand extends Command{

	public QuitCommand(){}
	
	@Override
	public void runCommand(Player player, RichMap richMap) {
		System.exit(-1);
	}
}
