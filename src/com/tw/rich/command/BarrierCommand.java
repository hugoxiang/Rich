package com.tw.rich.command;

import com.tw.rich.util.RichMap;
import com.tw.rich.player.Player;
import com.tw.rich.prop.Prop;
import com.tw.rich.terrain.Terrain;

public class BarrierCommand extends Command {

    public BarrierCommand(int Step, Player player, RichMap richMap) {
        Prop barrier = new Prop(Prop.BARRIER);
        barrier.setOwner(player.getPlayerID());
        player.userProp(barrier);
        Terrain territory = richMap.getRichMapCellList().get(
                (player.getPosition() + Step) % richMap.getRichMapCellList().size());
        territory.setBarrier(barrier);
        territory.setDisplayNow(Terrain.BARRIER);
    }

    @Override
    public void runCommand(Player player, RichMap richMap) {
        Terrain terrain = richMap.getRichMapCellList().get(player.getPosition());
        Prop roadBlock = terrain.getBarrier();

        if (roadBlock != null && player.getPlayerID() != roadBlock.getOwner()) {
            player.setStopTimes(1);
            terrain.setBarrier(null);
            terrain.setDisplayNow(richMap.getOrigRichMapCellListByChar().get(terrain.getId()));
        }
    }
}
