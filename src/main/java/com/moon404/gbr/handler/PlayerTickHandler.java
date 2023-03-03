package com.moon404.gbr.handler;

import java.util.HashMap;
import java.util.Map;

import com.moon404.gbr.struct.PlayerSpeed;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public class PlayerTickHandler
{
    public static Map<Player, PlayerSpeed> data = new HashMap<Player, PlayerSpeed>();
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.END) return;
        if (event.side != LogicalSide.SERVER) return;
        Player player = event.player;
        PlayerSpeed ps = new PlayerSpeed();
        double dx = 0.0F;
        double dy = 0.0F;
        double dz = 0.0F;
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if (data.containsKey(player))
        {
            ps = data.get(player);
            dx = x - ps.lastX;
            dy = y - ps.lastY;
            dz = z - ps.lastZ;
        }
        ps.lastX = x;
        ps.lastY = y;
        ps.lastZ = z;
        ps.speed = Math.sqrt(dx * dx + dy * dy + dz * dz);
        data.put(player, ps);
    }
}
