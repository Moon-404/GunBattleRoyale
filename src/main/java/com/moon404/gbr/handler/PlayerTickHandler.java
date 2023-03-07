package com.moon404.gbr.handler;

import java.util.HashMap;
import java.util.Map;

import com.moon404.gbr.struct.ArmorInfo;
import com.moon404.gbr.struct.ArmorMessage;
import com.moon404.gbr.struct.PlayerSpeed;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.PacketDistributor;

public class PlayerTickHandler
{
    public static Map<Player, PlayerSpeed> data = new HashMap<Player, PlayerSpeed>();
    public static Map<Player, ArmorInfo> armors = new HashMap<Player, ArmorInfo>();

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

        Scoreboard scoreboard = player.getServer().getScoreboard();
        Objective objective = scoreboard.getObjective("global");
        int game_start = scoreboard.getOrCreatePlayerScore("game_start", objective).getScore();
        if (game_start == 1)
        {
            if (!armors.containsKey(player))
            {
                ArmorInfo armor = new ArmorInfo();
                armor.level = 0;
                armor.upgrade = armor.getLevelUpgrade();
                armors.put(player, armor);
            }
            ArmorMessage.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayer)player;}), armors.get(player));
        }
        else
        {
            armors.clear();
            ArmorMessage.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {return (ServerPlayer)player;}), new ArmorInfo());
        }
    }
}
