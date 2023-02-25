package com.moon404.gbr;

import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RankParticles
{
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event)
    {
        if (event.phase != Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        ClientLevel clientLevel = mc.level;
        if (clientLevel == null) return;
        Scoreboard scoreboard = clientLevel.getScoreboard();
        Iterable<Entity> entities = clientLevel.entitiesForRendering();
        for (Entity entity : entities)
        {
            if (entity instanceof Player)
            {
                Item item = ((Player)entity).getItemBySlot(EquipmentSlot.CHEST).getItem();
                if (item.getClass() == ElytraItem.class || entity.getY() > 160)
                {
                    Objective objective = scoreboard.getObjective("rank");
                    int score = scoreboard.getOrCreatePlayerScore(entity.getScoreboardName(), objective).getScore();
                    Vector3f color;
                    if (score >= 150) color = new Vector3f(1.00F, 0.25F, 0.21F);
                    else if (score >= 100) color = new Vector3f(0.69F, 0.05F, 0.79F);
                    else if (score >= 60) color = new Vector3f(0.00F, 0.45F, 0.85F);
                    else if (score >= 30) color = new Vector3f(0.50F, 0.86F, 1.00F);
                    else if (score >= 10) color = new Vector3f(1.00F, 0.86F, 0.00F);
                    else color = new Vector3f(0.75F, 0.75F, 0.75F);
                    DustParticleOptions options = new DustParticleOptions(color, 2.0F);
                    clientLevel.addParticle(options, true, entity.getX(), entity.getY() + 0.2F, entity.getZ(), 0, 0, 0);
                }
            }
        }
    }
}
