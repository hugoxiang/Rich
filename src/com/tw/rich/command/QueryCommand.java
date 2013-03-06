package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class QueryCommand extends Command {

    public QueryCommand() {
    }

    @Override
    public void runCommand(Player player, RichMap richMap) {
        System.out.println("显示自家资产信息：");
        System.out.println("资 金：" + player.getMoney() + "元 ");
        System.out.println("点 数：" + player.getPoint() + "点");
        System.out.println("地产：" + player.calculateEstate());
        System.out.println("道具：" + player.calculateProps());
    }

}
