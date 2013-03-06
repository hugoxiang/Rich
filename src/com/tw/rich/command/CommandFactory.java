package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class CommandFactory {

	private final static String ROLL = "ROLL";
	private final static String BLOCK = "BLOCK";
	private final static String BOMB = "BOMB";
	private final static String ROBOT = "ROBOT";
	private final static String SELL = "SELL";
	private final static String SELL_TOOL = "SELLTOOL";
	private final static String QUERY = "QUERY";
	private final static String HELP = "HELP";
	private final static String QUIT = "QUIT";

	public static Command generateCommand(String command, Integer parameter, Player player, RichMap richMap) {
		command = command.toUpperCase();
		if(command.equals(ROLL))
			return new RollCommand();
		else if(command.equals(BLOCK))
			return new BarrierCommand(parameter,player, richMap);
		else if(command.equals(BOMB))
			return new BombCommand(parameter,player, richMap);
		else if(command.equals(ROBOT))
			return new MachineDollCommand(player);
		else if(command.equals(SELL))
			return new SellCommand(parameter);
		else if(command.equals(SELL_TOOL))
			return new SellToolCommand(parameter);
		else if(command.equals(QUERY))
			return new QueryCommand();
		else if(command.equals(HELP))
			return new HelpCommand();
		else if(command.endsWith(QUIT))
			return new QuitCommand();
		else
			return null;
	}

}
