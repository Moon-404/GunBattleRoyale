package com.moon404.gbr.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
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
            if (player.horizontalCollision && !player.isOnGround())
            {
                if (options.keyUp.isDown() && tickCount < 20)
                {
                    Vec3 delta = player.getDeltaMovement();
                    if (0.1 >= delta.y)
                    {
                        tickCount++;
                        player.setDeltaMovement(delta.x, 0.1, delta.z);
                        if (!jumping && options.keyJump.isDown())
                        {
                            float speed = player.getSpeed() * 4;
                            Vec3 jumpvec = player.getForward().multiply(1, 0, 1).normalize().scale(speed);
                            if (delta.x == 0) jumpvec = jumpvec.multiply(-1, 1, 1);
                            if (delta.z == 0) jumpvec = jumpvec.multiply(1, 1, -1);
                            double dy = 0.3;
                            if (player.hasEffect(MobEffects.JUMP))
                            {
                                dy *= 1.2 + 0.2 * player.getEffect(MobEffects.JUMP).getAmplifier();
                            }
                            jumpvec = jumpvec.add(0, dy, 0);
                            player.setDeltaMovement(jumpvec);
                        }
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
