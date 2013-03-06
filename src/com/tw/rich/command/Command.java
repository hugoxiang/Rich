package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public abstract class Command {
	public abstract void runCommand(Player player, RichMap richMap);
}
