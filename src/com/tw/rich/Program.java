package com.tw.rich;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.tw.rich.command.Command;
import com.tw.rich.command.CommandFactory;
import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.terrain.Space;
import com.tw.rich.terrain.Terrain;

public class Program {

    private static List<Player> players = new ArrayList<Player>();
    private static BufferedReader bufferedReader;
    private static boolean inputFlag = false;
    private final static int MoneyOriginalMin = 1000;
    private final static int MoneyOriginalMax = 50000;
    private static boolean isGameEnd = false;

    public static void main(String[] args) throws IOException {

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (!inputFlag) {
            System.out.println("设置玩家初始资金，范围1000～50000（默认10000）");
            initMoney(bufferedReader.readLine().trim());
        }
        inputFlag = false;
        while (!inputFlag) {
            System.out.println("请选择2~4位不重复玩家，输入编号即可（如输入12）。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):");
            initPlayers(bufferedReader.readLine().trim());
        }
        inputFlag = false;

        RichMap Map = new RichMap();
        while (!isGameEnd) {

            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                run(player, Map);
                runCommandImpl(player, Map);
            }
            checkIfGameEnd();
        }
    }

    private static void run(Player player, RichMap map) throws IOException {

        inputFlag = false;

        while (!inputFlag) {
            System.out.print(player.getName() + ">");
            String[] input = bufferedReader.readLine().trim().split(" ");

            Command command;
            if (input.length == 2)
                command = CommandFactory.generateCommand(input[0], Integer.parseInt(input[1]), player, map);
            else
                command = CommandFactory.generateCommand(input[0], null, player, map);

            if(command != null) {
                command.runCommand(player, map);
                map.drawMap();
            } else {
                System.out.println("不合法的命令！");
            }

            if (input[0].toLowerCase().equals("roll"))
                inputFlag = true;
        }

    }

    private static void runCommandImpl(Player player, RichMap Map) {
        Terrain territory = Map.getRichMapCellList().get(player.getPosition());
        territory.enterTerrainEvent(player, Map);
    }

    private static void initPlayers(String playersStr) {
        char[] playerCharList = playersStr.toCharArray();
        ArrayList<Integer> PlayerID = new ArrayList<Integer>();

        for (char p : playerCharList) {
            try {
                PlayerID.add(Integer.parseInt(String.valueOf(p)));
            } catch (Exception e) {
                inputFlag = false;
                return;
            }
        }
        inputFlag = true;
        for (int i = 0; i < PlayerID.size(); i++) {
            players.add(new Player(PlayerID.get(i)));
        }
    }

    private static void initMoney(String moneyOriginalStr) {
        try {
            int MoneyOriginal = Integer.parseInt(moneyOriginalStr);
            if (MoneyOriginal >= MoneyOriginalMin && MoneyOriginal <= MoneyOriginalMax)
                Player.setMoneyOriginal(MoneyOriginal);
            inputFlag = true;
        } catch (Exception e) {
            System.out.println("输入错误格式");
            inputFlag = false;
        }
    }

    private static void checkIfGameEnd() {
        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + "WIN");
            isGameEnd = true;
            System.exit(-1);
        } else {
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                if (player.getMoney() < 0) {
                    Space.ReturnFixedAssets(player.getLandList());
                }
            }
        }
    }

}