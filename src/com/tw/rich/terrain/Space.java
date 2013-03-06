package com.tw.rich.terrain;

import java.util.Map;
import java.util.Set;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;

public class Space extends Terrain {

    public final static int AREA = 0;
    public final static int COTTAGE = 1;
    public final static int BUNGALOW = 2;
    public final static int SKYSCRAPER = 3;

    private int basePrice;
    private int type;
    private static int id;

    private final static int OwnBySystem = -1;

    public Space(int id, int basePrice, int type) {
        super(id, '0');
        this.basePrice = basePrice;
        this.type = type;
        this.setID(id);
    }

    public char getDisplay() {
        switch (this.type) {
            case AREA:
                return '0';
            case COTTAGE:
                return '1';
            case BUNGALOW:
                return '2';
            case SKYSCRAPER:
                return '3';
            default:
                return ' ';
        }
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getSellPrice() {
        return basePrice * (type + 1) * 2;
    }

    public void upgradeSpace() {
        this.type++;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static void ReturnFixedAssets(Map<Integer, Space> LandHashTable) {
        Set<Integer> LandIDCollection = (Set<Integer>) LandHashTable.keySet();
        for (int i : LandIDCollection) {
            Space space = LandHashTable.get(i);
            space.setOwner(-1);
            space.setType(0);
            space.setBarrier(null);
            space.setBomb(null);
        }
    }

    public void setID(int id) {
        this.id = id;
    }

    public int passFree() {
        return this.basePrice / 2 * (this.type + 1);
    }

    @Override
    public void enterTerrainEvent(Player player, RichMap richMap) {
        if (owner == OwnBySystem) {
            while (!player.getInputController().getValidatedInput()) {
                System.out.println("是否购买该处空地，" + this.basePrice + "元（Y/N）？");
                player.getInputController().readFromUser();
            }
            player.buyArea(this, richMap);
            player.getInputController().setValidatedInput(false);
        } else if (owner == player.getPlayerID()) {
            while (!player.getInputController().getValidatedInput()) {
                System.out.println("是否升级该处地产，" + this.basePrice + "元（Y/N）？");
                player.getInputController().readFromUser();
            }
            player.updateArea(this, richMap);
            player.getInputController().setValidatedInput(false);
        } else {
            player.stepIntoOtherTerritory(this);
        }
    }
}