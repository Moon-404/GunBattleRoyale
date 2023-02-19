package com.moon404.gbr;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class EventHandler
{
    public static Map<Player, Double> speedMap = new HashMap<Player, Double>();
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.END) return;
        if (event.side != LogicalSide.CLIENT) return;
        Player player = event.player;
        double dx = player.getX() - player.xOld;
        double dy = player.getY() - player.yOld;
        double dz = player.getZ() - player.zOld;
        double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
        speedMap.put(player, d);
    }
}
