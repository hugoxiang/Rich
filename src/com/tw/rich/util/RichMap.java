package com.tw.rich.util;

import java.util.ArrayList;

import com.tw.rich.terrain.*;

public class RichMap {

    private ArrayList<Terrain> richMapCellList = new ArrayList<Terrain>();
    private ArrayList<Character> origRichMapCellListByChar = new ArrayList<Character>();
    private ArrayList<Character> richMapCellListByChar = new ArrayList<Character>();

    private static final int MAP_X = 8;
    private static final int MAP_Y = 29;

    private char[][] positions = new char[MAP_X][MAP_Y];
    private char[] PrintPositions = new char[MAP_X * MAP_Y];
    private int[][] positionsInt = new int[MAP_X][MAP_Y];

    public RichMap() {
        initMap();
    }

    public void initMap() {

        Start start = new Start();
        richMapCellList.add(start);

        origRichMapCellListByChar.add(start.getDisplayNow());

        richMapCellListByChar.add(start.getDisplayNow());

        for (int i = 1; i <= 13; i++) {
            richMapCellList.add(new Space(i, 200, 0));
            origRichMapCellListByChar.add(Terrain.SPACE);

            richMapCellListByChar.add(Terrain.SPACE);

        }

        richMapCellList.add(new Hospital(Hospital.HOSPITAL_POSITION));
        origRichMapCellListByChar.add(Terrain.HOSPITAL);
        richMapCellListByChar.add(Terrain.HOSPITAL);

        for (int i = 15; i <= 27; i++) {
            richMapCellList.add(new Space(i, 200, 0));
            origRichMapCellListByChar.add(Terrain.SPACE);
            richMapCellListByChar.add(Terrain.SPACE);
        }
        richMapCellList.add(new PropsHouse(28));
        origRichMapCellListByChar.add(Terrain.DOGUYA);
        richMapCellListByChar.add(Terrain.DOGUYA);

        for (int i = 29; i <= 34; i++) {
            richMapCellList.add(new Space(i, 500, 0));
            origRichMapCellListByChar.add(Terrain.SPACE);
            richMapCellListByChar.add(Terrain.SPACE);
        }

        richMapCellList.add(new GiftHouse(35));
        origRichMapCellListByChar.add(Terrain.GIFT);
        richMapCellListByChar.add(Terrain.GIFT);

        for (int i = 36; i <= 48; i++) {
            richMapCellList.add(new Space(i, 300, 0));
            origRichMapCellListByChar.add(Terrain.SPACE);
            richMapCellListByChar.add(Terrain.SPACE);
        }

        richMapCellList.add(new Prison(49));
        origRichMapCellListByChar.add(Terrain.PRISON);
        richMapCellListByChar.add(Terrain.PRISON);

        for (int i = 50; i <= 62; i++) {
            richMapCellList.add(new Space(i, 300, 0));
            origRichMapCellListByChar.add(Terrain.SPACE);
            richMapCellListByChar.add(Terrain.SPACE);
        }
        richMapCellList.add(new MagicHouse(63));
        origRichMapCellListByChar.add(Terrain.MAGIC);
        richMapCellListByChar.add(Terrain.MAGIC);

        richMapCellList.add(new Mineral(69, 60));
        origRichMapCellListByChar.add(Terrain.MINE);
        richMapCellListByChar.add(Terrain.MINE);

        richMapCellList.add(new Mineral(68, 80));
        origRichMapCellListByChar.add(Terrain.MINE);
        richMapCellListByChar.add(Terrain.MINE);

        richMapCellList.add(new Mineral(67, 40));
        origRichMapCellListByChar.add(Terrain.MINE);
        richMapCellListByChar.add(Terrain.MINE);

        richMapCellList.add(new Mineral(66, 100));
        origRichMapCellListByChar.add(Terrain.MINE);
        richMapCellListByChar.add(Terrain.MINE);

        richMapCellList.add(new Mineral(65, 80));
        origRichMapCellListByChar.add(Terrain.MINE);
        richMapCellListByChar.add(Terrain.MINE);

        richMapCellList.add(new Mineral(64, 20));
        origRichMapCellListByChar.add(Terrain.MINE);
        richMapCellListByChar.add(Terrain.MINE);

    }

    public void setPositions() {
        for (int i = 0; i < 29; i++) {
            positions[0][i] = richMapCellListByChar.get(i).charValue();
            positionsInt[0][i] = i;
        }
        for (int i = 1; i <= 7; i++) {
            positions[i][0] = richMapCellListByChar.get(70 - i).charValue();
            positionsInt[i][0] = 70 - i;
            positions[i][28] = richMapCellListByChar.get(28 + i).charValue();
            positionsInt[i][28] = 28 + i;
        }
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 27; j++) {
                positions[i][j] = ' ';
                positionsInt[i][j] = -1;
            }
        }
        for (int j = 1; j <= 27; j++) {
            positions[7][j] = richMapCellListByChar.get(63 - j).charValue();
        }

    }

    public void setupPrintPositions() {
        int k = 0;
        for (int i = 0; i < MAP_X; i++) {
            for (int j = 0; j < MAP_Y; j++) {
                if (j == MAP_Y)
                    PrintPositions[k] = ' ';
                else
                    PrintPositions[k] = positions[i][j];
                k++;
            }
        }
    }

    private void setMapPointDisplayListNow() {
        for (int i = 0; i < richMapCellListByChar.size(); i++) {
            richMapCellListByChar.set(i, richMapCellList.get(i).getDisplayNow());
        }
    }

    public ArrayList<Terrain> getRichMapCellList() {
        return this.richMapCellList;
    }

    public ArrayList<Character> getOrigRichMapCellListByChar() {
        return origRichMapCellListByChar;
    }

    private void doDraw() {
        for (int i = 0; i < MAP_X * MAP_Y; i++) {
            if (i != 0 && i % MAP_Y == 0)
                System.out.println();
            System.out.print(String.valueOf(PrintPositions[i]));
        }
        System.out.println();
    }

    public void drawMap() {
        setMapPointDisplayListNow();
        setPositions();
        setupPrintPositions();
        doDraw();
    }
}
