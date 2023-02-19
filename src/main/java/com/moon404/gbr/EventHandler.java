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
    public static Map<Player, Double> lastX = new HashMap<Player, Double>();
    public static Map<Player, Double> lastY = new HashMap<Player, Double>();
    public static Map<Player, Double> lastZ = new HashMap<Player, Double>();
    public static Map<Player, Double> speedMap = new HashMap<Player, Double>();
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.END) return;
        if (event.side != LogicalSide.SERVER) return;
        Player player = event.player;
        double dx = 0.0F;
        double dy = 0.0F;
        double dz = 0.0F;
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if (lastX.containsKey(player)) dx = x - lastX.get(player);
        if (lastY.containsKey(player)) dy = y - lastY.get(player);
        if (lastZ.containsKey(player)) dz = z - lastZ.get(player);
        lastX.put(player, x);
        lastY.put(player, y);
        lastZ.put(player, z);
        double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
        speedMap.put(player, d);
    }
}
