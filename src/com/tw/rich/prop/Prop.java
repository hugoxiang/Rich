package com.tw.rich.prop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Prop {

    public static final int BARRIER_POINT = 50;
    public static final int MACHINE_DOLL_POINT = 30;
    public static final int BOMB_POINT = 50;
    public static final String BARRIER_SYMBOL = "#";
    public static final String MACHINE_DOLL_SYMBOL = "";
    public static final String BOMB_SYMBOL = "@";

    private int type;
    private static String propName;
    private int point;
    private String propSymbol;
    private int owner = -1;

    public static final int BARRIER = 1;
    public static final int MACHINE_DOLL = 2;
    public static final int BOMB = 3;

    public Prop(int type) {
        this.type = type;
        setPropName();
        setPoint();
        setPropSymbol();
    }

    public static void initPlayerProps(HashMap<Integer, Integer> propHashMap) {
        propHashMap.put(BARRIER, 0);
        propHashMap.put(MACHINE_DOLL, 0);
        propHashMap.put(BOMB, 0);
    }

    public int getType() {
        return type;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName() {
        switch (type) {
            case BARRIER:
                propName = "路障";
                break;
            case MACHINE_DOLL:
                propName = "机器娃娃";
                break;
            case BOMB:
                propName = "炸弹";
                break;
        }
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getPropSymbol() {
        return propSymbol;
    }

    public void setPropSymbol() {
        switch (type) {
            case BARRIER:
                this.propSymbol = BARRIER_SYMBOL;
                break;
            case MACHINE_DOLL:
                this.propSymbol = MACHINE_DOLL_SYMBOL;
                break;
            case BOMB:
                this.propSymbol = BOMB_SYMBOL;
                break;
        }
    }

    public int getPoint() {
        return point;
    }

    public void setPoint() {
        switch (type) {
            case BARRIER:
                this.point = BARRIER_POINT;
                break;
            case MACHINE_DOLL:
                this.point = MACHINE_DOLL_POINT;
                break;
            case BOMB:
                this.point = BOMB_POINT;
                break;
        }
    }

    public static ArrayList<Prop> ReturnAllKindsProps() {
        ArrayList<Prop> result = new ArrayList<Prop>();
        result.add(new Prop(BARRIER));
        result.add(new Prop(BOMB));
        result.add(new Prop(MACHINE_DOLL));
        return result;
    }

}
