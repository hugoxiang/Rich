package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.terrain.Terrain;

public class RollCommand extends Command {
    public RollCommand() {
    }

    @Override
    public void runCommand(Player player, RichMap richMap) {
        if (player.getStopTimes() == 0) {
            int currentPosition = player.getPosition();
            Terrain terrain = richMap.getRichMapCellList().get(currentPosition);
            terrain.setDisplayNow(player.getLastTerrainName());

            int rollSteps = (int) (Math.random() * 6 + 1);
            int position = (player.getPosition() + rollSteps) % richMap.getRichMapCellList().size();
            player.setPosition(position);

            terrain = richMap.getRichMapCellList().get(position);
            player.setLastTerrainName(terrain.getDisplayNow());
            terrain.setDisplayNow(player.getDisplay());
        } else {
            int StopTime = player.getStopTimes();
            player.setStopTimes(StopTime - 1);
        }
    }

}
