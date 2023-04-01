package com.moon404.gbr.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = "gbr", value = Dist.CLIENT, bus = Bus.FORGE)
public class Climb
{
    public static int tickCount = 0;
    public static boolean jumping = false;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event)
    {
        if (event.phase != Phase.START) return;
        if (event.player instanceof LocalPlayer player)
        {
            Minecraft minecraft = Minecraft.getInstance();
            Options options = minecraft.options;
            if (player.horizontalCollision)
            {
                if (options.keyUp.isDown() && tickCount < 20)
                {
                    tickCount++;
                    Vec3 delta = player.getDeltaMovement();
                    player.setDeltaMovement(delta.x, 0.1, delta.z);
                    if (!jumping && options.keyJump.isDown())
                    {
                        float rot = player.getYRot();
                        double dx = Math.sin(Math.toRadians(-rot)) * 0.5;
                        double dz = Math.cos(Math.toRadians(rot)) * 0.5;
                        if (delta.x == 0) dx = -dx;
                        if (delta.z == 0) dz = -dz;
                        player.setDeltaMovement(dx, 0.3, dz);
                    }
                }
            }
            if (options.keyJump.isDown()) jumping = true;
            else jumping = false;
            if (player.isOnGround())
            {
                tickCount = 0;
            }
        }
    }
}
