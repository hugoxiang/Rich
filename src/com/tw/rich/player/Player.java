package com.tw.rich.player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.tw.rich.util.InputController;
import com.tw.rich.util.RichMap;
import com.tw.rich.prop.Prop;
import com.tw.rich.terrain.Space;

public class Player {

    private static int ORIG_MONEY = 10000;
    private int playerID;
    private int money;
    private int point;
    private boolean god;
    private int godUseTime;
    private int stopTimes;
    private int position;

    private char display;

    private HashMap<Integer, Space> spaceMap;
    private HashMap<Integer, Integer> propMap;

    private char lastTerrainName = 'S';

    private static final int Q = 1;
    private static final int A = 2;
    private static final int S = 3;
    private static final int J = 4;

    private InputController inputController;

    private static final int MAX_COUNT_OF_PROP = 10;

    public Player(int id) {
        this.playerID = id;
        this.money = ORIG_MONEY;
        this.point = 0;
        this.position = 0;
        this.setStopTimes(0);
        this.propMap = new HashMap<Integer, Integer>();
        Prop.initPlayerProps(propMap);
        this.spaceMap = new HashMap<Integer, Space>();
        this.inputController = new InputController();
        setDisplay();
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public String getName() {
        switch (this.playerID) {
            case Q:
                return "钱夫人";
            case A:
                return "阿土伯";
            case S:
                return "孙小美";
            case J:
                return "金贝贝";
            default:
                return "";
        }
    }

    public Map<Integer, Space> getLandList() {
        return this.spaceMap;
    }

    public void SetName(String name) {
    }

    public void setMoney(int Money) {
        this.money = Money;
    }

    public int getMoney() {
        return money;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String calculateEstate() {
        int spaceCount = 0;
        int cottageCount = 0;
        int houseCount = 0;
        int skyscrapperCount = 0;

        for (Iterator<Integer> it = spaceMap.keySet().iterator(); it.hasNext(); ) {
            int id = it.next();
            switch (spaceMap.get(id).getType()) {
                case Space.AREA:
                    spaceCount++;
                    break;
                case Space.COTTAGE:
                    cottageCount++;
                    break;
                case Space.BUNGALOW:
                    houseCount++;
                    break;
                case Space.SKYSCRAPER:
                    skyscrapperCount++;
                    break;
            }
        }

        return "空地" + spaceCount + "处；茅屋" + cottageCount
                + "处；洋房" + houseCount + "处；摩天楼" + skyscrapperCount + "处。";
    }

    public String calculateProps() {
        int barrierCount = 0;
        int bombCount = 0;
        int machineDollCount = 0;

        try {
            barrierCount = propMap.get(Prop.BARRIER);
            bombCount = propMap.get(Prop.BOMB);
            machineDollCount = propMap.get(Prop.MACHINE_DOLL);
        } catch (Exception e) {
        }

        return "路障" + barrierCount + "个；" +
                "炸弹" + bombCount + "个；" +
                "机器娃娃" + machineDollCount + "个。";
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void buyArea(Space space, RichMap richMap) {
        if (this.inputController.getInput().equals(InputController.YES)) {
            if (this.money >= space.getBasePrice()) {
                this.money -= space.getBasePrice();
                space.setOwner(this.playerID);
                spaceMap.put(space.getId(), space);
            } else {
                System.out.println("您的资金不足");
            }
        }
    }

    public void updateArea(Space space, RichMap richMap) {
        if (this.inputController.getInput().equals(InputController.YES)) {
            if (this.money >= space.getBasePrice()) {
                this.money -= space.getBasePrice();
                space.upgradeSpace();
                richMap.getRichMapCellList().get(space.getId()).setDisplayNow(space.getDisplay());
            } else {
                System.out.println("您的资金不足");
            }
        }
    }

    public void sellArea(Space space, RichMap richMap) {
        try {
            if (this.spaceMap.containsKey(space.getId())) {
                this.spaceMap.remove(space.getId());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("卖房失败！");
            return;
        }
        this.money += space.getSellPrice();
        space.setOwner(-1);
        space.setType(0);
        richMap.getRichMapCellList().get(space.getId()).setDisplayNow(space.getDisplay());
    }

    public void sellProp(Prop prop) {
        int count = propMap.get(prop.getType());
        if (count >= 1) {
            propMap.remove(prop.getType());
            propMap.put(prop.getType(), count - 1);
            this.point += prop.getPoint();
        } else {
            System.out.println("卖道具失败！");
            return;
        }
    }

    private boolean checkPropCanBuy() {
        return this.propMap.size() < MAX_COUNT_OF_PROP;
    }

    public void buyProp() {
        Prop prop = new Prop(Integer.parseInt(this.inputController.getInput()));
        if (checkPropCanBuy() && this.point >= prop.getPoint()) {
            this.point -= prop.getPoint();
            int count = this.propMap.get(prop.getType()) + 1;
            this.propMap.remove(prop.getType());
            this.propMap.put(prop.getType(), count);
            System.out.println("购买" + prop.getPropName() + "成功");
        }
    }

    public void userProp(Prop p) {
        try {
            this.propMap.remove(p);
        } catch (Exception e) {
            System.out.println("没有该道具，无法使用");
            return;
        }
    }

    public static void setMoneyOriginal(int moneyOriginal) {
        ORIG_MONEY = moneyOriginal;
    }

    public int getStopTimes() {
        return stopTimes;
    }

    public void setStopTimes(int stopTimes) {
        this.stopTimes = stopTimes;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean getGod() {
        return god;
    }

    public void setGod(boolean god) {
        this.god = god;
        if (god) {
            this.godUseTime = 5;
        } else {
            this.godUseTime = 0;
        }
    }

    public int getGodUseTime() {
        return godUseTime;
    }

    public void setGodUseTime(int godUseTime) {
        this.godUseTime = godUseTime;
    }

    public void stepIntoOtherTerritory(Space space) {
        if (this.god) {
            this.godUseTime -= 1;
            if (this.godUseTime == 0)
                this.god = false;
        } else
            this.money -= space.passFree();
    }

    public InputController getInputController() {
        return inputController;
    }

    public void setInputController(InputController inputController) {
        this.inputController = inputController;
    }

    public void gainMoney() {
        this.money += 2000;
    }

    public void gainPoint() {
        this.point += 200;
    }

    public void gainGod() {
        setGod(true);
    }

    public char getDisplay() {
        return display;
    }

    public void setDisplay() {
        switch (this.playerID) {
            case Q:
                this.display = 'Q';
                break;
            case A:
                this.display = 'A';
                break;
            case S:
                this.display = 'S';
                break;
            case J:
                this.display = 'J';
                break;
            default:
                this.display = ' ';
                break;
        }
    }

    public char getLastTerrainName() {
        return lastTerrainName;
    }

    public void setLastTerrainName(char lastTerrainName) {
        this.lastTerrainName = lastTerrainName;
    }
}
