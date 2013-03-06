package com.tw.rich.terrain;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.prop.Prop;

public abstract class Terrain {

    protected int id;
    private char display;
    public int owner = -1;

    private Prop roadBlock;
    private Prop bomb;

    public static final char DOGUYA = 'T';
    public static final char GIFT = 'G';
    public static final char MAGIC = 'M';
    public static final char HOSPITAL = 'H';
    public static final char PRISON = 'P';
    public static final char MINE = '$';

    public static final char SPACE = '0';

    public static final char BARRIER = '#';
    public static final char BOMB = '@';

    public Terrain() {
    }

    public Terrain(int id, char display) {
        this.id = id;
        this.display = display;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public char getDisplayNow() {
        return this.display;
    }

    public void setDisplayNow(char Display) {
        this.display = Display;
    }

    public void setOwner(int ownBy) {
        this.owner = ownBy;
    }

    public int getOwner() {
        return this.owner;
    }

    public Prop getBarrier() {
        return roadBlock;
    }

    public void setBarrier(Prop roadBlock) {
        this.roadBlock = roadBlock;
    }

    public Prop getBomb() {
        return bomb;
    }

    public void setBomb(Prop bomb) {
        this.bomb = bomb;
    }

    public abstract void enterTerrainEvent(Player player, RichMap richMap);

}
